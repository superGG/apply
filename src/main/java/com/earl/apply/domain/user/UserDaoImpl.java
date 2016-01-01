package com.earl.apply.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.earl.apply.domain.base.BaseDaoImpl;

/**
 * 
 * @author 宋文光
 * 
 */
public class UserDaoImpl extends BaseDaoImpl<UserPo> implements UserDao {

	@Override
	public void updateUser(UserPo model) {
			
	}

	@SuppressWarnings("unchecked")
	public UserPo getUser(UserPo model) {
		System.out.println("进入getUser方法");
		List<UserPo> user = new ArrayList<UserPo>();
		try {
			getSession().beginTransaction();
			String hql = "from UserPo u where u.userName = :userName and u.phoneNumber=:phoneNumber";
			user = getSession().createQuery(hql)
					.setString("userName", model.getUserName())
					.setString("phoneNumber", model.getPhoneNumber()).list();
		} catch (Exception e) {
			getSession().getTransaction().rollback();
			e.printStackTrace();
		} finally {
			getSession().getTransaction().commit();
		}
		System.out.println("退出getUser方法");
		return user.get(0);
	}

}