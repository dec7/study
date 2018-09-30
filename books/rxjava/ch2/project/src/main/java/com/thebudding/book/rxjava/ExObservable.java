package com.thebudding.book.rxjava;

import java.util.ArrayList;
import rx.Observable;

public class ExObservable {

  public static void method() {
    Observable.just("");
//    Observable.fromArray("","");
//    Observable.fromIterable(new ArrayList<String>());
    Observable.from(new ArrayList<String>().toArray());
    Observable.range(0, 1);
    Observable.empty();
    Observable.never();
    Observable.error(new Exception());
  }

  public static void create() {
    log("Before");
    Observable.range(5,3).subscribe(ExObservable::log);
    log("After");
  }

  private static void log(Object msg) {
    System.out.println(Thread.currentThread().getName() + ":" + msg);
  }

  public static void createAdvanced() {
    Observable<Integer> ints = Observable.create(s -> {
      log("Create");
      s.onNext(5);
      s.onNext(6);
      s.onNext(7);
      s.onCompleted();
      log("Completed");
    });

    log("Starting");
    ints.subscribe(i -> log("Element: " + i));
    log("Exit");
  }
}
