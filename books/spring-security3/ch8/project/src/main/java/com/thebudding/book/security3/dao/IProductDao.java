package com.thebudding.book.security3.dao;

import com.thebudding.book.security3.data.Category;
import com.thebudding.book.security3.data.Item;
import java.util.Collection;
import org.springframework.security.access.prepost.PreFilter;

public interface IProductDao {

  Collection<Category> getCategories();

  Category getCategoryByName(String name);

  //@PostFilter("(!filterObject.customersOnly) or (filterObject.customersOnly and hasRole('ROLE_ADMIN'))")
  @PreFilter("(!filterObject.customersOnly) or (filterObject.customersOnly and hasRole('ROLE_ADMIN'))")
  Collection<Category> filterCategories(Collection<Category> categories);

  Collection<Item> getItemsByCategory(Category cat);
}
