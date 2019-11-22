package com.learning.service.movieinfo.service;

import java.util.Arrays;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.learning.service.movieinfo.model.MovieInfoUserDetails;

@Service
public class MovieInfoUserDetailsService implements UserDetailsService {
	
	
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return new MovieInfoUserDetails("rouser", "rouser", true, true, true, true, Arrays.asList(new SimpleGrantedAuthority("ROLE_ROUSER")));
	}

}
