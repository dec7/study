package com.thebudding.book.security3.service;

import java.sql.ResultSet;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

public class DatabasePasswordSecurerBean extends JdbcDaoSupport {

  @Autowired
  private ShaPasswordEncoder passwordEncoder;

  @PostConstruct
  private void init() {
    secureDatabase();
  }

  public void secureDatabase() {
    getJdbcTemplate().query("select username, password from users", (ResultSet rs) -> {
      String username = rs.getString(1);
      String password = rs.getString(2);
      String encodedPassword = passwordEncoder.encodePassword(password, null);
      getJdbcTemplate().update(
          "update users set password = ? where username = ?", encodedPassword, username);
      logger.debug("Updating password for username: " + username + " to: " + encodedPassword);
    });
  }

}
