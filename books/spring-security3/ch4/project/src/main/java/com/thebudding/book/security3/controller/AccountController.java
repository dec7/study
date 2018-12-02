package com.thebudding.book.security3.controller;

import com.thebudding.book.security3.service.IChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import sun.plugin.liveconnect.SecurityContextHelper;

@Controller
public class AccountController {

//  @Autowired
//  private IChangePassword changePasswordDao;

  @Autowired
  private UserDetailsManager jdbcUserService;

  @RequestMapping("/account/home")
  public void accountHome() {
  }

  @RequestMapping(value="/account/changePassword",method=RequestMethod.GET)
  public void showChangePasswordPage() {
  }

  @RequestMapping(value="/account/changePassword",method=RequestMethod.POST)
  public String submitChangePasswordPage(
      @RequestParam("oldPassword") String oldPassword,
      @RequestParam("password") String newPassword) {
//    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//    String username = principal.toString();
//    if (principal instanceof UserDetails) {
//      username = ((UserDetails) principal).getUsername();
//    }
//    changePasswordDao.changePassword(username, newPassword);
    jdbcUserService.changePassword(oldPassword, newPassword);
    SecurityContextHolder.clearContext();

    return "redirect:home";
  }

}
