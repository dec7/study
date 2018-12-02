package com.thebudding.book.security3.config;

import com.thebudding.book.security3.config.web.DatabaseConfig;
import com.thebudding.book.security3.service.CustomJdbcDaoImpl;
import com.thebudding.book.security3.service.IPTokenBasedRememberMeServices;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
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
@Import({
    DatabaseConfig.class
})
@ImportResource({"classpath:security-context.xml"})
public class AppConfig {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Autowired
  private AuthenticationManager authenticationManager;

  @Bean
  public IPTokenBasedRememberMeServices ipTokenBasedRememberMeServicesBean() {
    return new IPTokenBasedRememberMeServices("jbcpPetStore", jdbcUserService(),
        "_remember_me", "REMEMBER_ME");
  }

  @Bean
  public UnanimousBased unanimousBased() {
    List<AccessDecisionVoter> list = new ArrayList<>();
    list.add(new RoleVoter());
    list.add(new AuthenticatedVoter());
    return new UnanimousBased(list);
  }

  /*
  @Bean
  public CustomJdbcDaoImpl jdbcUserService() {
    CustomJdbcDaoImpl jdbcUserService = new CustomJdbcDaoImpl();
    jdbcUserService.setDataSource(dataSource);
    jdbcUserService.setJdbcTemplate(jdbcTemplate);
    return jdbcUserService;
  }
  */

  @Bean
  public JdbcUserDetailsManager jdbcUserService() {
    JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager();
    jdbcUserDetailsManager.setDataSource(dataSource);
    jdbcUserDetailsManager.setAuthenticationManager(authenticationManager);
    return jdbcUserDetailsManager;
  }

}
