package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.repository.UserRepository;
@Component
public class UserService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	return	userRepository.findByUsername(username).map(t->new UserDetail(t.getUsername(), t.getPassword())).
			orElseThrow(()->new UsernameNotFoundException("user not available"));
	
		
	}
	
	
}
