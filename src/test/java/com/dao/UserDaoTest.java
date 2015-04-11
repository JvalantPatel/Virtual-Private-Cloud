package com.dao;

import java.util.List;

import com.application.dao.UserDao;
import com.application.model.User;

import junit.framework.TestCase;

public class UserDaoTest extends TestCase {

	public void testAddUser() throws ClassNotFoundException {
		UserDao userDao = new UserDao();
		//userDao.addUser("Jainik", "test123test");
		//MySQLConnection.getInstance();
		/*if(userDao.isVmNameValid("Jainik", "TestVM2")) {
			userDao.addUserVM("Jainik", "TestVM2");
		} else {
			System.out.println("VM with given name already exists. Please give different Name");
		}
		List<String> vmList = userDao.getUserVMs("Jainik");
		for(String vm : vmList) {
			System.out.println(vm);
		}*/
		
		User user = UserDao.getUser("Jainik");
		System.out.println(user.getUserName());
		System.out.println(user.getPassword());
	}

}
