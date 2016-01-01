package com.earl.apply.domain.user;

import java.util.HashMap;
import java.util.Map;

import com.earl.apply.domain.base.BaseAction;

/**
 * 
 * 用途+action 如Demo+Action-->DemoAction
 * 
 * @author Administrator
 * 
 */
public class UserAction extends BaseAction<UserPo> {


	private static final long serialVersionUID = 3293435262298029608L;
	
	private Map<String,Object>  dataMap = new HashMap<String, Object>();
	
	public Map<String, Object> getDataMap() {
		return dataMap;
	}
	
	//下面开始写action


	public String apply() {
		dataMap.clear();
		
		dataMap.put("resultInfo", "  ");
		dataMap.put("result", false);
		
		userServer = new UserServiceImpl();
		Boolean result = userServer.saveUser(model);
		System.out.println("-----------------------");
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
		System.out.println(dataMap);
		return "success";
	}




}
