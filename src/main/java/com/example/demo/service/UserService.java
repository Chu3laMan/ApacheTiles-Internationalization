package com.example.demo.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.User;
import com.example.demo.exception.UnkownIdentifierException;
import com.example.demo.repository.UserRepository;


import org.apache.commons.codec.binary.Base32;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	
	public List<User> allUsers() {
		return userRepository.findAll();
	}
	
	
	/*This method is to find User using Id 
	 * 
	 * @param id   
	 * 
	 * @return User This is User Object matching with Given id
	*/
	public User findById(Long id)
	{

		return this.userRepository.findById(id).orElse(null);
	}
	
	
	/*This method is to Save list of users
	 * 
	 * @param list of users this is objects of users
	 *
	*/
	public void saveAll(List<User> userList)
	{
		userRepository.saveAll(userList);
	}
	
	/*This method is to Save  user
	 * 
	 * @param user this is object of user
	 *
	*/
	public User save(User user)
	{
		user.setSecret(generateSecret());
		return userRepository.save(user);
	}
	
	/*This method is to delete user
	 * 
	 * @param user this is object of user
	 *
	*/
	public void delete(User user)
	{
		userRepository.delete(user);
	}

	/*This method is to find User using user id 
	 * 
	 * @param username this is userId   
	 * 
	 * @return User This is User Object matching with Given userId
	*/
	public User findByUserId(String username) {
		
		return  userRepository.findByUserId(username).orElse(null);
	}

	/*This method is to find all active User using user id
	 *
	 * @param username this is userId
	 *
	 * @return User This is User Object matching with Given userId
	*/
	public User findActiveByUserId(String username) {

		return  userRepository.findActiveByUserId(username).orElse(null);
	}
	 
	/*This method is to list of Users using email and password
	 * 
	 * @param email this is userId   
	 * @param password this is password
	 * 
	 * @return list of Users This is User Object matching with Given email and password
	*/
	 public Optional<User> findByEmailAndPassword(String email, String password){
		 return this.userRepository.findByUserIdAndPassword(email, password);
	 }

	 /*This method is to verify user exist with given email address
	 * 
	 * @param email this is userId   
	 * 
	 * @return boolean true if email exist and false if not
	*/
	 public Boolean existsByEmail(String email) {
		 return this.userRepository.existsByUserId(email);
	 }
	 
	 
	
	private String generateSecret() {
	        byte [] buffer = new byte[10];
	        new SecureRandom().nextBytes(buffer);
	        return new String(new Base32().encode(buffer));
	}
	
	public String generateOTPProtocol(String userName) {
        User user = userRepository.findByUserId(userName).get();
        return String.format("otpauth://totp/%s:%s?secret=%s&issuer=SpringBootTOTP", userName, userName, user.getSecret());
    }
	 
	 
	
	
	

}
