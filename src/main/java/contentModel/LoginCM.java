/**
 * Created on | 13 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package contentModel;

import java.util.HashMap;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import util.StringUtil;
import data.DBManager;
import data.DataSet;
import exception.DBException;
import exception.DataException;

public class LoginCM 
{
	private static Log logger = LogFactory.getLog(LoginCM.class);
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
	private DBManager db;
	
	public LoginCM(DBManager db)
	{		
		this.db = db;		
	}

	// ******************************************************************
	// This method validates the user login credentials against
	// authorized entry inside database and fetch its data like name,
	// email, role information, activity information and so on.
	// *******************************************************************
	
	public void validatePassword() throws DataException
	{
				
		String query = " SELECT a.userid, a.username, a.userloginid, a.userpasswd, a.useremail, a.userphone, "+
		               " c.roleid, c.role, d.activityid, d.activity, e.customerid, e.customer, d.request_type " +  
				       " FROM mizo_user_master a " +
				       " LEFT OUTER JOIN mizo_user_role_mapping b ON a.userid = b.userfk "+
				       " LEFT OUTER JOIN mizo_role_master c ON c.roleid=b.rolefk " + 
				       " LEFT OUTER JOIN mizo_activity_master d ON d.activityid=b.activityfk " +
				       " LEFT OUTER JOIN mizo_customer_master e ON e.customerid=b.customerfk " +
				       " WHERE lower(a.userloginid)='" + StringUtil.getSQLString(this.getUserLoginUserName().toLowerCase().trim())  + "' " +
				       " and a.userpasswd='" + StringUtil.getSQLString(this.getPassword()) + "'";
		
			logger.info("-> [validatePassword] "+ query);			   
			DataSet ds = db.getDataSetFromSQL(query);
			if(ds==null) throw new DataException();
			try {
				logger.info("\n\n\nChecking Data:inside CM before setting variables\n\n\n");
				this.setUserId(Integer.parseInt((String)ds.getValueAt(0, 0)));
				this.setUserName((String)ds.getValueAt(0, 1));	
				this.setUserLoginUserName((String)ds.getValueAt(0, 2));
				this.setUserEmail((String)ds.getValueAt(0, 4));
				this.setRoleId(Integer.parseInt((String)ds.getValueAt(0, 6)));
				this.setRoleName((String)ds.getValueAt(0, 7));
				
				// condition to check if activity is null
				if(StringUtil.isEmpty(ds.getValueAt(0, 8))){
					this.setActivityId(0);
					this.setActivityName("");
				}
				else{
					this.setActivityId(Integer.parseInt((String)ds.getValueAt(0, 8)));
					this.setActivityName((String)ds.getValueAt(0, 9));
				}
				if(StringUtil.isEmpty(ds.getValueAt(0, 10))){
					this.setCustomerId(0);
					this.setCustomerName("");
				}
				else{
					this.setCustomerId(Integer.parseInt((String)ds.getValueAt(0, 10)));
					this.setCustomerName((String)ds.getValueAt(0, 11));
				}
				logger.info("request type : " + (String)ds.getValueAt(0, 12));
				if(!StringUtil.isEmpty((String)ds.getValueAt(0, 12))){
				this.setRequestType(Integer.parseInt((String)ds.getValueAt(0, 12)));
				}
				//printing Data start
					logger.info("userId:"+userId+"\n");
					logger.info("userName:"+userName+"\n");
					logger.info("userLoginUserName:"+userLoginUserName+"\n");
					logger.info("userEmail:"+userEmail+"\n");
					logger.info("roleId:"+roleId+"\n");
					logger.info("roleName:"+roleName+"\n");
					logger.info("activityId:"+activityId+"\n");
					logger.info("activityName:"+activityName+"\n");
					logger.info("customerId:"+customerId+"\n");
					logger.info("customerName:"+customerName+"\n");
					
				//printing data end
				
				logger.info("\n\n\nChecking Data:inside CM after setting variables\n\n\n");
			}
			catch(Exception e) {
				throw new DataException(e.getMessage());
			}
	}
	
	
	// ****************************************************
	// used when user asks to change password.
	// ****************************************************
	
	public void savePassword() throws DataException, DBException
	{
			String query = " UPDATE mizo_user_master " + 
			               " SET userpasswd='" + StringUtil.getSQLString(this.getPassword()) + "' " + 
			               " WHERE userid=" + this.getUserId();
			
			logger.info("-> "+ query);			   
			db.doUpdate(query);
			db.doCommit();
	}	

	
	// *******************************************************
	// This method fetches the permissions for user based on 
	// his role and set them into HASHMAP data structure.
	// *******************************************************
	
	public void fetchPermissions() throws DataException
	{
			String query = " SELECT permission from mizo_access_permissions " +
				           " WHERE accessid IN (SELECT accessfk FROM mizo_role_access_mapping WHERE rolefk = " + this.getRoleId()+ ")";
			
			logger.info("-> [fetch permissions] "+ query);			   
			DataSet ds = db.getDataSetFromSQL(query);
			try	{
				for(int i=0;i<ds.getRows().size();i++)
				{
					this.addPermission((String)ds.getValueAt(i, 0));
				
				}
				//saveLog();
			}
			catch(Exception e) {
				throw new DataException(e.getMessage());
			}
	}
	
	// ************************************************
	// This method adds a string type permission value
	// in the HASHMAP data structure
	// *************************************************
	
	public void addPermission(String permission){
		permissions.put(permission,true);
	}
	
	// **********************************
	// GETTERS for the data variables 
	// **********************************
	
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
	
	
	// *****************************************
	// SETTERS for data variables for LoginCM 
	// *****************************************
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public void setPassword(String string) {
		password = string;
	}
	
	public void setUserEmail(String string) {
		userEmail = string;
	}
	
	public void setUserName(String string){
		userName = string;
	}
	
	public void setUserLoginUserName(String string) {
		userLoginUserName = string;
	}

	public void setActivityId(int activityId) {
		this.activityId = activityId;
	}
	
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public void setRoleName(String string) {
		roleName = string;
	}
	
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	public int getRequestType() {
		return requestType;
	}

	public void setRequestType(int requestType) {
		this.requestType = requestType;
	}

	public void setPermissions(HashMap<String, Boolean> permissions) {
		this.permissions = permissions;
	}

}