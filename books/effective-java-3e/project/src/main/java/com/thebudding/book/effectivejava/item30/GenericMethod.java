package com.thebudding.book.effectivejava.item30;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.function.UnaryOperator;

public class GenericMethod {

  private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

  public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
    Set<E> result = new HashSet<>(s1);
    result.addAll(s2);
    return result;
  }

  @SuppressWarnings("unchecked")
  public static <T> UnaryOperator<T> identityFunction() {
    return (UnaryOperator<T>) IDENTITY_FN;
  }


  public static <E extends Comparable<E>> E max(Collection<E> c) {
    if (c.isEmpty()) {
      throw new IllegalArgumentException("Collection is empty.");
    }

    E result = null;
    for (E e : c) {
      if (result == null || e.compareTo(result) > 0) {
        result = Objects.requireNonNull(e);
      }
    }
    return result;
  }

}
