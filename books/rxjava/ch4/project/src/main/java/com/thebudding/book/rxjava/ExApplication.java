package com.thebudding.book.rxjava;

import static rx.Observable.defer;
import static rx.Observable.from;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.thebudding.book.rxjava.dao.PersonDao;
import com.thebudding.book.rxjava.dto.Book;
import com.thebudding.book.rxjava.dto.Person;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import rx.Observable;
import rx.Scheduler;
import rx.observables.BlockingObservable;
import rx.schedulers.Schedulers;

public class ExApplication {

  private static long START_TIME = 0;

  private static final Scheduler schedulerA = Schedulers.from(
      Executors.newFixedThreadPool(10, threadFactory("Sched-A-%d")));
  private static final Scheduler schedulerB = Schedulers.from(
      Executors.newFixedThreadPool(10, threadFactory("Sched-B-%d")));
  private static final Scheduler schedulerC = Schedulers.from(
      Executors.newFixedThreadPool(10, threadFactory("Sched-C-%d")));

  private static final PersonDao personDao = new PersonDao();

  public static void collectionToObservable() {
    List<Person> list = personDao.listPeople();
    from(list).subscribe(System.out::println);
    System.out.println("==========\n\n");
  }

  public static void blockingObservable() {
    Observable<Person> personStream = from(personDao.listPeople());
    Observable<List<Person>> peopleList = personStream.toList();
    BlockingObservable<List<Person>> peopleBlocking = peopleList.toBlocking();
    List<Person> people = peopleBlocking.single();
    people.forEach(System.out::println);
    System.out.println("==========\n\n");
  }

  public static void lazyCollectionToObservable() {
    defer(() -> from(personDao.listPeople())).subscribe(System.out::println);

    // as-is
    bestBookFor(new Person("1"));
    // bo-be
    bestBookRxJavaFor(new Person("2"));

    System.out.println("==========\n\n");
  }

  private static void bestBookRxJavaFor(Person person) {
    Observable<Book> recommendedBook = Observable.just(new Book("recommend")); // recommend
    Observable<Book> bestSellerBook = Observable.just(new Book("best seller")); // best seller
    Observable<Book> book = recommendedBook.onErrorResumeNext(bestSellerBook);
    Observable<String> title = book.map(Book::getTitle);
    title.subscribe(System.out::println);
  }

  private static void bestBookFor(Person person) {
    Book book = null;
    try {
      // recommend
      book = new Book("recommend");
    }
    catch (Exception e) {
      // substitute by bast seller
      book = new Book("best seller");
    }
    System.out.println(book.getTitle());
  }

  private static Observable<Person> allPeople(int initialPage) {
    return defer(() -> from(personDao.listPeople(10)))
        .concatWith(defer(() -> allPeople(initialPage + 1)));
  }

  public static void immediateScheduler() {
    START_TIME = System.currentTimeMillis();

    Scheduler scheduler = Schedulers.immediate();
    Scheduler.Worker worker = scheduler.createWorker();

    log("Main start");
    worker.schedule(() -> {
      log(" Outer start");
      sleepOneSecond();
      worker.schedule(() -> {
        log("  Inner start");
        sleepOneSecond();
        log("  Inner end");
      });
      log(" Outer end");
    });
    log("Main end");
    worker.unsubscribe();
    System.out.println("==========\n\n");
  }

  public static void trampolineScheduler() {
    START_TIME = System.currentTimeMillis();

    Scheduler scheduler = Schedulers.trampoline();
    Scheduler.Worker worker = scheduler.createWorker();

    log("Main start");
    worker.schedule(() -> {
      log(" Outer start");
      sleepOneSecond();
      worker.schedule(() -> {
        log("  Inner start");
        sleepOneSecond();
        log("  Inner end");
      });
      log(" Outer end");
    });
    log("Main end");
    worker.unsubscribe();
    System.out.println("==========\n\n");
  }

  private static void sleepOneSecond() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      System.exit(1);
    }
  }

  private static void log(String msg) {
    long elapsedTime = System.currentTimeMillis() - START_TIME;
    System.out.println(String.format(
        "%5d", elapsedTime) +
        "\t| " + Thread.currentThread().getName() + "\t|" + msg);
  }

  private static Observable<String> simple() {
     return Observable.create(subscriber -> {
       log("Subscribed");
       subscriber.onNext("A");
       subscriber.onNext("B");
       subscriber.onCompleted();
     });
  }


  public static void declarativeSubscription() {
    START_TIME = System.currentTimeMillis();

    // simple case
    System.out.println("Simple Case");
    simple().subscribe(System.out::println);


    // subscribe
    System.out.println("Subscribe Case");
    log("Starting");
    Observable<String> obs2 = simple().map(x -> x).filter(x -> true);
    log("Transformed");
    obs2.subscribe(
        x -> log("Got " + x),
        Throwable::printStackTrace,
        () -> log("Completed"));
    log("Exiting");

    // subscribeOn
    System.out.println("SubscribeOn Case");
    log("Starting");
    final Observable<String> obs = simple();
    log("Created");
    obs.subscribeOn(Schedulers.io())
        .subscribeOn(Schedulers.io())
        .subscribe(
            x -> log("Got " + x),
            Throwable::printStackTrace,
            () -> log("Completed"));
    log("Exiting");
  }

  private static ThreadFactory threadFactory(String pattern) {
    return new ThreadFactoryBuilder().setNameFormat(pattern).build();
  }

  public static void declarativeObservationSimple() {
    START_TIME = System.currentTimeMillis();

    log("Starting");
    final Observable<String> obs = simple();
    log("Created");
    obs
        .doOnNext(x -> log("Found 1: " + x))
        .observeOn(schedulerA)
        .doOnNext(x -> log("Found 2: " + x))
        .subscribe(
            x -> log("Got " + x),
            Throwable::printStackTrace,
            () -> log("Completed")
        );
    sleep(1000L);
    log("Exiting");
    System.out.println("==========\n\n");
  }

  public static void declarativeObservation() {
    START_TIME = System.currentTimeMillis();

    log("Starting");
    final Observable<String> obs = simple();
    log("Created");
    obs
        .doOnNext(x -> log("Found 1: " + x))
        .observeOn(schedulerB)
        .doOnNext(x -> log("Found 2: " + x))
        .observeOn(schedulerC)
        .doOnNext(x -> log("Found 3: " + x))
        .subscribeOn(schedulerA)
        .subscribe(
            x -> log("Got " + x),
            Throwable::printStackTrace,
            () -> log("Completed")
        );
    sleep(1000L);
    log("Exiting");
    System.out.println("==========\n\n");
  }

  private static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (Exception e) {
      System.exit(1);
    }
  }

  public static void schedulerAdvanced() {
    log("Starting");
    Observable<String> obs = Observable.create(subscriber -> {
      log("Subscribed");
      subscriber.onNext("A");
      subscriber.onNext("B");
      subscriber.onNext("C");
      subscriber.onNext("D");
      subscriber.onCompleted();
    });
    log("Created");
    obs
        .subscribeOn(schedulerA)
        .flatMap(record -> store(record).subscribeOn(schedulerB))
        .observeOn(schedulerC)
        .subscribe(
            x -> log("Got " + x),
            Throwable::printStackTrace,
            () -> log("Completed")
        );
    sleep(1000L);
    log("Exiting");
    System.out.println("==========\n\n");
  }

  private static Observable<UUID> store(String record) {
    return Observable.create(subscriber -> {
      log("Storing " + record);
      // do hard work
      subscriber.onNext(UUID.randomUUID());
      subscriber.onCompleted();
    });
  }
}
