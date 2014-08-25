
package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import valueObject.LoginVO;

public class UserProfile 
{
    private int userId;
	private String userName;
    private String userLoginUserName;
	private String password;
	private String userEmail;
	private int roleId;
	private String roleName;
	private int activityId;
	private String activityName;
	private int customerId;
	private String customerName;
	private int requestType;
	
	private HashMap<String, Boolean> permissions;
	//private ArrayList<LableValueBean> circleList;

	public UserProfile(LoginVO loginVO)	{
		setUserId(loginVO.getUserId());
		setUserName(loginVO.getUserName());
		setUserLoginUserName(loginVO.getUserLoginUserName());
		setPassword(loginVO.getPassword());
		setUserEmail(loginVO.getUserEmail());
		setPermissions(loginVO.getPermissions());
		setActivityId(loginVO.getActivityId());
		setActivityName(loginVO.getActivityName());
		setRoleId(loginVO.getRoleId());
		setRoleName(loginVO.getRoleName());
		setCustomerId(loginVO.getCustomerId());
		setCustomerName(loginVO.getCustomerName());
		setRequestType(loginVO.getRequestType());
	}
	
	public int getUserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public String getUserLoginUserName() {
		return userLoginUserName;
	}

	public String getUserName() {
		return userName;
	}

	public int getRoleId() {
		return roleId;
	}

	public String getRoleName() {
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
	
	public HashMap<String, Boolean> getPermissions() {
		return permissions;
	}


	
	// setters for properties of class
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setPassword(String string) {
		password = string;
	}

	public void setUserEmail(String string) {
		userEmail = string;
	}

	public void setUserLoginUserName(String string) {
		userLoginUserName = string;
	}

	public void setUserName(String string) {
		userName = string;
	}

	public void setRoleId(int id) {
		this.roleId = id;
	}
	
	public void setRoleName(String string) {
		this.roleName = string;
	}
	
	public void setActivityId(int id) {
		this.activityId = id;
	}
	
	public void setActivityName(String string) {
		this.activityName = string;
	}
	
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public void setPermissions(HashMap<String, Boolean> permissions) {
		this.permissions = permissions;
	}

	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	// TODO : not updated with roles, activities and customer list
	public String toString() {
		StringBuilder str= new StringBuilder("");
		str.append("User ID: " + this.userId + "\n" + 
				"User Name: " + this.userName+ "\n" +
				"User Login Name : " + this.userLoginUserName + "\n" +
				"User Email : " + this.userEmail + "\nPermissions :"); 
		Set<String> keys=this.permissions.keySet();
		Iterator<String> it= keys.iterator();
		Object key;
		while(it.hasNext()){
			key= it.next();
			str.append("\n\t " + key  + " - " + permissions.get(key).toString());
		}
		return str.toString();
	}
}