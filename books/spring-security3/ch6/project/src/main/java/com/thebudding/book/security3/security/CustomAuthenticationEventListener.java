package com.thebudding.book.security3.security;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationEventListener
    implements ApplicationListener<AbstractAuthenticationEvent> {

  @Override
  public void onApplicationEvent(AbstractAuthenticationEvent event) {
    System.out.println("received event of type: " + event.getClass().getName()
        + ": " + event.toString());
  }
}
