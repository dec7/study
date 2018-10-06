package com.thebudding.book.rxjava;

import rx.Observable;

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

    //    ExOperator.withLatestFrom();
    //    System.out.println("==============\n\n");
        ExOperator.withLatestFromWithDelay();

        ExOperator.amb();

        ExOperatorAdvanced.scan();

    ExOperatorAdvanced.concatenate();
    System.out.println("==============\n\n");

    ExOperatorAdvanced.concatenateWithMerge();

    ExOperatorAdvanced.concatenateWithConcat();
*/
    ExOperatorAdvanced.concatenateWithSwitchOnNext();


    Thread.sleep(10000);
  }
}
