package com.akeir.publisher.security.cust.userdetails;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.akeir.publisher.security.model.User;

public class UserDetailsImpl implements UserDetails {

	private User user;
	
	public UserDetailsImpl(User user)
	{
		this.user = user;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() 
	{
		return List.of(() -> "read");
	}

	@Override
	public String getPassword() 
	{
		return user.getPassword();
	}

	@Override
	public String getUsername() 
	{
		return user.getUsername();
	}
	
}
