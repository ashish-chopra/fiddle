/**
 * Created on | 31 Dec 2009
 * Author     | Prabhjot Singh Lamba
 **/

package exception;

public class CommandExecuteException extends SystemException 
{

	public CommandExecuteException() 
	{
		super();
	}

	public CommandExecuteException(Integer n, String s) 
	{
		super(n,s);
	}

	public CommandExecuteException(String s) 
	{
		super(s);
	}
}