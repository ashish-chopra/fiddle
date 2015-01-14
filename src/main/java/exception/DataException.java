/**
 * Created on | 5 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package exception;
import exception.SystemException;

public class DataException extends SystemException
{
	private static final long serialVersionUID = 3536548912656118556L;

	public DataException() 
	{
		super();
	}
	
	public DataException(String param) 
	{
		super(param);
	}
}