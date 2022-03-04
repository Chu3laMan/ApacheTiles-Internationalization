package com.example.demo.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.demo.security.TOTPAuthenticationProvider;
import com.example.demo.security.TOTPAuthenticator;
import com.example.demo.security.TOTPWebAuthenticationDetailsSource;
import com.example.demo.service.CustomUserDetailsService;

@Component
public class TOTPConfigurator {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    public void configureAuthenticationDetailsSource(HttpSecurity http) throws Exception{
        http.formLogin().authenticationDetailsSource(new TOTPWebAuthenticationDetailsSource());
    }

    public void configureAuthenticationProvider(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = new BCryptPasswordEncoder();

        TOTPAuthenticationProvider authenticationProvider = new TOTPAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(encoder);
        authenticationProvider.setUserDetailsService(customUserDetailsService);
        authenticationProvider.setTotpAuthenticator(new TOTPAuthenticator());

        auth.authenticationProvider(authenticationProvider);
    }
}
