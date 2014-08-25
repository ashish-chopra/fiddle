/**
 * Created on | 31 Dec 2009
 * Author     | Prabhjot Singh Lamba
**/

package exception;

public class XLSParseException extends CommandExecuteException 
{

	public XLSParseException() 
	{
		super();
	}

	public XLSParseException(Integer n, String param)
	{
		super(n, param);
	}
}
