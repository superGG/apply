package com.earl.apply.domain.user;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.earl.apply.domain.base.BaseServiceImpl;
import com.earl.apply.util.ToolMail;

/**
 * 每个ServiceImpl都要继承相对应的service接口
 * 
 * @author Administrator
 * 
 */
public class UserServiceImpl extends BaseServiceImpl<UserPo> implements
		UserService {
	
	public UserServiceImpl(){
		baseDao = new UserDaoImpl();
	}
	
	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager
			.getLogger(UserServiceImpl.class);
	
	@Override
	public Boolean saveUser(UserPo model) {
		try {
			if(model.getPhoneNumber()==null||model.getUserClass()==null||model.getUserName()==null){
				logger.error("非法入侵");
				throw new RuntimeException("指定字段信息为空，请自己检查字段信息");
			}
			userDao = new UserDaoImpl();
			userDao.save(model);
			sendMessage(model.toString());
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

//	public List<UserPo> findAll(){
//		userDao = new UserDaoImpl();
//		return userDao.findAll();
//	}
	
	private void sendMessage(String content) {
		
//		logger.info("正在发送邮件信息！");
		String host = "smtp.163.com";		// 发送邮件的服务器的IP
		String port = "25";	// 发送邮件的服务器的端口
		
		String from = "yilinfeng1133@163.com";		// 邮件发送者的地址
		String userName = "yilinfeng1133@163.com";	// 登陆邮件发送服务器的用户名
		String password = "hhpujooblvbkeffo";	// 登陆邮件发送服务器的密码
		
		List<String> to = new ArrayList<String>();			// 邮件接收者的地址
		to.add("798555920@qq.com");
		to.add("jiataotx@163.com");
		
		boolean validate = true;	// 是否需要身份验证
		
		String subject = "服务器出错，错误信息：";		// 邮件标题
//		String content = "内容test111";		// 邮件的文本内容
//		String[] attachFileNames = new String[]{"D:/code.jpg"};	// 邮件附件的文件名
		ToolMail.sendTextMail(host, port, validate, userName, password, from, to, subject, content, null);
	}

}
