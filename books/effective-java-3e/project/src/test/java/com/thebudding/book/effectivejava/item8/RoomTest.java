package com.thebudding.book.effectivejava.item8;

import org.junit.Test;

public class RoomTest {

  @Test
  public void adult() throws Exception{
    try (Room myRood = new Room(7)) {
      System.out.println("hello~");
    }
  }

  @Test
  public void teenager() throws Exception{
    new Room(99);
    System.out.println("sure~");
    System.gc();
  }

}