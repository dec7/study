package com.thebudding.book.rxjava.dao;

import com.thebudding.book.rxjava.dto.Person;
import java.util.ArrayList;
import java.util.List;

public class PersonDao {

  public List<Person> listPeople() {
    return query("SELECT * FROM PEOPLE");
  }

  private List<Person> query(String query) {
    return new ArrayList<>();
  }

}
