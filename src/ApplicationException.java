/**
 * The Class ApplicationException is called when there is an error in the
 * program, and will contain an appropriate message based on what the error was.
 * 
 * @author Spencer Niemi
 * @version 1.0 Class: CSS162 Winter 2014 Instructor: Rob Nash
 */
public class ApplicationException extends RuntimeException {

	/**
	 * Instantiates a new application exception with default message
	 */
	public ApplicationException() {
		super("There was an error");
	}

	/**
	 * Instantiates a new application exception with a custom message
	 * 
	 * @param msg
	 *            the custom message
	 */
	public ApplicationException(String msg) {
		super(msg);
	}
}
