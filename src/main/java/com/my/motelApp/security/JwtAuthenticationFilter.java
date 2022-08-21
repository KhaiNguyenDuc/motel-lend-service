package com.my.motelApp.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.my.motelApp.repository.UserRepository;
import com.my.motelApp.service.CustomUserDetailsService;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

	Logger log = LoggerFactory.getLogger(JwtAuthenticationFilter.class);
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	CustomUserDetailsService detailsService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		String jwt = tokenProvider.getToken(request);
		if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			
			Long userId = tokenProvider.getUserId(jwt);
			
			UserPrincipal userPrincipal = detailsService.loadById(userId);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					userPrincipal,
					null,
					userPrincipal.getAuthorities());
			
			SecurityContextHolder.getContext().setAuthentication(authenticationToken);
		}
		filterChain.doFilter(request, response);
	}

}
