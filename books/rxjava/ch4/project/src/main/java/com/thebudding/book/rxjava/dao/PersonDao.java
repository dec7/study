package com.thebudding.book.rxjava.dao;

import com.thebudding.book.rxjava.dto.Person;
import java.util.Arrays;
import java.util.List;

public class PersonDao {

  private static final int PAGE_SIZE = 10;

  public List<Person> listPeople() {
    return query("SELECT * FROM PEOPLE");
  }

  public List<Person> listPeople(int page) {
    return query("SELECT * FROM PEOPLE ORDER BY id LIMIT ? OFFSET ?", PAGE_SIZE, page * PAGE_SIZE);
  }

  private List<Person> query(String query, int pageSize, int page) {
    return query(query);
  }

  private List<Person> query(String query) {
    return Arrays.asList(
        new Person("alice"),
        new Person("bob"),
        new Person("charles"));
  }
}
