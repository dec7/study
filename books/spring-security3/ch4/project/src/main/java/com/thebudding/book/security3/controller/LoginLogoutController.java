package com.thebudding.book.security3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public void home() {

  }

}
