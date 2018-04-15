package com.nareshit.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/*").permitAll().and()
		.formLogin().loginProcessingUrl("/j_spring_security_check")
		.successForwardUrl("/getDashboard").usernameParameter("j_username")
		.passwordParameter("j_password").and().logout().logoutSuccessUrl("/getHomePage").deleteCookies("JSESSIONID").and().csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	   auth.inMemoryAuthentication().withUser("john").password("john").roles("USER");
	}
}
