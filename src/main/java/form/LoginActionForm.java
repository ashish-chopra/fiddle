/**
 * Created on | 5 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package form;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import util.StringUtil;
 
public class LoginActionForm extends ActionFormAbstract
{
	private static final long serialVersionUID = 6062325194158164295L;
	private String userName="";
	private String passWord="";
	private String language;
	private String subAction;

	public String getLanguage() {
		return language;
	}

	public String getPassWord() {
		return passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setLanguage(String string) {
		language = string;
	}

	public void setPassWord(String string) {
		passWord = string;
	}

	public void setUserName(String string) {
		userName = string;
	}

	public String getSubAction() {
		return subAction;
	}

	public void setSubAction(String string) {
		subAction = string;
	}

	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
	    try
		{
			if(request.getParameter("subAction")== null)
			{
				if(StringUtil.isEmpty(this.getUserName().trim()))
					errors.add("userName", new ActionMessage("errors.login.emptyUserName"));
				if(StringUtil.isEmpty(this.getPassWord().trim()))
					errors.add("passWord", new ActionMessage("errors.login.emptyPassWord"));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	
		return errors;
	}

}