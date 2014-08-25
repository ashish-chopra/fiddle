/**
 * Created on | 5 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public abstract class ActionFormAbstract extends ActionForm {
	
	private static final long serialVersionUID = 7313165833602926788L;
	public abstract ActionErrors validate(ActionMapping mapping,HttpServletRequest request);
}
