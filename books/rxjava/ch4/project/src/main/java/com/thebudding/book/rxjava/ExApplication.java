package com.thebudding.book.rxjava;

import com.thebudding.book.rxjava.dao.PersonDao;
import com.thebudding.book.rxjava.dto.Person;
import java.util.List;
import rx.Observable;

public class ExApplication {

  public static Observable<Person> collectionToObservable() {
    PersonDao personDao = new PersonDao();
    List<Person> list = personDao.listPeople();
    return Observable.from(list);
  }
}
