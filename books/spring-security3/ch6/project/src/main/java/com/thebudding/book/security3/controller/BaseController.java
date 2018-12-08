package com.thebudding.book.security3.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

public class BaseController {

  protected Authentication getAuthentication() {
    return SecurityContextHolder.getContext().getAuthentication();
  }

  @ModelAttribute("showLoginLink")
  public boolean getShowLoginLink() {
    for (GrantedAuthority authority : getAuthentication().getAuthorities()) {
      if(authority.getAuthority().equals("ROLE_USER")) {
        return false;
      }
    }
    return true;
  }

}
