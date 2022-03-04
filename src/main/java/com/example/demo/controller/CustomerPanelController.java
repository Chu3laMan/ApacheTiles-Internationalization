package com.example.demo.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.Optional;

import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.example.demo.entities.User;
import com.example.demo.entities.UserInfo;
import com.example.demo.entities.UserRole;
import com.example.demo.enums.RoleName;
import com.example.demo.exception.UnkownIdentifierException;
import com.example.demo.security.TOTPAuthenticator;
import com.example.*;
import com.example.demo.service.QRCodeServiceImpl;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserInfoService;
import com.example.demo.service.UserService;
import com.example.demo.util.PlainTextPasswordEncoder;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import dev.samstevens.totp.exceptions.QrGenerationException;

@Controller
@RequestMapping("/customer-panel")
public class CustomerPanelController {
	
	private String APPLICATION_NAME = "Chu3la";
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PlainTextPasswordEncoder bcrypt;
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private QRCodeServiceImpl qRCodeServiceImpl;
	
	
	
	private static final Logger LOG = LoggerFactory.getLogger(CustomerPanelController.class);
	
	@GetMapping("/home") 
	public String books() {
		return "home";
	}
	

	
	@RequestMapping(value = "/generateQRCode", method = RequestMethod.GET)
    public void generateQRCode(String email, HttpServletResponse response) throws WriterException, IOException {
        String otpProtocol = userService.generateOTPProtocol(email);
        response.setContentType("image/png");
        byte[] qrCode = qRCodeServiceImpl.generateQRCode(otpProtocol, 200, 200);
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(qrCode);
    }
	
    @PostMapping("/qr-verification")
    public String verifyQRCODE(HttpServletRequest request, HttpServletResponse response, Model model) {
    	LOG.info("-----------QR CODE VERIFICATION-----------");
    	String email = request.getParameter("email");
    	System.out.println(email);
    	User user = userService.findByUserId(email);
    	System.out.println(user.getUserId());
    	String qrDigit = request.getParameter("qrDigit");
    	System.out.println(qrDigit);
    	boolean isCodeMatch = qrDigit.equalsIgnoreCase(qRCodeServiceImpl.getTOTPCode(user.getSecret()));
    	if(isCodeMatch) {
    		return "redirect:/customer-panel/home";
    	}
    	return "qr-verification";
    }
    
    @GetMapping("/qr-verification")
    public String getVerifyCode() {
    	return "qr-verification";
    }
	
	@PostMapping("/login")
	public String customerVerfication(HttpServletRequest request, HttpServletResponse response, Model model) {
		LOG.info("-----------Customer LOGIN-----------");
		
		HttpSession httpSession = request.getSession();
		
		String email = request.getParameter("email");                                                                                          
		String pswd = request.getParameter("pswd");
		String message = "";
		
		
		User user = userService.findByUserId(email);
		
		try {
			if(user == null) {
				message = "You don't have an account, At First Create an Account.";
				model.addAttribute("errorMsg", message);
				return "login";
			}
			
			
			boolean isMatch = bcrypt.matches(pswd, user.getPassword());
			System.out.println("equality " + isMatch);
			
			if(email.equalsIgnoreCase(user.getUserId()) && !isMatch) {
				System.out.println("Not Match " + isMatch);
				message = "Invalid information.";
				model.addAttribute("errorMsg", message);
				return "login";
			}
			
			boolean isCustomer = false;
			if(email.equalsIgnoreCase(user.getUserId()) && isMatch) {
				System.out.println("match " + isMatch);
				for(com.example.demo.entities.Role role : user.getRoles()) {
					System.out.println(role.getName());
					if(role.getName().equalsIgnoreCase(RoleName.CUSTOMER.toString())) {
						isCustomer = true;
					}
				}
				
				
				
				if(isCustomer) {
					httpSession.setAttribute("CLIENT", user);
					String qr = "/generateQRCode?email=" + user.getUserId();
					httpSession.setAttribute("qrCodeContent", qr);
					httpSession.setAttribute("emailId", user.getUserId());
					return "redirect:/customer-panel/qr-verification";
				}else {
					message = "Invalid Credentials.";
					model.addAttribute("errorMsg", message);
					return "login";
				}
			}
		}catch(Exception e) {
			message = "Something went wrong!";
			model.addAttribute("errorMsg", message);
			return "login";
		}
		return message;
	}
	
	
	
	@GetMapping("/login")
	public String showCustomerLogin(Model model) {
		return "login";
	}
	
	@GetMapping("/customer-registeration")
	public String addCustomerForm(HttpServletRequest request, HttpServletResponse response, Model model) {
		LOG.info("-----------Customer Registration----------");
		
		HttpSession session = request.getSession();
		session.setAttribute("APP_NAME", APPLICATION_NAME);
		
		if(session.getAttribute("userInfo") == null)
			model.addAttribute("userInfo", new UserInfo());
		
		return "customer-register";
	}
	
	@PostMapping("/customer-registration")
	public RedirectView registerCustomerForm(HttpServletRequest request, HttpServletResponse response, Model model, RedirectAttributes red) {
		
		HttpSession httpSession = request.getSession();
		String email = request.getParameter("email");
		
		UserInfo userInfo = new UserInfo();
		userInfo.setFirstName(request.getParameter("firstname"));
		userInfo.setLastName(request.getParameter("lastname"));
		userInfo.setCountry(request.getParameter("country"));
		userInfo.setStreetNumber(request.getParameter("address"));
		userInfo.setCity(request.getParameter("city"));
		userInfo.setState(request.getParameter("state"));
	    userInfo.setPincode(request.getParameter("postcode"));
		userInfo.setContactEmail(request.getParameter("phone"));
		String password = request.getParameter("password");
		String password2 = request.getParameter("password2");
		String agreedTerms = request.getParameter("terms");
		RedirectView view = new RedirectView("customer-registration");
		
		httpSession.setAttribute("uInfo", userInfo);
		
		
		User u = userService.findByUserId(email);
		String message;
		String url;
		
		if(u != null) {
			message = "The email address is already exists.";
			red.addFlashAttribute("errorMsg", "Email already exists.");
			return view;
		}
		
		if(agreedTerms != null) {
			userInfo.setAgreedTerms(true);
			userInfo = this.userInfoService.insert(userInfo);
		}else {
			red.addFlashAttribute("errorMsg", "Please Accept Terms and Conditions");
			return view;
		}
		
		User user = new User();
		user.setUserId(email);
		if(password.equalsIgnoreCase(password2)) {
			user.setPassword(bcrypt.encode(password));
		}
//		user.setSecret(url)
		user.setUserInfo(userInfo);
		user = userService.save(user);
		
		Role role = (Role) this.roleService.findById(Long.valueOf(RoleName.CUSTOMER.ordinal() + 1)).get();
		
		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole((com.example.demo.entities.Role) role);
		userRole.setDeleted(false);
		userRole.setCreated_date(new Date());
		httpSession.removeAttribute("uInfo");
		red.addFlashAttribute("successMsg", "Successfully registered! Plese login to continue");
		httpSession.removeAttribute("userInfo");
		return new RedirectView("login");
		
	}
	
	
	
	
	
	
	
	

}
