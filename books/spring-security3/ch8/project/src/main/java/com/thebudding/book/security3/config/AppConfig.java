package com.thebudding.book.security3.config;

import com.thebudding.book.security3.data.Category;
import com.thebudding.book.security3.security.AclBootstrapBean;
import com.thebudding.book.security3.security.CustomJdbcDaoImpl;
import com.thebudding.book.security3.security.CustomPermission;
import com.thebudding.book.security3.security.CustomPermissionFactory;
import com.thebudding.book.security3.security.DatabasePasswordSecurerBean;
import com.thebudding.book.security3.security.IPRoleAuthenticationFilter;
import com.thebudding.book.security3.security.IPTokenBasedRememberMeServices;
import com.thebudding.book.security3.security.NullAclCache;
import com.thebudding.book.security3.security.RequestHeaderProcessingFilter;
import com.thebudding.book.security3.security.SignedUsernamePasswordAuthenticationProvider;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.access.vote.AuthenticatedVoter;
import org.springframework.security.access.vote.RoleVoter;
import org.springframework.security.access.vote.UnanimousBased;
import org.springframework.security.acls.AclEntryVoter;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.BasePermission;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcAclService;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.model.Permission;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.authentication.event.LoggerListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
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

  @Bean
  public LoggerListener authenticationListener() {
    return new LoggerListener();
  }

  @Bean
  public org.springframework.security.access.event.LoggerListener authorizationListener() {
    return new org.springframework.security.access.event.LoggerListener();
  }

  @Bean
  public AffirmativeBased aclDecisionManager() {
    List<AccessDecisionVoter> list = new ArrayList<>();
    list.add(categoryReadVoter());
    list.add(adminResourceReadVoter());

    AffirmativeBased affirmativeBased = new AffirmativeBased();
    affirmativeBased.setDecisionVoters(list);

    return affirmativeBased;
  }

  @Bean
  public AclEntryVoter categoryReadVoter() {
    AclEntryVoter voter = new AclEntryVoter(aclService(), "VOTE_CATEGORY_READ",
        new Permission[]{BasePermission.READ});
    voter.setProcessDomainObjectClass(Category.class);
    return voter;
  }

  @Bean
  public JdbcAclService aclService() {
    return new JdbcAclService(dataSource, lookupStrategy());
  }

  @Bean
  public BasicLookupStrategy lookupStrategy() {
    BasicLookupStrategy lookupStrategy = new BasicLookupStrategy(dataSource, ehCacheAclCache(),
        aclAuthorizationStrategy(), aclAuditLogger());
    lookupStrategy.setPermissionFactory(customPermissionFactory());

    return lookupStrategy;
  }

  @Bean
  public CustomPermissionFactory customPermissionFactory() {
    return new CustomPermissionFactory();
  }

  @Bean
  public AclEntryVoter adminResourceReadVoter() {
    AclEntryVoter aclEntryVoter = new AclEntryVoter(
        aclService(), "VOTE_ADMIN_READ",
        new Permission[]{CustomPermission.ADMIN_READ});
    aclEntryVoter.setProcessDomainObjectClass(Category.class);
    return aclEntryVoter;
  }

  @Bean
  public ConsoleAuditLogger aclAuditLogger() {
    return new ConsoleAuditLogger();
  }

  @Bean
  public AclAuthorizationStrategyImpl aclAuthorizationStrategy() {
    GrantedAuthority[] authorities =
        { aclAdminAuthority(), aclAdminAuthority(), aclAdminAuthority() };
    return new AclAuthorizationStrategyImpl(authorities);
  }

  @Bean
  public GrantedAuthorityImpl aclAdminAuthority() {
    return new GrantedAuthorityImpl("ROLE_ADMIN");
  }

  @Bean
  public JdbcMutableAclService mutableAclService() {
    return new JdbcMutableAclService(dataSource, lookupStrategy(), ehCacheAclCache());
  }

  @Bean
  public AclBootstrapBean aclBootstrapBean() {
    return new AclBootstrapBean();
  }

  @Bean
  public EhCacheManagerFactoryBean ehCacheManagerBean() {
    return new EhCacheManagerFactoryBean();
  }

  @Bean
  public EhCacheFactoryBean ehCacheFactoryBean() {
    EhCacheFactoryBean factoryBean = new EhCacheFactoryBean();
    factoryBean.setCacheManager(ehCacheManagerBean().getObject());
    factoryBean.setCacheName("springAclCacheRegion");
    return factoryBean;
  }

  @Bean
  public EhCacheBasedAclCache ehCacheAclCache() {
    return new EhCacheBasedAclCache(ehCacheFactoryBean().getObject());
  }

}
