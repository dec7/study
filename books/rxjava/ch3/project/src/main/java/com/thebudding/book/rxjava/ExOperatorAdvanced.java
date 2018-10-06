package com.thebudding.book.rxjava;

import static rx.Observable.just;

import com.thebudding.book.rxjava.dto.Pair;
import java.math.BigInteger;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import rx.Observable;

public class ExOperatorAdvanced {

  private static final Observable<String> alice = speak(
      "To be, or not to be: that is the question", 10L);
  private static final Observable<String> bob = speak(
      "Through this be madness, yet there is method in't", 90L);
  private static final Observable<String> charles = speak(
      "There are more things in Heaven and Earth, "
          + "Horatio, Than are dreamt of in your philosophy",
      100L);

  public static void scan() {

    Observable.range(2, 100)
        .scan(BigInteger.ONE, (big, cur) -> big.multiply((BigInteger.valueOf(cur))))
        .subscribe(System.out::println);
  }

  public static void concatenate() {
    Observable<Integer> ob1 = Observable.range(1, 5);
    Observable<Integer> ob2 = Observable.range(11, 5);

    Observable.concat(ob1, ob2).subscribe(System.out::println);
  }

  public static void concatenateWithMerge() {

    Observable.merge(
        alice.map(w -> "Alice: " + w),
        bob.map(w -> "Bob: " + w),
        charles.map(w -> "Charles: " + w))
        .subscribe(System.out::println);
  }

  public static void concatenateWithConcat() {


    Observable.concat(
        alice.map(w -> "Alice: " + w),
        bob.map(w -> "Bob: " + w),
        charles.map(w -> "Charles: " + w))
        .subscribe(System.out::println);
  }


  public static void concatenateWithSwitchOnNext() {

    Random rand = new Random();
    Observable<Observable<String>> quotes = just(
        alice.map(w -> "Alice: " + w),
        bob.map(w -> "Bob: " + w),
        charles.map(w -> "Charles: " + w))
        .flatMap(innerObs -> just(innerObs).delay(rand.nextInt(5), TimeUnit.SECONDS));

    Observable.switchOnNext(quotes).subscribe(System.out::println);
  }

  private static Observable<String> speak(String quote, long millisPerChar) {
    String[] token = quote.replaceAll("[:,]]", "").split(" ");
    Observable<String> words = Observable.from(token);
    Observable<Long> absoluteDelay =
        words
            .map(String::length)
            .map(len -> len * millisPerChar)
            .scan((total, current) -> total + current);

    return words
        .zipWith(absoluteDelay.startWith(0L), Pair::of)
        .flatMap(pair -> just(pair.getLeft()).delay(pair.getRight(), TimeUnit.MILLISECONDS));
  }


}
