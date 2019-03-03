package com.thebudding.book.effectivejava.item30;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.function.UnaryOperator;
import org.junit.Test;

public class GenericMethodTest {

  @Test
  public void test() {
    Set<String> a = Set.of("1","2","3");
    Set<String> b = Set.of("a","b","c");
    Set<String> all = GenericMethod.union(a, b);
    System.out.println(all);
  }

  @Test
  public void test2() {
    String[] str = { "a", "b", "c" };
    UnaryOperator<String> strOp = GenericMethod.identityFunction();
    for (String s : str) {
      System.out.println(strOp.apply(s));
    }

    Number[] num = { 1, 2.0, 3L };
    UnaryOperator<Number> numOp = GenericMethod.identityFunction();
    for (Number n : num) {
      System.out.println(numOp.apply(n));
    }
  }
}