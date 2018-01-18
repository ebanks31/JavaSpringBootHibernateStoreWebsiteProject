package com.ebanks.springapp.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource({ "WebSecurityConfig.xml" })
public class SecSecurityConfig {
   public SecSecurityConfig() {
      super();
   }
}