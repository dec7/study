package com.thebudding.book.security3.config;

import com.thebudding.book.security3.config.web.DatabaseConfig;
import com.thebudding.book.security3.security.CustomJdbcDaoImpl;
import com.thebudding.book.security3.security.DatabasePasswordSecurerBean;
import com.thebudding.book.security3.security.IPRoleAuthenticationFilter;
import com.thebudding.book.security3.security.IPTokenBasedRememberMeServices;
import com.thebudding.book.security3.security.RequestHeaderProcessingFilter;
import com.thebudding.book.security3.security.SignedUsernamePasswordAuthenticationProvider;
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
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
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

  @Bean
  public CustomJdbcDaoImpl jdbcUserService() {
    CustomJdbcDaoImpl jdbcUserService = new CustomJdbcDaoImpl();
    jdbcUserService.setDataSource(dataSource);
    jdbcUserService.setJdbcTemplate(jdbcTemplate);
    jdbcUserService.setEnableAuthorities(true);
    jdbcUserService.setEnableGroups(true);
    jdbcUserService.setUsersByUsernameQuery(
        "SELECT username, password, enabled, salt FROM users where username = ?");
    return jdbcUserService;
  }

  @Bean
  public IPRoleAuthenticationFilter ipFilter() {
    List<String> ipAddresses = new ArrayList<>();
    ipAddresses.add("127.0.0.1");

    IPRoleAuthenticationFilter filter = new IPRoleAuthenticationFilter();
    filter.setTargetRole("ROLE_ADMIN");
    filter.setAllowedIPAddressed(ipAddresses);
    return filter;
  }

  @Bean
  public SignedUsernamePasswordAuthenticationProvider signedRequestAuthenticationProvider() {
    SignedUsernamePasswordAuthenticationProvider provider =
        new SignedUsernamePasswordAuthenticationProvider();
    provider.setPasswordEncoder(passwordEncoder());
    provider.setSaltSource(saltSource());
    provider.setUserDetailsService(jdbcUserService());
    return provider;
  }

  @Bean
  public RequestHeaderProcessingFilter requestHeaderFilter() {
    RequestHeaderProcessingFilter filter = new RequestHeaderProcessingFilter();
    filter.setAuthenticationManager(authenticationManager);
    return filter;
  }

  @Bean
  public ShaPasswordEncoder passwordEncoder() {
    return new ShaPasswordEncoder();
  }

  @Bean
  public DatabasePasswordSecurerBean databasePasswordSecurerBean() {
    DatabasePasswordSecurerBean databasePasswordSecurerBean = new DatabasePasswordSecurerBean();
    databasePasswordSecurerBean.setDataSource(dataSource);
    return databasePasswordSecurerBean;
  }

  @Bean
  public ReflectionSaltSource saltSource() {
    ReflectionSaltSource saltSource = new ReflectionSaltSource();
    saltSource.setUserPropertyToUse("username");
    return saltSource;
  }



}
