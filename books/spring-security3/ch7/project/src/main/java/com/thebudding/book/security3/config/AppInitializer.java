package com.thebudding.book.security3.config;

import com.thebudding.book.security3.config.web.WebConfig;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class AppInitializer implements WebApplicationInitializer {

  private static final String ALL = "/*";
  private static final String ROOT = "/";
  private static final String NAME = "root";
  private static final String UTF8 = "UTF-8";
  private static final String TRUE = "true";

  private final AnnotationConfigWebApplicationContext rootContext =
      new AnnotationConfigWebApplicationContext();


  public void onStartup(ServletContext servletContext) {
    rootContext.register(AppConfig.class);

    // root application context 라이프사이클 관리
    servletContext.addListener(new ContextLoaderListener(rootContext));
    //servletContext.addListener(new HttpSessionEventPublisher());

    // spring application context의 dispatcher servlet
    AnnotationConfigWebApplicationContext
        dispatcherContext = new AnnotationConfigWebApplicationContext();
    dispatcherContext.register(WebConfig.class);

    // dispatcher servlet 등록
    ServletRegistration.Dynamic dispatcher = servletContext.addServlet(
        NAME, new DispatcherServlet(dispatcherContext));
    dispatcher.setLoadOnStartup(1);
    dispatcher.addMapping(ROOT);
    dispatcher.setInitParameter("throwExceptionIfNoHandlerFound", TRUE);

    // filter
    FilterRegistration charEncodingfilterReg = servletContext.addFilter(
        "characterEncodingFilter", CharacterEncodingFilter.class);
    charEncodingfilterReg.setInitParameter("encoding", UTF8);
    charEncodingfilterReg.setInitParameter("forceEncoding", TRUE);
    charEncodingfilterReg.addMappingForUrlPatterns(null, false, ALL);

    FilterRegistration springSecurityFilterReg = servletContext.addFilter(
        "springSecurityFilterChain", DelegatingFilterProxy.class);
    springSecurityFilterReg.addMappingForUrlPatterns(null, false, ALL);
  }

}