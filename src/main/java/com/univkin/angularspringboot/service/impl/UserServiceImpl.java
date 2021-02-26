package com.univkin.angularspringboot.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.univkin.angularspringboot.entite.UserEntite;
import com.univkin.angularspringboot.repository.UserRepository;
import com.univkin.angularspringboot.service.UserService;
import com.univkin.angularspringboot.shared.Utils;

public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	Utils util;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserEntite userEntity = userRepository.findByEmail(email);

		if (userEntity == null)
			throw new UsernameNotFoundException(email);

		return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
	}

	@Override
	public UserEntite createUser(UserEntite user) {
		UserEntite checkUser = userRepository.findByEmail(user.getEmail());

		if (checkUser != null)
			throw new RuntimeException("User Already Exists !");

		user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

		user.setUserId(util.generateStringId(32));

		UserEntite newUser = userRepository.save(user);

		return newUser;
	}

	@Override
	public UserEntite getUser(String email) {
		// TODO Auto-generated method stub
		return userRepository.findByEmail(email);
	}

	@Override
	public UserEntite getUserByUserId(String userId) {
		return userRepository.findByUserId(userId);
	}

}
