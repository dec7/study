package com.thebudding.book.security3.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;

public interface IUserService {

  @PreAuthorize("hasRole('ROLE_USER')")
  // @PreAuthorize("hasRole('ROLE_ADMIN')") 403 error
  void changePassword(String username, String password);

  @Transactional
  void createUser(String userId, String unused, Object o);
}
