package com.abhishek.book_store.security.controller;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhishek.book_store.security.repository.UserRepository;
import com.abhishek.book_store.security.configuration.JwtTokenUtil;
import com.abhishek.book_store.security.model.JwtRequest;
import com.abhishek.book_store.security.model.JwtResponse;
import com.abhishek.book_store.security.model.UserModel;
import com.abhishek.book_store.security.service.JwtUserDetailsService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class JwtAuthenticationController {
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationController.class);
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	

	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
		
		
		//logger.info("Received login data "+ authenticationRequest.toString());
		
		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		UserModel userModel = userRepository.findUserByUsername(authenticationRequest.getUsername());
		String role = userModel.getRole();
		logger.info("Authorities: "+ role);

		//return ResponseEntity.ok(new JwtResponse(token));
		return new ResponseEntity<>(new JwtResponse(token,role), HttpStatus.OK);
	}

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody UserModel userModel) throws Exception {
		
		UserModel alreadyPresentUser = null;
		alreadyPresentUser = userRepository.findUserByUsername(userModel.getUsername());
		
		
		if(alreadyPresentUser==null)
		{
			userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
			UserModel insertedUser = userRepository.insert(userModel);
			return new ResponseEntity<>(insertedUser, HttpStatus.CREATED);
		}else {
			throw new Exception("User Already Present");
		}
		
	}


	

}
