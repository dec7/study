package com.thebudding.book.rxjava;

public class HelloWorld {

  public static void main(String[] args) throws Exception {
/*
    ExOperator.flatMapNonSequentialInfinity();
    System.out.println("==============\n\n");

    ExOperator.flatMapNonSequential();
    System.out.println("==============\n\n");

    ExOperator.concatMap();
    System.out.println("==============\n\n");

    ExOperator.zipUncombined();
    System.out.println("==============\n\n");

    ExOperator.combineLatest();
*/
//    ExOperator.withLatestFrom();
//    System.out.println("==============\n\n");
    ExOperator.withLatestFromWithDelay();

    Thread.sleep(5000);
  }


}
