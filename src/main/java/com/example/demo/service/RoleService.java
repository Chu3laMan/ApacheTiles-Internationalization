package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Role;
import com.example.demo.enums.RoleName;
import com.example.demo.repository.RoleRepository;

@Service
public class RoleService {
	
	@Autowired
	private RoleRepository roleRepository;


	/*This method is to find Role using  name 
	 * 
	 * @param name   
	 * 
	 * @return Role This is Role Object matching with Given name
	*/
	public Role findByName(RoleName string)
	{
		return roleRepository.findByName(string).get();
	}
	

	/*This method is to find Role using  id 
	 * 
	 * @param id   
	 * 
	 * @return Role This is Role Object matching with Given id
	*/
	public Optional<Role> findById(Long id)
	{
		return roleRepository.findById(id);
	}
	
	/*This method is to find list of Role
	 * 
	 * 
	 * @return list of Role objects 
	*/
	public List<Role> findAll()
	{
		return roleRepository.findAll();
	}

}
