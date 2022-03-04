package co.chu3la.legume.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chu3la.legume.entities.UserRole;
import co.chu3la.legume.repository.UserRoleRepository;

@Service
public class UserRoleService {
	
	@Autowired
	private UserRoleRepository userRoleRepository;
	
	

	/*This method is to find UserRole using Id 
	 * 
	 * @param id
	 * 
	 * @return UserRole This is UserRole Object matching with Given id
	*/
	public UserRole findById(Long id)
	{

		return this.userRoleRepository.findById(id).orElse(null);
	}

	 /*This method is to UserRole the User Role Object
	 * 
	 * @param UserRole This is User Role object
	 * 		 *
	 * @return UserRole This is UserRole Objects of saved User Role 
	*/	
	public UserRole insert(UserRole userRole)
	{
		return this.userRoleRepository.save(userRole);
	}
	
	
	/*This method is to find UserRole using Id and role
	 * 
	 * @param id
	 * @param role
	 * 
	 * @return UserRole This is UserRole Object matching with Given id and role
	*/
	public UserRole findByRole(Long id,String role) {
		
		List<UserRole> userRoleList = userRoleRepository.findByUserId(id);
		
		return userRoleList.stream().filter(r -> r.getRole().getName().equals(role.toUpperCase())).findFirst().get();
	
	
	}
	
	/*This method is to find UserRole using Id and role name
	 * 
	 * @param id
	 * @param name this is role name
	 * 
	 * @return UserRole This is UserRole Object matching with Given id and name
	*/
	public UserRole findByUserIdAndRoleName(Long id,String name)
	{

		return this.userRoleRepository.findByUserIdAndRoleNameIgnoreCase(id,name).orElse(null);
	}


}
