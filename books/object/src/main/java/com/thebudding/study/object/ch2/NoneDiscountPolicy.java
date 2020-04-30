package com.thebudding.study.object.ch2;

public class NoneDiscountPolicy implements DiscountPolicy {

  @Override
  public Money calculateDiscountAmount(Screening screening) {
    return Money.ZERO;
  }
}
