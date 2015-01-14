
package action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;

import util.StringUtil;
import util.UserProfile;
import constants.Constants;

public abstract class StrutsAction extends Action
{
	

	public abstract ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		throws java.io.IOException, javax.servlet.ServletException;
	
	public ActionForward checkUser(
		ActionMapping mapping,
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		{
			HttpSession session = request.getSession();
			UserProfile userProfile = (UserProfile)session.getAttribute(Constants.USER_KEY);
			if(session.isNew() || userProfile==null)
			{
				ActionMessages errors = new ActionMessages();
				errors.add("sessionExpired", new ActionMessage("errors.global.accessDenied"));
				saveErrors(request, errors);
				return mapping.findForward(Constants.INVALID_SESSION);
			}
		return null;	
		}

	public boolean checkUserPermission(
			javax.servlet.http.HttpServletRequest request,
			String Permission)
			{
				try
				{
					if(StringUtil.isEmpty((((UserProfile)request.getSession().getAttribute(Constants.USER_KEY)).getPermissions()).get(Permission)))
						return false;
					if((((UserProfile)request.getSession().getAttribute(Constants.USER_KEY)).getPermissions()).get(Permission))
						return true;
					else
						return false;
				}
				catch(Exception e)
				{
					return false;
				}
			}
	
	public ActionForward checkUserWhileLogIn(
		ActionMapping mapping,
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response)
		{
		    System.out.println("->Inside StrutsAction checkUserWhileLogIn() method.");
			HttpSession session = request.getSession();
			if(session.isNew())
			{
				ActionMessages errors = new ActionMessages();
				errors.add("sessionExpired", new ActionMessage("errors.global.accessDenied"));
				saveErrors(request, errors);
				System.out.println("XX->Returning invalidSession from StrutsAction.");
				return mapping.findForward(Constants.INVALID_SESSION);
			}
		return null;	
		}

	public Locale getLocale(HttpServletRequest request, HttpSession session)
	{
		String sessionLocale = (String)session.getAttribute(Constants.LOCALE);
		String country = (String)session.getAttribute(Constants.COUNTRY);
		String requestLocale = request.getParameter(Constants.LOCALE);
		String requestCountry = request.getParameter(Constants.COUNTRY);
		if(requestLocale!=null)
		{
			sessionLocale = requestLocale;
		}
		else if(sessionLocale==null) 
		{
			sessionLocale = "en";
			country = "EN";
		}

		if(requestCountry!=null) 
		{
			country = requestCountry;
		}
		else if(country==null)
		{
			sessionLocale = "en";
			country = "EN";
		}
		Locale myLocale = new Locale(sessionLocale, country);
		session.setAttribute(Constants.LOCALE, sessionLocale);
		session.setAttribute(Constants.COUNTRY, country);
		session.setAttribute(Constants.LOCALE_OBJECT, myLocale);
		setLocale(request,myLocale);
		return myLocale;
	}

	protected HttpSession getValidSession(HttpServletRequest request) 
	{
		return request.getSession(false);
	}
	
	protected String validateSession(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if( session!=null && session.isNew() ) {
			session.removeAttribute(Constants.USER_KEY);
			System.out.println("Session Expire");
			return "sessionExpire";
		}
		return null;
	}

	protected ActionForward executeAtEnd(
		ActionMapping mapping,
		ActionForm form,
		javax.servlet.http.HttpServletRequest request,
		javax.servlet.http.HttpServletResponse response,
		ActionForward forward)
		{
			return forward;
		}

}