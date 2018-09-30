package com.thebudding.book.rxjava;

import io.reactivex.Observable;
import java.util.ArrayList;

public class ExObservable {

  public static void method() {
    Observable.just("");
    Observable.fromArray("","");
    Observable.fromIterable(new ArrayList<String>());
    Observable.range(0, 1);
    Observable.empty();
    Observable.never();
    Observable.error(new Exception());
  }

  public static void create() {
    log("Before");
    io.reactivex.Observable.range(5,3).subscribe(ExObservable::log);
    log("After");
  }

  private static void log(Object msg) {
    System.out.println(Thread.currentThread().getName() + ":" + msg);
  }

}
