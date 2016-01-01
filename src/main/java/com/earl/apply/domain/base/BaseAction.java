package com.earl.apply.domain.base;

import java.lang.reflect.ParameterizedType;
import java.util.Map;

import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.earl.apply.domain.user.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

//默认是类简单名称，首字母小写
public class BaseAction<T> extends ActionSupport implements RequestAware,
		SessionAware, ApplicationAware, ModelDriven<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected T model;

	protected Map<String, Object> request;
	protected Map<String, Object> session;
	protected Map<String, Object> application;

	protected UserService userServer ;
	

	@SuppressWarnings("unchecked")
	public BaseAction() {
		ParameterizedType type = (ParameterizedType) this.getClass()
				.getGenericSuperclass();
		@SuppressWarnings("rawtypes")
		Class clazz = (Class) type.getActualTypeArguments()[0];
		try {
			model = (T) clazz.newInstance();
			//TODO 输出model的
//			System.out.println(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}

	@Override
	public T getModel() {
		return model;
	}

	@Override
	public void setApplication(Map<String, Object> application) {
		this.application = application;

	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

}
