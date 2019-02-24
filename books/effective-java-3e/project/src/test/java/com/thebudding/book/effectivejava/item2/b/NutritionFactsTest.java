package com.thebudding.book.effectivejava.item2.b;

import org.junit.Test;

public class NutritionFactsTest {

  @Test
  public void test() {
    NutritionFacts cocaCola = new NutritionFacts();
    cocaCola.setServingSize(240);
    cocaCola.setServings(8);
    cocaCola.setCalories(100);
    cocaCola.setSodium(23);
    cocaCola.setCarbohydrate(27);
  }

}