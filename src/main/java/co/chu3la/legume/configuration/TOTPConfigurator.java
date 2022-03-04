package co.chu3la.legume.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import co.chu3la.legume.security.TOTPAuthenticationProvider;
import co.chu3la.legume.security.TOTPAuthenticator;
import co.chu3la.legume.security.TOTPWebAuthenticationDetailsSource;
import co.chu3la.legume.service.CustomUserDetailsService;

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
