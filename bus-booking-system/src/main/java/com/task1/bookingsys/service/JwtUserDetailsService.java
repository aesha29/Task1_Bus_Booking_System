package com.task1.bookingsys.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.task1.bookingsys.model.User;
import com.task1.bookingsys.repository.UserRepo;


@Service
public class JwtUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepo repo;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if(username.contains("@")) {
			User user = repo.findByUsermail(username);
		if (user == null) 
			throw new UsernameNotFoundException("User not found with username: " + username);
		
		return new org.springframework.security.core.userdetails.User(user.getUsermail(), user.getPassword(),
				getAuthority(user));
		}
		else {
		User user = repo.findByUsername(username);
		if (user == null) 
			throw new UsernameNotFoundException("User not found with username: " + username);
		
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
				getAuthority(user));
		
		}	
	   
	}

	
	private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
	
	
	
	
}