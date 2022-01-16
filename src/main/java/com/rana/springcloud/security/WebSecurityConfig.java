package com.rana.springcloud.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServiceImpl userDetailsservice;
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("userDetailsservice  called");
		auth.userDetailsService(userDetailsservice);
		

	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("Basic authentication called");
		http.httpBasic();
		http.authorizeHttpRequests().mvcMatchers(HttpMethod.GET,"/couponapi/coupons/**").hasAnyRole("USER","ADMIN")
		.mvcMatchers(HttpMethod.POST,"/couponapi/coupons").hasRole("ADMIN").and().csrf().disable();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		System.out.println("passwordEncoder  called");
		return new BCryptPasswordEncoder();
	}
}
