package com.thebudding.book.effectivejava.item33;

import static org.junit.Assert.*;

import org.junit.Test;

public class FavoritesTest {

  @Test
  public void test() {
    Favorites f = new Favorites();

    f.putFavorite(String.class, "Java");
    f.putFavorite(Integer.class, 0xcafebabe);
    f.putFavorite(Class.class, Favorites.class);

    String favoriteString = f.getFavorie(String.class);
    int favoriteInteger = f.getFavorie(Integer.class);
    Class<?> favoriteClass = f.getFavorie(Class.class);

    System.out.printf("%s %x %s%n", favoriteString, favoriteInteger, favoriteClass.getName());
  }

}