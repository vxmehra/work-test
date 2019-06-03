package com.loyaltyone.homework.controllers;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.loyaltyone.homework.bo.UserMessages;
import com.loyaltyone.homework.entities.User;
import com.loyaltyone.homework.services.UserCityService;
import com.loyaltyone.homework.services.UserService;

@RestController
public class MessageController {
	
	Logger logger = LoggerFactory.getLogger(MessageController.class);

	private UserService userService;

	private UserCityService userCityService;

	@Autowired
	public MessageController(UserService userService, UserCityService userCityService) {
		super();
		this.userService = userService;
		this.userCityService = userCityService;
	}

	@CrossOrigin
	@GetMapping(value = "/echo", produces = MediaType.TEXT_HTML_VALUE)
	public String echoMessage(@RequestParam @NotEmpty String message) {
		return message;
	}

	@CrossOrigin
	@PostMapping(value = "/users1", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> storeUserName(@RequestParam @NotEmpty String name) {
		User user = new User();
		user.setName(name);
		userService.saveUser(user);
		return ResponseEntity.ok(user);
	}

	@CrossOrigin
	@PostMapping(value = "/users", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) {
		userService.saveUser(user);
		return ResponseEntity.ok(user);
	}

	@CrossOrigin
	@GetMapping(value = "/users/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserMessages> getUserByName(@PathVariable @NotEmpty String userName,
			@RequestParam @NotEmpty String city) {
		return ResponseEntity.ok(userCityService.getUserCity(userName, city));
	}

	@CrossOrigin
	@GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> getUsers() {
		List<User> user = userService.getAllUsers();
		return ResponseEntity.ok(user);
	}
}
