package com.rocketleague.config;

import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
public class CustomizationForHandling404s implements EmbeddedServletContainerCustomizer {
  public void customize(ConfigurableEmbeddedServletContainer configurableEmbeddedServletContainer) {
    configurableEmbeddedServletContainer.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/"));
  }
}
