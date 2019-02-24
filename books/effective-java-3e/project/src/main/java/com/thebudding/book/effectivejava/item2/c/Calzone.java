package com.thebudding.book.effectivejava.item2.c;

public class Calzone extends Pizza {
  private final boolean sauseInside;

  public static class Builder extends Pizza.Builder<Builder> {
    private boolean sauseInside = false;

    public Builder sauceInside() {
      sauseInside = true;
      return this;
    }

    @Override
    public Calzone build() {
      return new Calzone(this);
    }

    @Override
    protected Builder self() {
      return this;
    }
  }

  private Calzone(Builder builder) {
    super(builder);
    sauseInside = builder.sauseInside;
  }
}
