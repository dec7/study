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

  public static void zipUncombined() {
    Observable<Long> red = Observable.interval(10, TimeUnit.MILLISECONDS);
    Observable<Long> green = Observable.interval(11, TimeUnit.MILLISECONDS);

    Observable.zip(
            red.timestamp(),
            green.timestamp(),
            (r, g) -> r.getTimestampMillis() - g.getTimestampMillis())
        .forEach(System.out::println);
  }

  public static void combineLatest() {

    Observable.combineLatest(
            Observable.interval(17, TimeUnit.MILLISECONDS).map(x -> "S" + x),
            Observable.interval(10, TimeUnit.MILLISECONDS).map(x -> "F" + x),
            (s, f) -> f + ":" + s)
        .forEach(System.out::println);
  }

  public static void withLatestFrom() {
    Observable<String> fast = Observable.interval(10, TimeUnit.MILLISECONDS).map(x -> "F" + x);
    Observable<String> slow = Observable.interval(17, TimeUnit.MILLISECONDS).map(x -> "S" + x);
    slow.withLatestFrom(fast, (s, f) -> s + ":" + f).forEach(System.out::println);
  }

  public static void withLatestFromWithDelay() {
    Observable<String> fast =
        Observable.interval(10, TimeUnit.MILLISECONDS)
            .map(x -> "F" + x)
            .delay(100, TimeUnit.MILLISECONDS)
            .startWith("FX");
    Observable<String> slow = Observable.interval(17, TimeUnit.MILLISECONDS).map(x -> "S" + x);
    slow.withLatestFrom(fast, (s, f) -> s + ":" + f).forEach(System.out::println);
  }

  public static void amb() {
    Observable.amb(stream(100, 17, "S"), stream(200, 10, "F")).subscribe(System.out::println);
  }

  private static Observable<String> stream(int initialDelay, int interval, String name) {
    return Observable.interval(initialDelay, interval, TimeUnit.MILLISECONDS)
        .map(x -> name + x)
        .doOnSubscribe(() -> System.out.println("Subscribe to " + name))
        .doOnUnsubscribe(() -> System.out.println("UnSubscribe to " + name));
  }
}
