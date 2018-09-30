package com.thebudding.book.rxjava;

public class HelloWorld {

  public static void main(String[] args) throws Exception {

    ExObservable.method();
    System.out.println("==============\n\n");


    ExObservable.create();
    System.out.println("==============\n\n");


    ExObservable.createAdvanced();
    System.out.println("==============\n\n");


    ExObservable.publishToAll();
    System.out.println("==============\n\n");

    //ExObservable.infiniteStream();
    System.out.println("==============\n\n");

    ExObservable.processError();
    System.out.println("==============\n\n");

    ExObservable.timer();
    System.out.println("==============\n\n");

    Thread.sleep(12000);
  }


}
