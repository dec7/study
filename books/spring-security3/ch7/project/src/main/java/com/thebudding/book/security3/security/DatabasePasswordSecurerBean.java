package com.thebudding.book.security3.security;

import java.sql.ResultSet;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public class DatabasePasswordSecurerBean extends JdbcDaoSupport {

  @Autowired
  private ShaPasswordEncoder passwordEncoder;

  @Autowired
  private ReflectionSaltSource saltSource;

  @Autowired
  private UserDetailsService userDetailsService;

  @PostConstruct
  private void init() {
    secureDatabase();
  }

  public void secureDatabase() {
    getJdbcTemplate().query("select username, password from users", (ResultSet rs) -> {
      String username = rs.getString(1);
      String password = rs.getString(2);

      UserDetails user = userDetailsService.loadUserByUsername(username);
      String encodedPassword = passwordEncoder.encodePassword(password, saltSource.getSalt(user));
      getJdbcTemplate().update(
          "update users set password = ? where username = ?", encodedPassword, username);
      logger.debug("Updating password for username: " + username + " to: " + encodedPassword);
    });
  }

}
