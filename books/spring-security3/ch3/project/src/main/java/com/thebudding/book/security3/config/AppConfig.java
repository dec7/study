package com.thebudding.book.security3.config;

import com.thebudding.book.security3.service.IPTokenBasedRememberMeServices;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(
    basePackages = {
        "com.thebudding.book.security3"
    },
    excludeFilters={
        @Filter(
            type = FilterType.ANNOTATION,
            value = {
                Configuration.class,
                Controller.class
            })
    }
)
@ImportResource({"classpath:security-context.xml"})
public class AppConfig {

  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public IPTokenBasedRememberMeServices ipTokenBasedRememberMeServicesBean() {
    return new IPTokenBasedRememberMeServices("jbcpPetStore", userDetailsService);
  }

  @Bean
  public UnanimousBased unanimousBased() {
    List<AccessDecisionVoter> list = new ArrayList<>();
    list.add(new RoleVoter());
    list.add(new AuthenticatedVoter());
    return new UnanimousBased(list);
  }
}
