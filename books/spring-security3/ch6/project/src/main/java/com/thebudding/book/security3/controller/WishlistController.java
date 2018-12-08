package com.thebudding.book.security3.controller;

import com.thebudding.book.security3.data.Item;
import java.util.ArrayList;
import java.util.Collection;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/wishlist/home")
public class WishlistController extends BaseController {
  @RequestMapping(method= RequestMethod.GET)
  public void show() {

  }

  @ModelAttribute("wishlistItems")
  public Collection<Item> getWishlistItems() {
    return new ArrayList<>();
  }
}