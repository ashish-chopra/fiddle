/**
 * Created on | 13 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package controller;


import util.StringUtil;
import util.UserProfile;
import valueObject.LoginVO;
import contentModel.LoginCM;
import data.DBManager;
import exception.DBException;
import exception.DataException;

public class LoginController
{

	public UserProfile authenticateUser(String userLoginName, String password) throws DataException , DBException
	{
		DBManager db = DBManager.init();
		LoginVO loginVO = new LoginVO();
		db.doConnect();
		LoginCM loginCM = new LoginCM(db);
		loginVO.setUserLoginUserName(userLoginName);
		loginVO.setPassword(password);
		copyToCM(loginCM,loginVO);
		try
		{
			loginCM.validatePassword();
			loginCM.fetchPermissions();
		}
		catch(DataException e)
		{
			throw e;
		}
		finally
		{
			try{
			db.doDisconnect();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		copyToVO(loginCM,loginVO);
		return new UserProfile(loginVO);
	}

	public void savePassword(int loginUserID, String password) throws DataException , DBException
		{
			DBManager db = DBManager.init();
			db.doConnect();
			LoginCM loginCM = new LoginCM(db);
			loginCM.setPassword(password);
			loginCM.setUserId(loginUserID);
			try
			{
				loginCM.savePassword();
			}
			catch(DataException e)
			{
				throw e;
			}
			finally
			{
				try{
				db.doDisconnect();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
	}

	public void copyToVO(LoginCM loginCM, LoginVO loginVO)
	{
		loginVO.setUserLoginUserName(StringUtil.parseSQLString(loginCM.getUserLoginUserName()));
		loginVO.setPassword(StringUtil.parseSQLString(loginCM.getPassword()));
		loginVO.setUserName(StringUtil.parseSQLString(loginCM.getUserName()));
		loginVO.setUserEmail(StringUtil.parseSQLString(loginCM.getUserEmail()));
		loginVO.setPermissions(loginCM.getPermissions());
		loginVO.setUserId(loginCM.getUserId());
		loginVO.setRoleId(loginCM.getRoleId());
		loginVO.setRoleName(StringUtil.parseSQLString(loginCM.getRoleName()));
		loginVO.setActivityId(loginCM.getActivityId());
		loginVO.setActivityName(StringUtil.parseSQLString(loginCM.getActivityName()));
		loginVO.setCustomerId(loginCM.getCustomerId());
		loginVO.setCustomerName(StringUtil.parseSQLString(loginCM.getCustomerName()));
		loginVO.setRequestType(loginCM.getRequestType());
	}

	public void copyToCM(LoginCM loginCM, LoginVO loginVO)
	{
		loginCM.setUserLoginUserName(StringUtil.parseSQLString(loginVO.getUserLoginUserName()));
		loginCM.setPassword(StringUtil.parseSQLString(loginVO.getPassword()));
		loginCM.setUserName(StringUtil.parseSQLString(loginVO.getUserName()));
		loginCM.setUserEmail(StringUtil.parseSQLString(loginVO.getUserEmail()));
		loginCM.setPermissions(loginVO.getPermissions());
		loginCM.setUserId(loginVO.getUserId());
		loginCM.setRoleId(loginVO.getRoleId());
		loginCM.setRoleName(StringUtil.parseSQLString(loginVO.getRoleName()));
		loginCM.setActivityId(loginVO.getActivityId());
		loginCM.setActivityName(StringUtil.parseSQLString(loginVO.getActivityName()));
		loginCM.setCustomerId(loginVO.getCustomerId());
		loginCM.setCustomerName(StringUtil.parseSQLString(loginVO.getCustomerName()));
		
	}
}