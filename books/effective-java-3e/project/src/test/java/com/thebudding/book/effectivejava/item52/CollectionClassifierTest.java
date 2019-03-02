package com.thebudding.book.effectivejava.item52;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import org.junit.Test;

public class CollectionClassifierTest {

  @Test
  public void test() {
    Collection<?>[] collections = {
        new HashSet<String>(),
        new ArrayList<BigInteger>(),
        new HashMap<String, String>().values()
    };

    for (Collection<?> c : collections) {
      System.out.println(CollectionClassifier.classify(c));
    }
  }

}