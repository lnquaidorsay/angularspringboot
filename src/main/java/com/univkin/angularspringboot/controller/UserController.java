package com.univkin.angularspringboot.controller;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.univkin.angularspringboot.entite.UserEntite;
import com.univkin.angularspringboot.request.UserRequest;
import com.univkin.angularspringboot.response.UserResponse;
import com.univkin.angularspringboot.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {
	@Autowired
	UserService userService;

	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserResponse> createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {

		ModelMapper modelMapper = new ModelMapper();
		UserEntite userDto = modelMapper.map(userRequest, UserEntite.class);

		UserEntite createUser = userService.createUser(userDto);

		UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);

		return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);

	}

	/*
	 * @RequestMapping(method = RequestMethod.POST, produces = "application/json",
	 * consumes = "application/json") public ResponseEntity<UserResponse>
	 * createUser(@RequestBody @Valid UserRequest userRequest) throws Exception {
	 * 
	 * ModelMapper modelMapper = new ModelMapper(); UserEntite userDto =
	 * modelMapper.map(userRequest, UserEntite.class);
	 * 
	 * UserEntite createUser = userService.createUser(userDto);
	 * 
	 * UserResponse userResponse = modelMapper.map(createUser, UserResponse.class);
	 * 
	 * return new ResponseEntity<UserResponse>(userResponse, HttpStatus.CREATED);
	 * 
	 * }
	 */
}
