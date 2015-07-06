package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

public class MarkersActionForm extends ActionFormAbstract{
	
	private static final long serialVersionUID = 6062325194158164295L;
	private String subAction = "";
	
	public void setSubAction(String string) {
		subAction = string;
	}
	
	public String getSubAction() {
		return subAction;
	}
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
	    
	
		return errors;
	}

}
