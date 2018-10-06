package com.thebudding.book.rxjava.dto;

public class Pair<L,R> {

  private L left;

  private R right;

  public L getLeft() {
    return left;
  }

  public void setLeft(L left) {
    this.left = left;
  }

  public R getRight() {
    return right;
  }

  public void setRight(R right) {
    this.right = right;
  }

  public static <L,R> Pair<L,R> of (L left, R right) {
    Pair<L,R> pair = new Pair<>();
    pair.setLeft(left);
    pair.setRight(right);

    return pair;
  }
}
