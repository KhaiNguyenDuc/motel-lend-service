package com.my.motelApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig {

	@Bean
	 SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf()
			.disable()
		.authorizeRequests()
		.antMatchers("/api/v1/users/**")
			.authenticated()
		.antMatchers("/api/v1/roles/**")
			.authenticated()
		.antMatchers(HttpMethod.POST, "/api/v1/**")
			.authenticated()
		.anyRequest()
			.permitAll()
		.and()
			.httpBasic();
	return http.build();
	}

   @Bean
   AuthenticationManager authenticationManager(
		   AuthenticationConfiguration auth) throws Exception {
	   return auth.getAuthenticationManager();
   }
   
   @Bean
   PasswordEncoder passwordEncoder() {
	   return new BCryptPasswordEncoder();
   }
	
	
}
