package com.akeir.publisher.security.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.akeir.publisher.security.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
	{
		return userRepository.findByUsername(username)
				.map(UserDetailsImpl::new)
				.orElseThrow(() -> new UsernameNotFoundException("USERNAME WAS NOT FOUND OR PASSWORD IS WRONG"));
	}
}