package com.thebudding.book.security3.service;

import javax.annotation.security.RolesAllowed;
import org.springframework.security.access.prepost.PreAuthorize;

public interface IUserService {

  @RolesAllowed("ROLE_USER")
  //@RolesAllowed({"ROLE_USER", "ROLE_ADMIN"})
  void changePassword(String username, String password);

}
