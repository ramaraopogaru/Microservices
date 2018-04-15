package com.dotridge.config;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class SecurityInitializer extends AbstractSecurityWebApplicationInitializer {
  public SecurityInitializer(){
	  super(AppSecurityConfig.class);
  }
}
