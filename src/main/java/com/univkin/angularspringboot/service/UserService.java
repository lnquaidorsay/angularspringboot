package com.univkin.angularspringboot.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.univkin.angularspringboot.entite.UserEntite;

public interface UserService extends UserDetailsService {
	UserEntite createUser(UserEntite user);

	UserEntite getUser(String email);

	UserEntite getUserByUserId(String userId);
}
