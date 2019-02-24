package com.thebudding.book.security3.service;

import com.thebudding.book.security3.security.IChangePassword;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

  @Autowired
  @Qualifier("jdbcUserService")
  private IChangePassword changePassword;

  @Override
  public void changePassword(String username, String password) {
    changePassword.changePassword(username, password);
  }

  @Override
  public void createUser(String userId, String unused, Object o) {
    
  }

}
