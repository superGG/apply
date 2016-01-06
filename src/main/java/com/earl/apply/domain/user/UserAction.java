package com.earl.apply.domain.user;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.earl.apply.domain.base.BaseAction;

/**
 * 
 * 用途+action 如Demo+Action-->DemoAction
 * 
 * @author Administrator
 * 
 */
public class UserAction extends BaseAction<UserPo> {


	/**
	 * log4j实例对象.
	 */
	private static Logger logger = LogManager
			.getLogger(UserAction.class);
	
	private static final long serialVersionUID = 3293435262298029608L;
	
	private Map<String,Object>  dataMap = new HashMap<String, Object>();
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
	//下面开始写action


	public String apply() throws UnsupportedEncodingException {
		dataMap.clear();
		dataMap.put("resultInfo", "  ");
		dataMap.put("result", false);
		userServer = new UserServiceImpl();
		Boolean result = userServer.saveUser(model);
		logger.info("-----------------------");
		if(result) {
			UserPo user = userServer.getUser(model);
			
			String info = new String("保存成功");
			dataMap.put("resultInfo", info);
			dataMap.put("result", result);
			dataMap.put("user", user);
		} else {
			String info = new String("操作失败");
			dataMap.put("resultInfo", info);
			dataMap.put("result", result);
			return "error";
		}
		logger.info(dataMap);
		return "success";
	}

//	public void deleAll() {
//		userServer = new UserServiceImpl();
//		userServer.deleAll();
//	}

	public String bjkexxcx(){
		dataMap.clear();
		userServer = new UserServiceImpl();;
//		List<UserPo> findAll = userServer.findAll();
//		dataMap.put("allUser", findAll);
		return "success";
	}
}
