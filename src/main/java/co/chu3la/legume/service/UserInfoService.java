package co.chu3la.legume.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.chu3la.legume.entities.UserInfo;
import co.chu3la.legume.repository.UserInfoRepository;

@Service
public class UserInfoService {
	
	@Autowired
	private UserInfoRepository userInfoRepository;

	/*This method is to find UserInfo using Id 
	 * 
	 * @param id   
	 * 
	 * @return UserInfo This is UserInfo Object matching with Given id
	*/
	public UserInfo findById(Long id)
	{

		return this.userInfoRepository.findById(id).orElse(null);
	}

	/*This method is to Save the UserInfo Object
	 * 
	 * @param userInfo This is user info Object
	 *
	 * @return UserInfo This is UserInfo Objects of saved UserInfo
	*/
	public UserInfo insert(UserInfo userInfo)
	{
		return this.userInfoRepository.save(userInfo);
	}
	
	/*This method is to Save the UserInfo Object
	 * 
	 * @param userInfo This is user info Object
	 *
	 * @return UserInfo This is UserInfo Objects of saved UserInfo
	*/
	public UserInfo save(UserInfo userInfo)
	{
		return this.userInfoRepository.save(userInfo);
	}
	
	
	
	/*This method is to delete UserInfo by using userInfo
	 * 
	 * @param userInfo object
	 * 
	*/
	public void deleteUserInfo(UserInfo userInfo)
	{
		userInfoRepository.delete(userInfo);
	}

}
