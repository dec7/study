package com.thebudding.book.rxjava.dao;

import com.thebudding.book.rxjava.dto.Person;
import java.util.Arrays;
import java.util.List;

public class PersonDao {

  public List<Person> listPeople() {
    return query("SELECT * FROM PEOPLE");
  }

  private List<Person> query(String query) {
    return Arrays.asList(
        new Person("alice"),
        new Person("bob"),
        new Person("charles"));
  }
}
