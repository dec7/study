package com.thebudding.book.effectivejava.item13;

import static org.junit.Assert.*;

import org.junit.Test;

public class PhoneNumberTest {

  @Test
  public void test_clone() {
    PhoneNumber pn = new PhoneNumber(123, 456, 7890);

    PhoneNumber pn1 = pn.clone();
    System.out.println(pn);
    System.out.println(pn1);
  }

}