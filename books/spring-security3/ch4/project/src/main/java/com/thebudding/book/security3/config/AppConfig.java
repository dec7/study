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
@Import({
    DatabaseConfig.class
})
@ImportResource({"classpath:security-context.xml"})
public class AppConfig {

  @Autowired
  private DataSource dataSource;

  @Autowired
  private JdbcTemplate jdbcTemplate;

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
  public UserDetailsService jdbcUserService() {
    CustomJdbcDaoImpl jdbcUserService = new CustomJdbcDaoImpl();
    jdbcUserService.setDataSource(dataSource);
    jdbcUserService.setJdbcTemplate(jdbcTemplate);
    jdbcUserService.setEnableAuthorities(true);
    jdbcUserService.setEnableGroups(true);

    // customized
    jdbcUserService.setUsersByUsernameQuery(
        "SELECT LOGIN, PASSWORD, 1 FROM USER_INFO WHERE LOGIN = ?");
    jdbcUserService.setGroupAuthoritiesByUsernameQuery(
        "SELECT G.GROUP_ID, G.GROUP_NAME, P.NAME, "
            + "FROM USER_INFO U "
            + "JOIN USER_GROUP UG on U.USER_INFO_ID = UG.USER_INFO_ID "
            + "JOIN GROUP G ON UG.GROUP_ID = G.GROUP_ID "
            + "JOIN GROUP_PERMISSION GP ON G.GROUP_ID = GP.GROUP_ID "
            + "JOIN PERMISSION P ON GP.PERMISSION_ID = P.PERMISSION_ID "
            + "WHERE U.LOGIN = ?"
    );
    return jdbcUserService;
  }



}
