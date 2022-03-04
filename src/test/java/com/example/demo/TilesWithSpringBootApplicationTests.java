package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.binary.Base32;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

import com.example.demo.entities.Role;
import com.example.demo.entities.User;
import com.example.demo.entities.UserInfo;
import com.example.demo.entities.UserRole;
import com.example.demo.enums.RoleName;
import com.example.demo.security.TOTPAuthenticator;
import com.example.demo.service.QRCodeServiceImpl;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserInfoService;
import com.example.demo.service.UserRoleService;
import com.example.demo.service.UserService;
import com.example.demo.util.PlainTextPasswordEncoder;

@SpringBootTest
@AutoConfigureMockMvc
class TilesWithSpringBootApplicationTests {
	
	@Autowired(required = true)
	private MockMvc mvc;
	
	@Autowired
	private PlainTextPasswordEncoder bcrypt;
	
	@Autowired
	private UserInfoService userInfService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserRoleService userRoleService;
	
	@Autowired
	private QRCodeServiceImpl qRCodeServiceImpl;
	

	
	

	@Test
	void contextLoads() {
	}
	
//	@Test
//	public void save() {
//		UserInfo u = new UserInfo("Houssam2", "EL Mansouri2");
//		userInfService.save(u);
//		User user = new User();
//		user.setUserId("elmansouri4.houssam4@gmail.com");
//		user.setPassword(bcrypt.encode("12365"));
//		user.setUserInfo(u);
//		userService.save(user);
//		
//	
//		Role role = this.roleService.findById(Long.valueOf( RoleName.CUSTOMER.ordinal() + 1)).get(); 
//		
//		System.out.println("Role " + role);
//		
//		
//		UserRole userRole = new UserRole();
//		userRole.setUser(user);
//		userRole.setRole(role);
//		userRole.setDeleted(false);
//		userRole.setCreated_date(new Date());
//		userRoleService.insert(userRole);
//	
//		
//		assertThat(userService).isNotNull();
//		assertThat(userRoleService).isNotNull();
//	}
//	
	
//	@Test
//	@WithUserDetails("ouissal@ouissal.com")
//	public void AuthenticatedTest() throws Exception {
//		mvc.perform(get("/home/login"))
//		.andExpect(status().isOk());
//	}
	
//	@Test
//	public void checkPassword() {
//		User user = userService.findByUserId("elmansouri.houssam@gmail.com");
//		System.out.println(user.getPassword().equalsIgnoreCase(bcrypt.encode("12365")));
//		boolean isMatch = bcrypt.matches("12365", user.getPassword());
//		System.out.println(isMatch);
//		assertEquals(isMatch, true);
//				
//	}
	@Test
	public void decodeAndencode() throws EncoderException, InvalidKeyException, NoSuchAlgorithmException {
		String emailId = "KgR4kVMAPq/zOg==";
//		User user = userService.findByUserId(emailId);
		String decoded = qRCodeServiceImpl.getTOTPCode(emailId);
		System.out.println(decoded);
//		assertEquals(emailId, user.getUserId());
	}

}
