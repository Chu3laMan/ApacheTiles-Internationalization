package com.example.demo.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import com.example.demo.exception.MissingTOTPKeyAuthenticatorException;
import com.example.demo.model.CustomUserDetails;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class TOTPAuthenticationProvider extends DaoAuthenticationProvider {
	
	private final Logger logger = LoggerFactory.getLogger(TOTPAuthenticationProvider.class);
    private TOTPAuthenticator totpAuthenticator;

    @Override
    protected void additionalAuthenticationChecks(UserDetails customUserDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
            throws AuthenticationException {

        super.additionalAuthenticationChecks(customUserDetails, authentication);

        if (authentication.getDetails() instanceof TOTPWebAuthenticationDetails) {
            String secret = ((CustomUserDetails) customUserDetails).getSecret();

            if (StringUtils.hasText(secret)) {
                Integer totpKey = ((TOTPWebAuthenticationDetails) authentication
                        .getDetails()).getTotpKey();
                if (totpKey != null) {
                    try {
                        if (!totpAuthenticator.verifyCode(secret, totpKey, 2)) {
                            logger.info("Code {} was not valid", totpKey);
                            System.out.printf("Code %d was not valid", totpKey);
                            throw new BadCredentialsException(
                                    "Invalid TOTP code");
                        }
                    }
                    catch (InvalidKeyException | NoSuchAlgorithmException e) {
                        throw new InternalAuthenticationServiceException(
                                "TOTP code verification failed", e);
                    }

                }
                else {
                    throw new MissingTOTPKeyAuthenticatorException(
                            "TOTP code is mandatory");
                }

            }
        }

    }

    public TOTPAuthenticator getTotpAuthenticator() {
        return totpAuthenticator;
    }

    public void setTotpAuthenticator(TOTPAuthenticator totpAuthenticator) {
        this.totpAuthenticator = totpAuthenticator;
    }

}
