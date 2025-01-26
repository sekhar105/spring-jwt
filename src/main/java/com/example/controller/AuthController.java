package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.JwtService;


@RestController
@RequestMapping("/aut")
public class AuthController {
    @Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtService jwtService;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder; 
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/auth")
	public  String authenticate(@RequestBody UserDto user){
//	return jwtService.generateToken(user.getUserName());
		Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
		System.out.println(authenticate.toString());
		if(authenticate.isAuthenticated()) {
			return jwtService.generateToken(user.getUserName());
		}else {
			throw new UsernameNotFoundException("Invalid user request !");
		}
//		return "hi";
	}
	@PostMapping("/add")
	public ResponseEntity onboard(@RequestBody UserDto userDto) {
		User user = new User();
		System.out.println(userDto.getUserName());
		user.setUsername(userDto.getUserName());
		user.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
		userRepository.save(user);
		return new ResponseEntity("user added",HttpStatus.OK);
	}
	@GetMapping("/get1")
	 public String get1() {
		 return "hi";
	 }
	@GetMapping("/get")
	 public String get() {
		 return "hi";
	 }
}
