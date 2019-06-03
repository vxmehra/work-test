package com.loyaltyone.homework.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.loyaltyone.homework.data.UserRepository;
import com.loyaltyone.homework.entities.User;
import com.loyaltyone.homework.exception.MyResourceNotFoundException;

@Service
@Transactional
public class UserService {

	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	@Transactional(readOnly=true)
	public User getUserByName(String userName) {
		Optional<User> user = userRepository.findByName(userName);
		if(!user.isPresent()) throw new MyResourceNotFoundException("User Not Found");
		return user.get();
	}

	@Transactional(readOnly=true)
	public List<User> getAllUsers() {
		Iterable<User> it = userRepository.findAll();
		List<User> users = new ArrayList<>();
		it.forEach(users::add);
		if(users.isEmpty()) throw new MyResourceNotFoundException("Users Not Found");
		return users;
	}
	
	@Transactional(readOnly=true)
	public User getUserById(Long id) {
		Optional<User> user = userRepository.findById(id);
		if(!user.isPresent()) throw new MyResourceNotFoundException("User Not Found");
		return user.get();
	}
	
	public void saveUser(User user) {
		userRepository.save(user);
	}
	
	
}
