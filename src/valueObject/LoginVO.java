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

import java.util.HashMap;

public class LoginVO
{
	private int userId;
	private String userName="";
	private String userLoginUserName="";
	private String password="";
	private String userEmail="";
	private int roleId;
	private String roleName = "";
	private int activityId;
	private String activityName = "";
	private int customerId;
	private String customerName="";
	private int requestType;

	private HashMap<String, Boolean> permissions=new HashMap<String,Boolean>();

	public LoginVO()
	{
		setUserName("");
		setUserName("");
		setUserLoginUserName("");
		setPassword("");
		setUserEmail("");
		setPermissions(new HashMap<String,Boolean>());
	}
	
	public void addPermission(String permission){
		permissions.put(permission,true);
	}
	

	public int getUserId() {
		return userId;
	}
	
	public String getUserEmail() {
		return userEmail;
	}

	public String getUserLoginUserName() {
		return userLoginUserName;
	}

	public String getPassword() {
		return password;
	}
	public String getUserName() {
		return userName;
	}

	public int getRoleId(){
		return roleId;
	}

	public String getRoleName(){
		return roleName;
	}
	
	public int getActivityId() {
		return activityId;
	}
	
	public String getActivityName() {
		return activityName;
	}
	
	public int getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}


	
	public void setUserId(int id) {
		this.userId = id;
	}
	
	public void setUserEmail(String string) {
		userEmail = string;
	}

	public void setUserLoginUserName(String string) {
		userLoginUserName = string;
	}

	public void setPassword(String string) {
		password = string;
	}

	public void setUserName(String string) {
		userName = string;
	}

	public void setRoleId(int id){
		roleId = id;
	}
	public void setRoleName(String string){
		roleName = string;
	}

	public void setActivityId(int id) {
		this.activityId = id;
	}

	
	public void setActivityName(String string) {
		this.activityName = string;
	}
	
	
	public void setCustomerId(int id) {
		this.customerId = id;
	}

	
	public void setCustomerName(String string) {
		this.customerName = string;
	}
	
	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public HashMap<String, Boolean> getPermissions() {
		return permissions;
	}

	public void setPermissions(HashMap<String, Boolean> permissions) {
		this.permissions = permissions;
	}	
}
