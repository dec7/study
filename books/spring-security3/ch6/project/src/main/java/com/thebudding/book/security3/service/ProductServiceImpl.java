package com.thebudding.book.security3.service;

import com.thebudding.book.security3.dao.IProductDao;
import com.thebudding.book.security3.data.Category;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements IProductService {

  @Autowired
  private IProductDao productDao;

  @Override
  public Collection<Category> getCategories() {
    Collection<Category> unfilteredCategories = productDao.getCategories();
    return productDao.filterCategories(unfilteredCategories);
  }

  @Override
  public Category getCategoryByName(String name) {
    return productDao.getCategoryByName(name);
  }

}
