package com.thebudding.book.security3.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController extends BaseController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public void home() {

  }

  @RequestMapping(method = RequestMethod.GET, value="/accessDenied")
  public void accessDenied(ModelMap model, HttpServletRequest request) {
    AccessDeniedException ex = (AccessDeniedException) request.getAttribute(
        "SPRING_SECURITY_403_EXCEPTION");

    StringWriter sw = new StringWriter();
    model.addAttribute("errorDetails", ex.getMessage());
    ex.printStackTrace(new PrintWriter(sw));
    model.addAttribute("errorTrace", sw.toString());
  }

}
