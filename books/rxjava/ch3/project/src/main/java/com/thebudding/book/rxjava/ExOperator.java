package com.thebudding.book.rxjava;

import java.time.DayOfWeek;
import java.util.concurrent.TimeUnit;
import rx.Observable;

public class ExOperator {

  public static void flatMapNonSequential() {

    Observable.just(3L, 1L)
        .flatMap(x -> Observable.just(x).delay(x, TimeUnit.SECONDS))
        .subscribe(System.out::println);
  }

  public static void flatMapNonSequentialInfinity() {
    Observable.just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
        .flatMap(ExOperator::loadRecordsFor)
        .subscribe(
            i -> {
              System.out.println(Thread.currentThread().getName() + " - " + i);
            });
  }

  private static Observable<String> loadRecordsFor(DayOfWeek dow) {

    switch (dow) {
      case SUNDAY:
        return Observable.interval(90, TimeUnit.MILLISECONDS).take(5).map(i -> "Sun-" + i);
      case MONDAY:
        return Observable.interval(65, TimeUnit.MILLISECONDS).take(5).map(i -> "Mon-" + i);
      default:
        return Observable.empty();
    }
  }

  public static void concatMap() {
    Observable.just(DayOfWeek.SUNDAY, DayOfWeek.MONDAY)
        .concatMap(ExOperator::loadRecordsFor)
        .subscribe(
            i -> {
              System.out.println(Thread.currentThread().getName() + " - " + i);
            });
  }
}
