////////////////////////////////////////////////////////////////////////////////
//
//  ERICSSON INDIA LIMITED.
//  Copyright 2010-2011 Ericsson India Limited.
//  All Rights Reserved.
//     
//  
//  Initial Developer : Naseem Malik, Ashish Chopra
//  Contributor       : 
//  Date Created      : 
//  Last Modified     : 
//
//
//  NOTICE: Ericsson permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////


package valueObject;

public class UserVO
{
	private int userId;
	private String userName = "";
	private String userEmail = "";
	private String userLoginId = "";
	private String userPassword = "";
	private String userPhone = "";
	
	private String role = "";
	private int roleId = 0;
	private int activityId = 0;
	private int customerId = 0;
	private String activity = "";
	private String customer = "";
	

	public UserVO()
	{
	
	}
	
	public UserVO(int userId,String userName,String userEmail,String userPhone, String userLoginId,String userPasswd,int roleId,String role,int activityId,String activity,int customerId,String customer)
	{
		this.setUserId(userId);
		this.setUserName(userName);
		this.setUserEmail(userEmail);
		this.setUserPhone(userPhone);
		this.setUserLoginId(userLoginId);
		this.setUserPassword(userPasswd);
		this.setRole(role);
		this.setRoleId(roleId);
		this.setActivity(activity);
		this.setActivityId(activityId);
		this.setCustomer(customer);
		this.setCustomerId(customerId);
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getUserLoginId() {
		return userLoginId;
	}

	public void setUserLoginId(String userLoginId) {
		this.userLoginId = userLoginId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPasswd) {
		this.userPassword = userPasswd;
	}

	public String getUserPhone() {
		return userPhone;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getActivityId() {
		return activityId;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getActivity() {
		return activity;
	}

	public void setActivity(String activity) {
		this.activity = activity;
	}

	public String getCustomer() {
		return customer;
	}

	public void setCustomer(String customer) {
		this.customer = customer;
	}
	

	
}

