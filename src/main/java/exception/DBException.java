/**
 * Created on | 11 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package exception;

import exception.SystemException;

public class DBException extends SystemException
{
	private static final long serialVersionUID = 367809787802866668L;

	public DBException() 
	{
		super();
	}
	
	/**
 	* @param n Integer the error number
 	* @param s String the error message
 	*/
	public DBException(Integer n, String s) 
	{
		super(n,s);
	}
	
	/**
 	* @param s String The error message
 	*/
	public DBException(String s) 
	{
		super(s);
	}
}