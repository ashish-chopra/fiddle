/**
 * Created on | 18 Nov 2009
 * Author     | Prabhjot Singh Lamba
 **/

package exception;

// Thrown when a column is not found

public class ColumnNotFoundException extends DataException
{
	public ColumnNotFoundException()
	{
		super();
	}

	public ColumnNotFoundException(String s)
	{
		super(s);
	}
}