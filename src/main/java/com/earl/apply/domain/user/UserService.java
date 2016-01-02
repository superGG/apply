package com.earl.apply.domain.user;

import com.earl.apply.domain.base.BaseService;

public interface UserService extends BaseService<UserPo>{

	Boolean saveUser(UserPo model);

	Boolean updateUser(UserPo model);

	UserPo getUser(UserPo model);

	void deleAll();

}
