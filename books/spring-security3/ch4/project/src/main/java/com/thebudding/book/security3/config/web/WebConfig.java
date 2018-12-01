package com.thebudding.book.security3.config.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan(
    basePackages = {
        "com.thebudding.book.security3.controller"
    },
    excludeFilters={
        @Filter(type = FilterType.ANNOTATION, value = Configuration.class)
    }
)
public class WebConfig extends WebMvcConfigurerAdapter {

  @Bean
  public ViewResolver viewResolver() {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setOrder(1);
    viewResolver.setCache(true);
    viewResolver.setPrefix("/WEB-INF/views/");
    viewResolver.setSuffix(".jsp");
    viewResolver.setContentType("text/html; charset=UTF-8");

    return viewResolver;
  }

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/favicon.ico").addResourceLocations("/WEB-INF/resources/favicon.ico");
    registry.addResourceHandler("/images/**").addResourceLocations("/WEB-INF/resources/images/");
    registry.addResourceHandler("/scripts/**").addResourceLocations("/WEB-INF/resources/scripts/");
    registry.addResourceHandler("/styles/**").addResourceLocations("/WEB-INF/resources/styles/");
  }
}
