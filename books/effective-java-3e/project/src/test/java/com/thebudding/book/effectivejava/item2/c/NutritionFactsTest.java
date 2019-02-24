package com.thebudding.book.effectivejava.item2.c;

import org.junit.Test;

public class NutritionFactsTest {

  @Test
  public void test() {
    NutritionFacts cocaCola =
        new NutritionFacts.Builder(240, 8).calories(100).sodium(25).carbohydrate(27).build();
  }
}
