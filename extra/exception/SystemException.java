/**
 * Created on | 11 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package exception;

//Generic Exception thrown by the application

public class SystemException extends Exception
{
	private static final long serialVersionUID = -8451784047350568538L;
	private Integer number;

	public SystemException() 
	{
		super();
	}

	/**
 	* @param no Integer the error number 
 	* @param msg String The error message
 	*/
	public SystemException(Integer no, String msg)
	{
		super(msg);
		this.number = no;
	}
	
	/**
 	* @param msg String The error message
 	*/
	public SystemException(String msg)
	{
		super(msg);
	}

	public SystemException(Integer param) 
	{
		number = param;
	}

	public Integer getNum() 
	{
		return number;
	}
}