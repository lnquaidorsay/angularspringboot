package com.univkin.angularspringboot.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.univkin.angularspringboot.SpringApplicationContext;
import com.univkin.angularspringboot.entite.UserEntite;
import com.univkin.angularspringboot.request.UserLoginRequest;
import com.univkin.angularspringboot.service.UserService;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	private final AuthenticationManager authenticationManager;

	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		try {

			UserLoginRequest creds = new ObjectMapper().readValue(req.getInputStream(), UserLoginRequest.class);
			System.out.println("creds email :" + creds.getEmail() + " creds password : " + creds.getPassword());

			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getPassword(), new ArrayList<>()));

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String userName = ((User) auth.getPrincipal()).getUsername();

		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");

		UserEntite userDto = userService.getUser(userName);

		String token = Jwts.builder().setSubject(userName).claim("id", userDto.getUserId())
				.claim("name", userDto.getNom() + " " + userDto.getPrenom())
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstants.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstants.TOKEN_SECRET).compact();

		res.addHeader(SecurityConstants.HEADER_STRING, SecurityConstants.TOKEN_PREFIX + token);
		res.addHeader("user_id", userDto.getUserId());

		/*
		 * res.getWriter().write("{\"token\": \"" + token + "\", \"id\": \"" +
		 * userDto.getUserId() + "\"}");
		 */

		res.getWriter().write("{\"token\": \"" + token + "\", \"nom\": \"" + userDto.getNom() + "\",\"id\": \""
				+ userDto.getUserId() + "\"}");

	}
}
