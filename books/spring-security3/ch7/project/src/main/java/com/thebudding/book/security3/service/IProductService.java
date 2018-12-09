package com.thebudding.book.security3.service;

import com.thebudding.book.security3.data.Category;
import com.thebudding.book.security3.data.Item;
import java.util.Collection;
import org.springframework.security.access.annotation.Secured;

public interface IProductService {

  Collection<Category> getCategories();

  Category getCategoryByName(String name);

  @Secured("VOTE_CATEGORY_READ")
  Collection<Item> getItemsByCategory(Category category);

}
