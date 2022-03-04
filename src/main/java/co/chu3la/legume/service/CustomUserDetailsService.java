package co.chu3la.legume.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import co.chu3la.legume.entities.User;
import co.chu3la.legume.model.CustomUserDetails;
import co.chu3la.legume.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Supplier<UsernameNotFoundException> s = 
				() -> new UsernameNotFoundException("Problem during authentication");
		User u = userRepository.findByUserId(username).orElseThrow(s);
		
		return new CustomUserDetails(u);
	}

}
