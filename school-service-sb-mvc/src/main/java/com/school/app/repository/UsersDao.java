package com.school.app.repository;

import java.util.List;

import com.school.app.model.PickLIst;
import com.school.app.model.Users;

public interface UsersDao {
	
	Users saveUsersInfo(Users usersInfo) throws Exception;
	List<Users> findAllUsersInfo()throws Exception;
	Users findUsersInfoById(Integer id)throws Exception;
	int deleteUsersInfoById(Integer id)throws Exception;
	List<PickLIst> findAllStates()throws Exception;
	


}
