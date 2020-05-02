package com.thebudding.study.object.ch4;

import java.time.LocalDateTime;

public class Screening {

  private Movie movie;
  private int sequence;
  private LocalDateTime whenScreended;

  public Movie getMovie() {
    return movie;
  }

  public void setMovie(Movie movie) {
    this.movie = movie;
  }

  public int getSequence() {
    return sequence;
  }

  public void setSequence(int sequence) {
    this.sequence = sequence;
  }

  public LocalDateTime getWhenScreended() {
    return whenScreended;
  }

  public void setWhenScreended(LocalDateTime whenScreended) {
    this.whenScreended = whenScreended;
  }
}
