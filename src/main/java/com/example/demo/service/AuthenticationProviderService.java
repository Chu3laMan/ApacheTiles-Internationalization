package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.util.PlainTextPasswordEncoder;

@Service
public class AuthenticationProviderService implements AuthenticationProvider {
	
	@Autowired
	private PlainTextPasswordEncoder bcryptPasswordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String username = authentication.getName();
		String password = (String) authentication.getCredentials();
		
		CustomUserDetails u = (CustomUserDetails) customUserDetailsService.loadUserByUsername(username);
		
		return checkPassword(u, password, bcryptPasswordEncoder);
		
	}

	

	private Authentication checkPassword(CustomUserDetails u, String password,
			PlainTextPasswordEncoder bcryptPasswordEncoder2) {
		if(bcryptPasswordEncoder2.matches(password, u.getPassword())) {
			return new UsernamePasswordAuthenticationToken(u.getUsername(), 
					u.getPassword(), 
					u.getAuthorities()
					);
		}
		throw new BadCredentialsException("Bad Credentials");
	}



	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
