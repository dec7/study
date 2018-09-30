package com.thebudding.book.rxjava;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import rx.Observable;
import rx.subscriptions.Subscriptions;

public class ExObservable {

  public static void method() {
    Observable.just("");
//    Observable.fromArray("","");
//    Observable.fromIterable(new ArrayList<String>());
    Observable.from(new ArrayList<String>().toArray());
    Observable.range(0, 1);
    Observable.empty();
    Observable.never();
    Observable.error(new Exception());
  }

  public static void create() {
    log("Before");
    Observable.range(5,3).subscribe(ExObservable::log);
    log("After");
  }

  private static void log(Object msg) {
    System.out.println(Thread.currentThread().getName() + ":" + msg);
  }

  public static void createAdvanced() {
    Observable<Integer> ints = Observable.create(s -> {
      log("Create");
      s.onNext(5);
      s.onNext(6);
      s.onNext(7);
      s.onCompleted();
      log("Completed");
    });

    log("Starting");
    ints.subscribe(i -> log("Element: " + i));
    log("Exit");
  }

  public static void publishToAll() {
    Observable<Integer> ints = Observable.create(s -> {
      log("Create");
      s.onNext(42);
      s.onCompleted();
    });
    log("Starting");
    ints.subscribe(i -> log("Elements A: " + i));
    ints.subscribe(i -> log("Elements B: " + i));
    log("Exit");

    System.out.println("=======");
    // cached
    Observable<Object> intsCached = Observable.create(s -> {
      log("Create");
      s.onNext(42);
      s.onCompleted();
    }).cache();
    log("Starting");
    intsCached.subscribe(i -> log("Elements A: " + i + ", Cached Ref: " + System.identityHashCode(i)));
    intsCached.subscribe(i -> log("Elements B: " + i + ", Cached Ref: " + System.identityHashCode(i)));
    log("Exit");
  }

  public static void infiniteStream() {
    wrongImplementation1();

    implementation2();

    wrongDelayedImplementation1("hello");

    delayedImplementation1("hello");

    List<Integer> list = new ArrayList<>();
    list.add(1); list.add(2);
    wrongInnerThreadImplementation1(list);

  }

  private static Observable<Object> wrongInnerThreadImplementation1(Collection<Integer> ids) {
    return Observable.create(s -> {
      ExecutorService pool = Executors.newFixedThreadPool(10);
      AtomicInteger countDown = new AtomicInteger(ids.size());

      ids.forEach(id -> pool.submit(() -> {
        final Object data = new Object(); //load(id);
        s.onNext(data);
        if (countDown.decrementAndGet() == 0) {
          pool.shutdownNow();
          s.onCompleted();
        }
      }));
    });
  }

  private static <T> Observable<T> delayedImplementation1(T x) {
    return Observable.create(s -> {
      Runnable r = () -> {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          // do noting
        }
        if (!s.isUnsubscribed()) {
          s.onNext(x);
          s.onCompleted();
        }
      };
      final Thread thread = new Thread(r);
      thread.start();
      s.add(Subscriptions.create(thread::interrupt));
    });
  }

  private static <T> Observable<T> wrongDelayedImplementation1(T x) {
    return Observable.create(s -> {
      Runnable r = () -> {
        try {
          Thread.sleep(10000);
        } catch (InterruptedException e) {
          // do noting
        }
        if (!s.isUnsubscribed()) {
          s.onNext(x);
          s.onCompleted();
        }
      };
      new Thread(r).start();
    });
  }


  private static void implementation2() {
    Observable<BigInteger> naturalNumbers = Observable.create(s -> {
      Runnable r = () -> {
        BigInteger i = BigInteger.ZERO;
        while (!s.isUnsubscribed()) {
          s.onNext(i);
          i = i.add(BigInteger.ONE);
        }
      };
      new Thread(r).start();
    });
    naturalNumbers.subscribe(x -> log(x));
  }

  private static void wrongImplementation1() {

    Observable<BigInteger> naturalNumbers = Observable.create(s -> {
      BigInteger i = BigInteger.ZERO;
      while (true) {
        s.onNext(i);
        i = i.add(BigInteger.ONE);
      }
    });
    naturalNumbers.subscribe(x -> log(x));
    //naturalNumbers.unsafeSubscribe();
  }

  public static void processError() {
    Observable.create(s -> {
      try {
        s.onNext(1);
        s.onCompleted();
      }
      catch (Exception e) {
        s.onError(e);
      }
    });

    Observable.fromCallable(() -> {
          System.out.println("Exec fn");
          return "data";
    });
  }

  public static void timer() {
    Observable.timer(1, TimeUnit.SECONDS).subscribe((n) -> log(n));
    System.out.println("======");
    Observable.interval(1_000, TimeUnit.MILLISECONDS).subscribe((n) -> log(n));
  }
}
