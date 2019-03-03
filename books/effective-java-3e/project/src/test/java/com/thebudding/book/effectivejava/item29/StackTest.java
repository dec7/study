package com.thebudding.book.effectivejava.item29;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.*;

import org.junit.Test;

public class StackTest {

  @Test
  public void test() {
    Stack<Momo> stack = new Stack<>();
    assertThat(stack.isEmpty(), equalTo(true));

    stack.push(new Momo());
    assertThat(stack.isEmpty(), equalTo(false));

    stack.pop();
    assertThat(stack.isEmpty(), equalTo(true));

  }

}