package com.temu.app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.temu.app.entity.User;
import com.temu.app.repository.UserRepository;
import com.temu.app.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	
	UserRepository userRepository;
	PasswordEncoder passwordEncoder;
	
	public UserServiceImpl(
			UserRepository userRepository, 
			PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	
	@Override
	public User getUserById(Long id) {		
		Optional<User> userOptional = userRepository.findById(id);
		User existingUser;
		
		if( userOptional.isPresent() ) {
			existingUser = userOptional.get();
			return existingUser;
		} else {
			throw new IllegalStateException("User does not exist with id " + id);
		}			
	}

	@Override
	public User getUserByEmail(String email) {
		Optional<User> userOptional = userRepository.findByEmail(email);
		User existingUser;
		
		if( userOptional.isPresent() ) {
			existingUser = userOptional.get();
			return existingUser;
		} else {
			throw new IllegalStateException("User does not exist with email " + email);
		}	
	}

	@Override
	public User createUser(User user) {	
		user.setActive(true);
		user.setId(null);
		// user.setRole( new Role(1) );
		user.setPassword( passwordEncoder.encode( user.getPassword() ) );
		
		if( userRepository.existsByEmail(user.getEmail()) ) {
			throw new IllegalStateException("User exist with email " + user.getEmail());
		}
					
		return userRepository.save(user);
	}

	@Override
	public List<User> getAllActiveUsers() {		
		return (List<User>) userRepository.findAllByActiveTrue();
	}

	@Override
	public List<User> getAllInactiveUsers() {
		return (List<User>) userRepository.findAllByActiveFalse();
	}
	
	@Override
	public List<User> getAllUsers(boolean isActive) {
		if( isActive ) return getAllActiveUsers();
		else return getAllInactiveUsers();		
	}

	@Override
	public User updateUser(User user, Long id) {
		User existingUser = getUserById(id);
		existingUser.setFirstName( user.getFirstName() );
		existingUser.setLastName( user.getLastName() );
		existingUser.setPassword( user.getPassword() );
		existingUser.setTelephone( user.getTelephone() );
		existingUser.setBirthDate( user.getBirthDate() );
		return userRepository.save(existingUser);
	}

	@Override
	public void deleteUser(Long id) {		
		User existingUser = getUserById(id);		
		existingUser.setActive(false);
		
		userRepository.save(existingUser);
	}

}
