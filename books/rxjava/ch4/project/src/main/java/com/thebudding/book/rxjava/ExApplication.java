package com.thebudding.book.rxjava;

import com.thebudding.book.rxjava.dao.PersonDao;
import com.thebudding.book.rxjava.dto.Person;
import java.util.List;
import rx.Observable;
import rx.observables.BlockingObservable;

public class ExApplication {

  private static final PersonDao personDao = new PersonDao();

  public static void collectionToObservable() {
    List<Person> list = personDao.listPeople();
    Observable.from(list).subscribe(System.out::println);
  }

  public static void blockingObservable() {
    Observable<Person> personStream = Observable.from(personDao.listPeople());
    Observable<List<Person>> peopleList = personStream.toList();
    BlockingObservable<List<Person>> peopleBlocking = peopleList.toBlocking();
    List<Person> people = peopleBlocking.single();
    people.forEach(System.out::println);
  }
}
