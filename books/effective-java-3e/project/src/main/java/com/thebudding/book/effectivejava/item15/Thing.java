package com.thebudding.book.effectivejava.item15;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Thing {

  // 보안 허점
  public static final Thing[] VALUES = {  };

  private static final Thing[] PRIVATE_VALUES = {  };

  // Solution.1
  public static final List<Thing> VALUES1 =
      Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

  // Solution.2
  public static final Thing[] values() {
    return PRIVATE_VALUES.clone();
  }




}
