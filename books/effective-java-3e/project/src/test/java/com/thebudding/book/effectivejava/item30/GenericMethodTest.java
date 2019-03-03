package com.thebudding.book.effectivejava.item30;

import static org.junit.Assert.*;

import java.util.Set;
import org.junit.Test;

public class GenericMethodTest {

  @Test
  public void test() {
    Set<String> a = Set.of("1","2","3");
    Set<String> b = Set.of("a","b","c");
    Set<String> all = GenericMethod.union(a, b);
    System.out.println(all);
  }
}