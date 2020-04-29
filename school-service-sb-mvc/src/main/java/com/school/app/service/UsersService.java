package com.school.app.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school.app.model.PickLIst;
import com.school.app.model.Users;
import com.school.app.repository.UsersDao;

@Service
public class UsersService {
	
	@Autowired
	private UsersDao userDao;

	
	public Users saveUsersInfo(Users usersInfo) throws Exception {
		String uuidCode = String.valueOf(UUID.randomUUID());
		usersInfo.setUuidCode(uuidCode.trim());
		usersInfo=userDao.saveUsersInfo(usersInfo);
		return usersInfo;
	}
	
	public Users findUsersInfoById(String id) throws Exception {
		Users users = userDao.findUsersInfoById(id);
		return users;
	}
	
	public List<Users> findAllUsersInfo() throws Exception {
		 List<Users> findAllUsersInfos = userDao.findAllUsersInfo();
		 return findAllUsersInfos;
	}
	public void deleteUsersInfoById(String id) throws Exception {
		userDao.deleteUsersInfoById(id);
	}
	
	public List<PickLIst> findAllStates() throws Exception {
		return userDao.findAllStates();
	}


}
