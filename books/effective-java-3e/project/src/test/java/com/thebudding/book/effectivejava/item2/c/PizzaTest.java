package com.thebudding.book.effectivejava.item2.c;

import static com.thebudding.book.effectivejava.item2.c.NyPizza.Size.SMALL;

import com.thebudding.book.effectivejava.item2.c.Pizza.Topping;
import org.junit.Test;

public class PizzaTest {

  @Test
  public void test() {
    NyPizza nyPizza = new NyPizza.Builder(SMALL).addTopping(Topping.ONION).build();
    Calzone calzone = new Calzone.Builder().addTopping(Topping.HAM).sauceInside().build();
  }
}
