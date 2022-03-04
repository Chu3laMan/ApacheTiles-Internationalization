package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;
import org.springframework.util.StringUtils;

import org.springframework.security.web.authentication.WebAuthenticationDetails;

public class TOTPWebAuthenticationDetails extends WebAuthenticationDetails {
	
	private Integer totpKey;

	public TOTPWebAuthenticationDetails(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		String totpKeyString = request.getParameter("TOTPKey");
        if (StringUtils.hasText(totpKeyString)) {
            try {
                this.totpKey = Integer.valueOf(totpKeyString);
            }
            catch (NumberFormatException e) {
                this.totpKey = null;
            }
        }
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -3805279823265949380L;
	
	public Integer getTotpKey() {
        return this.totpKey;
    }

}
