package com.earl.apply.domain.user;

import com.earl.apply.domain.base.BaseServiceImpl;

/**
 * 每个ServiceImpl都要继承相对应的service接口
 * 
 * @author Administrator
 * 
 */
public class UserServiceImpl extends BaseServiceImpl<UserPo> implements
		UserService {
	
	
	
	@Override
	public Boolean saveUser(UserPo model) {
		try {
			userDao = new UserDaoImpl();
			userDao.save(model);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Boolean updateUser(UserPo model) {
		userDao.updateUser(model);
		return null;
	}

	@Override
	public UserPo getUser(UserPo model) {
		userDao = new UserDaoImpl();
		UserPo user = userDao.getUser(model);
		
		return user;
	}

	@Override
	public void deleAll() {
		userDao = new UserDaoImpl();
		userDao.deleteAll();
	}



}
