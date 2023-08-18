/**
 * 
 */
package assignment1;

/**
 * Exception triggered when user gives an input not in the list of choices in
 * the main menu.
 */
public class BadUserInputException extends Exception {
	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -6736452255314356975L;

	public BadUserInputException() {
	}

	public BadUserInputException(String message) {
		super(message);
	}
}