package com.thebudding.book.security3.controller;

import com.thebudding.book.security3.data.Category;
import com.thebudding.book.security3.service.IProductService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController extends BaseController {
  @Autowired
  private IProductService productService;

//  @ModelAttribute("categories")
//  public Collection<Category> getCategories() {
//    return productService.getCategories();
//  }

  @RequestMapping(value = {"","/home"})
  public String home(Model model) {
    model.addAttribute("categories", productService.getCategories());
    return "home";
  }

  @RequestMapping(method= RequestMethod.GET,value="/category")
  public void viewCategory(@RequestParam("id") String categoryName, ModelMap model) {
    Category cat = productService.getCategoryByName(categoryName);
    model.addAttribute("items", productService.getItemsByCategory(cat));
  }
}
