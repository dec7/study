package com.thebudding.book.rxjava;

import static rx.Observable.defer;
import static rx.Observable.from;

import com.thebudding.book.rxjava.dao.PersonDao;
import com.thebudding.book.rxjava.dto.Book;
import com.thebudding.book.rxjava.dto.Person;
import java.util.List;
import rx.Observable;
import rx.observables.BlockingObservable;

public class ExApplication {

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
}
