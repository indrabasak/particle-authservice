package com.divstar.particle.authservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class AuthServiceWebConfiguration extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthServiceWebConfiguration.class);

	@Override
	protected void configure(final HttpSecurity http) throws Exception {
		http.csrf().disable()
			.headers().frameOptions().disable()
			.and()
			.authorizeRequests()
			.antMatchers("/**").permitAll();
	}

	//	@Override
	//	protected void configure(final HttpSecurity http) throws Exception {
	//		http.csrf().ignoringAntMatchers("/login");
	//
	//		http.authorizeRequests()
	//			.antMatchers("/login")
	//			.permitAll()
	//			.antMatchers("/**/*")
	//			.denyAll();
	//	}
}
