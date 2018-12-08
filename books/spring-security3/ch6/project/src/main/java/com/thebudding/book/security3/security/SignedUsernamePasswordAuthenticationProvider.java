package com.thebudding.book.security3.security;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

public class SignedUsernamePasswordAuthenticationProvider extends DaoAuthenticationProvider {

  @Override
  public boolean supports(Class<?> authentication) {
    return SignedUsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
      UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
    super.additionalAuthenticationChecks(userDetails, authentication);

    SignedUsernamePasswordAuthenticationToken signedToken =
        (SignedUsernamePasswordAuthenticationToken) authentication;

    if (signedToken.getRequestSignature() == null) {
      throw new BadCredentialsException(
          messages.getMessage("SignedUsernamePasswordAuthenticationProvider.missingSignature",
              "Missing request signature"),
          userDetails);
    }

    /*
    if (!signedToken.getRequestSignature().equals(calculateExpectedSignature(signedToken))) {
      throw new BadCredentialsException(
          messages.getMessage("SignedUsernamePasswordAuthenticationProvider.badSignature",
              "Invalid request signature"),
          userDetails);
    }
    */
  }

  private String calculateExpectedSignature(SignedUsernamePasswordAuthenticationToken signedToken) {
    return signedToken.getPrincipal() + "|+|" + signedToken.getCredentials();
  }
}
