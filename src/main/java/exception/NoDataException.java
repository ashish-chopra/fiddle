/**
 * Created on | 18 Nov 2009
 * Author     | Prabhjot Singh Lamba
**/

package exception;

// Thrown if required data is missing

public class NoDataException extends DataException
{
	public NoDataException()
	{
		super();
	}

	public NoDataException(String s)
	{
		super(s);
	}
}