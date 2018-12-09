package com.thebudding.book.security3.controller;

import com.thebudding.book.security3.service.IUserService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AccountController extends BaseController {

  @Autowired
  private IUserService iUserService;

  @Autowired
  private SessionRegistry sessionRegistry;

  @RequestMapping("/account/home")
  public void accountHome() {
  }

  @RequestMapping(value="/account/changePassword",method=RequestMethod.GET)
  public void showChangePasswordPage() {
  }

  @RequestMapping(value="/account/changePassword",method=RequestMethod.POST)
  public String submitChangePasswordPage(@RequestParam("password") String newPassword) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String username = principal.toString();
    if (principal instanceof UserDetails) {
      username = ((UserDetails) principal).getUsername();
    }
    iUserService.changePassword(username, newPassword);
    SecurityContextHolder.clearContext();

    return "redirect:home";
  }

  @RequestMapping(value="/account/listActiveUsers")
  public void listActiveUsers(Model model) {
    Map<Object, Date> lastActivityDates = new HashMap<>();
    for (Object principal: sessionRegistry.getAllPrincipals()) {
      for (SessionInformation session : sessionRegistry.getAllSessions(principal, false)) {
        if (lastActivityDates.get(principal) == null) {
          lastActivityDates.put(principal, session.getLastRequest());
        } else {
          Date prevLastRequest = lastActivityDates.get(principal);
          if (session.getLastRequest().after(prevLastRequest)) {
            lastActivityDates.put(principal, session.getLastRequest());
          }
        }
      }
    }
    model.addAttribute("activeUsers", lastActivityDates);
  }

}
