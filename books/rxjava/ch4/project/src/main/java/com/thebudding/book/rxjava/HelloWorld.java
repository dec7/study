package com.thebudding.book.rxjava;

import rx.Observable;

public class HelloWorld {

  public static void main(String[] args) throws Exception {

    ExApplication.collectionToObservable();

    ExApplication.blockingObservable();

    ExApplication.lazyCollectionToObservable();


  }
}
