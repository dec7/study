package com.thebudding.book.effectivejava.item52;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class CollectionClassifier {

  public static String classify(Set<?> s) {
    return "set";
  }

  public static String classify(List<?> list) {
    return "list";
  }

  public static String classify(Collection<?> c) {
    return "etc";
  }

}
