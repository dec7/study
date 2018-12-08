package com.thebudding.book.security3.controller;

import com.thebudding.book.security3.data.Category;
import com.thebudding.book.security3.service.IProductService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController extends BaseController {
  @Autowired
  private IProductService productService;

  @ModelAttribute("categories")
  public Collection<Category> getCategories() {
    return productService.getCategories();
  }

  @RequestMapping(value = {"","/home"})
  public String home() {
    return "home";
  }
}
