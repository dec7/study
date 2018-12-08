package com.thebudding.book.security3.service;

import com.thebudding.book.security3.data.Category;
import java.util.Collection;

public interface IProductService {

  Collection<Category> getCategories();

  Category getCategoryByName(String name);

}
