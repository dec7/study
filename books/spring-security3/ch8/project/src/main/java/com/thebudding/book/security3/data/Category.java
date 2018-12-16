package com.thebudding.book.security3.data;

public class Category extends BaseModelObject {
  private String name;
  private boolean customersOnly;

  public Category(String name, boolean customersOnly) {
    super();

    this.name = name;
    this.customersOnly = customersOnly;
  }

  public String getName() {
    return name;
  }

  public boolean isCustomersOnly() {
    return customersOnly;
  }
}

