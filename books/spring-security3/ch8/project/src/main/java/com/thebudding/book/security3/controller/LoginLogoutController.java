package com.thebudding.book.security3.controller;

import com.thebudding.book.security3.service.IUserService;
import java.io.PrintWriter;
import java.io.StringWriter;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginLogoutController extends BaseController {

  @Autowired
  private IUserService userService;

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public void home() {

  }

  @RequestMapping(value = "/registrationOpenId", method = RequestMethod.GET)
  public String registrationOpenId(HttpServletRequest request) {
    String userId = (String) request.getSession(true).getAttribute("USER_OPENID_CREDENTIAL");
    if (userId != null) {
      userService.createUser(userId, "unused", null);
      setMessage(request, "Your account has been created. Please log in using your OpenID.");
      return "redirect:login";
    } else {
      setMessage(request, "Please register using your OpenID.");
      return "redirect:registration";
    }
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
