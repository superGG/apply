package com.earl.apply.domain.user;


import com.earl.apply.domain.base.BaseDao;

public interface UserDao extends BaseDao<UserPo>{

	void updateUser(UserPo model);

	UserPo getUser(UserPo model);

	
	

}
