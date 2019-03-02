package com.thebudding.book.effectivejava.item13;

import java.util.Comparator;
import java.util.Objects;

public class PhoneNumber implements Cloneable, Comparable<PhoneNumber> {

  private static final Comparator<PhoneNumber> COMPARATOR =
      Comparator.comparingInt((PhoneNumber pn) -> pn.areaCode)
          .thenComparingInt(pn -> pn.prefix)
          .thenComparingInt(pn -> pn.lineNum);

  private final short areaCode, prefix, lineNum;

  public PhoneNumber(int areaCode, int prefix, int lineNum) {
    this.areaCode = rangeCheck(areaCode, 999, "areacode");
    this.prefix = rangeCheck(prefix, 999, "prefix");
    this.lineNum = rangeCheck(lineNum, 9999, "linenum");
  }

  private static short rangeCheck(int val, int max, String arg) {
    if (val < 0 || val > max) {
      throw new IllegalArgumentException(arg + ": " + val);
    }
    return (short) val;
  }

  @Override
  public PhoneNumber clone() {

    try {
      return (PhoneNumber) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    PhoneNumber that = (PhoneNumber) o;
    return areaCode == that.areaCode &&
        prefix == that.prefix &&
        lineNum == that.lineNum;
  }

  @Override
  public int hashCode() {
    return Objects.hash(areaCode, prefix, lineNum);
  }

  @Override
  public String toString() {
    return "PhoneNumber{" +
        "areaCode=" + areaCode +
        ", prefix=" + prefix +
        ", lineNum=" + lineNum +
        '}';
  }

  @Override
  public int compareTo(PhoneNumber that) {
    /*
    int result = Short.compare(areaCode, that.areaCode);
    if (result == 0) {
      result = Short.compare(prefix, that.prefix);
      if (result == 0) {
        result = Short.compare(lineNum, that.lineNum);
      }
    }
    return result;
    */
    return COMPARATOR.compare(that, that);
  }
}
