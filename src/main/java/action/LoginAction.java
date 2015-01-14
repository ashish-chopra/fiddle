
package action;

import javax.servlet.http.HttpSession;

import org.apache.log4j.*;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import util.StringUtil;
import util.UserProfile;
import constants.Constants;
import controller.LoginController;
//import controller.UserController;

import form.LoginActionForm;

public class LoginAction extends StrutsAction
{
	private static Logger logger = Logger.getLogger(LoginAction.class);

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws java.io.IOException, javax.servlet.ServletException
		{
			String forward = Constants.LOGIN_FORWARD;
			LoginActionForm currentForm = null;
			currentForm = (LoginActionForm) form;
			ActionMessages errors= new ActionMessages();
			HttpSession session = getValidSession(request);
			
		    String userName= currentForm.getUserName();
			String passWord = currentForm.getPassWord();
			String subAction= currentForm.getSubAction();
			
			if(StringUtil.isEmpty(subAction))
			{
				logger.info("entered inside empty block");
				
				 LoginController loginController=new LoginController();
				try 
				{				
					UserProfile userProfile = loginController.authenticateUser(userName,passWord);

					session.setAttribute(Constants.USER_KEY,userProfile);
					request.setAttribute("userId",((UserProfile)session.getAttribute(Constants.USER_KEY)).getUserId());
	
					forward=Constants.SUCCESS;
					
				}
				catch (Exception e) 
				{
					errors.add("authenticationFailed", new ActionMessage("errors.login.authenticationFailed"));
					forward = Constants.LOGIN_FORWARD;
					logger.error("Got An Exception In Login Action Class. Error : " + e.getMessage());
				}
			}
			else if(subAction.equals(Constants.CHANGE_PASSWORD))
			{
				logger.info("-->Going to save Password");
				logger.info("New Password is :" + passWord);
				LoginController loginController= new LoginController();
				try
				{
					loginController.savePassword(((UserProfile)request.getSession().getAttribute(Constants.USER_KEY)).getUserId(), passWord);
					response.setContentType("text/html");
					response.getWriter().println("Your Password has been changed.");
				}
				catch(Exception e)
				{
					e.printStackTrace();
					
				}
				return null;
			}
			else
			{
				//destroy session
				request.getSession().invalidate();
				forward = Constants.LOGIN_FORWARD;
			}	
			
			saveErrors(request,errors);
			return executeAtEnd(mapping, form, request, response, mapping.findForward(forward));
		}
}
