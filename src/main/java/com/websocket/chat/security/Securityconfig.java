package com.websocket.chat.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Securityconfig extends WebSecurityConfigurerAdapter {

	// Securing the urls and allowing role-based access to these urls.
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.
		
		httpBasic().and().authorizeRequests()
//		 httpBasic().disable()
//         .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//         .authorizeRequests()
				.antMatchers("/rest/user-connecti").hasRole("USER")
				.and()
				.headers()
				// allow same origin to frame our site to support iframe SockJS
				.frameOptions().sameOrigin()
				.and()
				.authorizeRequests()
				.and()
				.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication().withUser("user").password(encoder().encode("user"))
				.roles("USER");
//		auth.inMemoryAuthentication().withUser("user").password("$2a$04$cBG76lGferNtLmZfQdGw7OExCVy9dIwoqsDPX8opGExQEeK04Sp72")
//		.roles("USER");
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}