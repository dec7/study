package com.thebudding.book.security3.security;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.openid.OpenIDAuthenticationStatus;
import org.springframework.security.openid.OpenIDAuthenticationToken;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

public class OpenIDAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
      AuthenticationException exception) throws IOException, ServletException {

    if (exception instanceof UsernameNotFoundException
        && exception.getAuthentication() instanceof OpenIDAuthenticationToken
        && ((OpenIDAuthenticationToken) exception.getAuthentication()).getStatus()
        .equals(OpenIDAuthenticationStatus.SUCCESS)) {

      DefaultRedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
      request.getSession(true).setAttribute(
          "USER_OPENID_CREDENTIAL",
          ((UsernameNotFoundException) exception).getExtraInformation());

      redirectStrategy.sendRedirect(request, response, "/registrationOpenId");
    } else {
      super.onAuthenticationFailure(request, response, exception);
    }
  }
}
