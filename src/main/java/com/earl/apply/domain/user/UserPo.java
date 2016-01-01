package com.earl.apply.domain.user;



public class UserPo{


	/**
	 * 字段描述：Long 
	 * 字段类型：userId  
	 */
	private Long userId ;
	
	/**
	 * 字段描述：String 
	 * 字段类型：userType  
	 */
	private String userName ;
	
	private String phoneNumber;
	
	private String userClass;
	
	private String saying;
	

	public String getSaying() {
		return saying;
	}

	public void setSaying(String saying) {
		this.saying = saying;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getUserClass() {
		return userClass;
	}

	public void setUserClass(String userClass) {
		this.userClass = userClass;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "UserPo [userId=" + userId + ", userName=" + userName
				+ ", phoneNumber=" + phoneNumber + ", userClass=" + userClass
				+ "]";
	}

	

	
	
}
