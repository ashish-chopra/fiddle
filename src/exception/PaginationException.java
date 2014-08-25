package exception;

/**
 * Created on | 12 Jan 2009
 * Author     | Prabhjot Singh Lamba
 * This is a generic Exception used for Pagination.
 */

public class PaginationException extends Exception {
	private static final long serialVersionUID = 1L;

	public PaginationException() {
		super();
	}

	public PaginationException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public PaginationException(String arg0) {
		super(arg0);
	}

	public PaginationException(Throwable arg0) {
		super(arg0);
	}
}