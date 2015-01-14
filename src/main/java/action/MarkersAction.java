package action;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;

import constants.Constants;
import controller.MarkersController;
import form.MarkersActionForm;

public class MarkersAction extends StrutsAction
{
	private static Logger logger = Logger.getLogger(MarkersAction.class);
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws java.io.IOException, javax.servlet.ServletException
			{
				String forward = null;
				MarkersActionForm currentForm = null;
				currentForm = (MarkersActionForm) form;
				ActionMessages errors= new ActionMessages();
				//HttpSession session = getValidSession(request);
				
				String subAction= currentForm.getSubAction();
				if(subAction.equals("listMarkers"))
				{
					logger.info("-->Going to get all markers data for map");
					
					response.setContentType(Constants.JSON_CONTENT_TYPE);
					JSONObject json = new JSONObject();
					JSONArray jsonArray = null;
					
					MarkersController markersController= new MarkersController();
					
					try
					{
						jsonArray = JSONArray.fromObject(markersController.getAllMarkersData());
						
						json.put("status", "success");
						json.put("data", jsonArray);
					}
					catch(Exception e)
					{
						json.put("status", "exception");
						e.printStackTrace();
						
					}
					forward = null;
					response.getWriter().println(json);
				}
				
				return executeAtEnd(mapping, form, request, response, mapping.findForward(forward));
			}

}
