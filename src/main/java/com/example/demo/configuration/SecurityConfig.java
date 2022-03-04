package com.example.demo.configuration;



import java.net.UnknownHostException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFilter;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.authentication.rememberme.TokenBasedRememberMeServices;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.security.TOTPAuthenticationProvider;
import com.example.demo.service.AuthenticationProviderService;
import com.example.demo.util.PlainTextPasswordEncoder;

import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.NtpTimeProvider;
import dev.samstevens.totp.time.TimeProvider;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private AuthenticationProviderService authenticationProviderService;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	
	@Bean
	@Primary
	public PlainTextPasswordEncoder encoded() {
		return new PlainTextPasswordEncoder();
	}
	
	@Autowired
	public DataSource dataSource;
	
	@Autowired
	private PersistentTokenRepository persistenceTokenRepository;
	
	
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) {
		auth.authenticationProvider(authenticationProviderService);
	}
	
	
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
//		http.csrf().disable().cors().disable();
		http.authorizeRequests().antMatchers("/customer-panel/**").permitAll();
		
//		http.formLogin()
//	     .loginPage("/customer-panel/login")
//	     .defaultSuccessUrl("/customer-panel/books", true)
//	     .permitAll()
//	    .and()
//	     .rememberMe().key("remember-me")
//	     .rememberMeParameter("remember-me")
//	     .rememberMeCookieName("remembermelogininnardone")
//	     .alwaysRemember(true)
//	     .tokenValiditySeconds(100)
//	     .and()
//	     .logout()
//	     .logoutUrl("/logout")
//	     .deleteCookies("remembermelogininnardone");
		
	}
	
	
	
	     

    // Token stored in Memory (Of Web Server).
	@Bean
    public PersistentTokenRepository persistentTokenRepository() {
        InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
        return memory;
    }
	
	
	

}
