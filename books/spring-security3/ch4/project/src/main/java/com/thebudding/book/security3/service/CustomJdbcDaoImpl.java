package com.thebudding.book.security3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.jdbc.JdbcDaoImpl;

public class CustomJdbcDaoImpl extends JdbcDaoImpl implements IChangePassword {

  @Autowired
  private ShaPasswordEncoder passwordEncoder;

  @Autowired
  private ReflectionSaltSource saltSource;

  @Override
  public void changePassword(String username, String password) {
    UserDetails user = loadUserByUsername(username);
    String encodedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));
    getJdbcTemplate().update(
        "UPDATE USERS SET PASSWORD = ? WHERE USERNAME = ?",
        encodedPassword, username);
  }
}
