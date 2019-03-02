package com.thebudding.book.effectivejava.item52.overriding;

import java.util.List;
import org.junit.Test;

public class WineTest {

  @Test
  public void test() {
    List<Wine> wines = List.of(new Wine(), new SparklingWine(), new Champagne());

    for (Wine wine : wines) {
      System.out.println(wine.name());
    }
  }

}