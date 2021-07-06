package com.wccgroup.wccgroupjava;

import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig
    extends WebSecurityConfigurerAdapter implements ApplicationContextAware {

	/**
	 *  web security default cross site request forgery (csrf) enabled. 
	 */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http
             .csrf().disable()
//             .headers().frameOptions().disable() 
             .authorizeRequests().anyRequest().authenticated()
             .and()
             .httpBasic().and()
             .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
        }
      
}
