package com.thebudding.study.object.ch4;

import java.time.LocalDateTime;

public class Screening {

  private Movie movie;
  private int sequence;
  private LocalDateTime whenScreended;

  public Screening(Movie movie, int sequence, LocalDateTime whenScreended) {
    this.movie = movie;
    this.sequence = sequence;
    this.whenScreended = whenScreended;
  }

  public Money calculateFee(int audienceCount) {
    switch (movie.getMovieType()) {
      case AMOUNT_DISCOUNT:
        if (movie.isDiscountable(whenScreended, sequence)) {
          return movie.calculateAmountDiscountedFee().times(audienceCount);
        }
        break;
      case PERCENT_DISCOUNT:
        if (movie.isDiscountable(whenScreended, sequence)) {
          return movie.calculatePercentDiscountedFee().times(audienceCount);
        }
        break;
      case NONE_DISCOUNT:
        return movie.calculateNoneDiscountedFee().times(audienceCount);
    }
    return movie.calculateNoneDiscountedFee().times(audienceCount);
  }
}
