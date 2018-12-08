package com.thebudding.book.security3.security;

import java.sql.ResultSet;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
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

  @Override
  protected UserDetails createUserDetails(String username, UserDetails userFromUserQuery,
      List<GrantedAuthority> combinedAuthorities) {
    String resultUsername = userFromUserQuery.getUsername();
    if (!isUsernameBasedPrimaryKey()) {
      resultUsername = username;
    }

    return new SaltUser(
        resultUsername,
        userFromUserQuery.getPassword(),
        userFromUserQuery.isEnabled(),
        true, true, true,
        combinedAuthorities,
        ((SaltUser) userFromUserQuery).getSalt());
  }

  @Override
  protected List<UserDetails> loadUsersByUsername(String username) {
    return getJdbcTemplate().query(
        getUsersByUsernameQuery(),
        new String[]{username},
        (ResultSet rs, int rowNum) -> {
          String name = rs.getString(1);
          String password = rs.getString(2);
          boolean enabled = rs.getBoolean(3);
          String salt = rs.getString(4);

          return new SaltUser(
              name, password, enabled,
              true, true, true,
              AuthorityUtils.NO_AUTHORITIES, salt);
        }
    );
  }
}
