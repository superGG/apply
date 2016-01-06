package com.earl.apply.domain.userTest;

import org.junit.Test;

import com.earl.apply.domain.user.UserDao;
import com.earl.apply.domain.user.UserDaoImpl;
import com.earl.apply.domain.user.UserPo;

public class userTest {

	@Test
	public void saveUser(){
		UserDao userDao = new UserDaoImpl();
		UserPo user = new UserPo();
		user.setUserId(1l);
		user.setUserName("宋文光");
		user.setPhoneNumber("123456789");
		user.setUserClass("软件1133班");
		System.out.println(user.toString());
		userDao.save(user);
	}
}
