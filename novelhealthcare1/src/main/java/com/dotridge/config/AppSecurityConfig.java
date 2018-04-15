package com.dotridge.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired 
	CutomAuthenticationProvider authProvider;

	@Autowired
	CustomPermissionEvaluator permEval;
	
	
	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/windowLogin").hasRole("doctor")
	      .and()
	      .httpBasic();
		http.httpBasic();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("admin")
		.password("admin")
		.authorities(new SimpleGrantedAuthority("doctor"));
	}*/
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("aim in security config");
		http.authorizeRequests().antMatchers("/**").permitAll().and().formLogin()
				.loginProcessingUrl("/j_spring_security_check").failureUrl("/j_spring_security_check?error")
				.defaultSuccessUrl("/formLogin").usernameParameter("j_username").passwordParameter("j_password").and()
				.logout().deleteCookies("JSESSIONID").logoutSuccessUrl("/getHomePage").and().csrf().disable();
	}

	@Bean
	protected MethodSecurityExpressionHandler createExpressionHandler() {
		System.out.println("i am in create expression handler");
		DefaultMethodSecurityExpressionHandler expressionHandler = new DefaultMethodSecurityExpressionHandler();
		expressionHandler.setPermissionEvaluator(permEval);
		return expressionHandler;
	}

	/**
	 * Use this method when authentication and method level authorization enabled
	 * 
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
	public void registerGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authProvider);
	}

}
