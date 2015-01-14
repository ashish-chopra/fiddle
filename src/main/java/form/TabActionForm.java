/**
 * Created on | 18 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package form;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
 
public class TabActionForm extends ActionFormAbstract
{
	private static final long serialVersionUID = -8597060683021890396L;
	private String tabName;
	private HashMap<String, Boolean> permissions;
	
	public HashMap<String, Boolean> getPermissions() {
		return permissions;
	}

	public void setPermissions(HashMap<String, Boolean> permissions) {
		this.permissions = permissions;
	}

	public String getTabName() {
		return tabName;
	}

	public void setTabName(String tabName) {
		this.tabName = tabName;
	}
	
	public ActionErrors validate(ActionMapping mapping,HttpServletRequest request) {
		ActionErrors errors = new ActionErrors();
		return errors;
	}
}