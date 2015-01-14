////////////////////////////////////////////////////////////////////////////////
//
//  ERICSSON INDIA PRIVATE LIMITED.
//  Copyright 2011-2012 Ericsson India Private Limited.
//  All Rights Reserved.
//     
//  
//  Initial Developer : Ashish Chopra, Naseem Malik
//  Contributor       : None
//  Date Created      : 03/02/2011
//  Last Modified     : 07/03/2011
//
//
//  NOTICE: Ericsson permits you to use, modify, and distribute this file
//  in accordance with the terms of the license agreement accompanying it.
//
////////////////////////////////////////////////////////////////////////////////

package action;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import util.StringUtil;
import util.UserProfile;
import constants.Constants;
import form.TabActionForm;

public class TabAction extends StrutsAction
{
	private static Log logger = LogFactory.getLog(TabAction.class);

	public ActionForward execute( 
			ActionMapping mapping, 
			ActionForm form, 
			HttpServletRequest request,
			HttpServletResponse response)throws java.io.IOException, javax.servlet.ServletException
		{

			String forward = Constants.LOGIN_FORWARD;
			TabActionForm currentForm = null;
			currentForm = (TabActionForm) form;
		    String tabName= currentForm.getTabName();
		    request.setAttribute("tabName", currentForm.getTabName());

		    logger.info("Tab Name : " + tabName);
			//Check If User Is Valid
			ActionForward actionForward = checkUser(mapping, request, response);
			if(actionForward!=null) return actionForward;

			currentForm.setPermissions(((UserProfile)request.getSession().getAttribute(Constants.USER_KEY)).getPermissions());
			request.setAttribute(Constants.USER_PERMISSIONS,((UserProfile)request.getSession().getAttribute(Constants.USER_KEY)).getPermissions());
			request.setAttribute("username", ((UserProfile)request.getSession().getAttribute(Constants.USER_KEY)).getUserName());

			if(StringUtil.isEmpty(tabName)){ //No tabName. So Assuming it is success Window
				forward=Constants.SUCCESS;
			}
			
			logger.info("forward: " + forward);
		
			return executeAtEnd(mapping, form, request, response, mapping.findForward(forward));
		}
}