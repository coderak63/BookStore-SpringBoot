package com.abhishek.book_store.security.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhishek.book_store.security.repository.UserRepository;
import com.abhishek.book_store.security.model.UserModel;

@Service
public class JwtUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		UserModel userModel = userRepository.findUserByUsername(username);
		
		if (userModel.getUsername().equals(username)) {
			List<SimpleGrantedAuthority> authorities = new ArrayList<>();
			authorities.add(new SimpleGrantedAuthority(userModel.getRole()));
			return new User(userModel.getUsername(), userModel.getPassword(),
					authorities);
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
	}

}
