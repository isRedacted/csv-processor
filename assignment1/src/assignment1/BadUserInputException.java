/**
 * 
 */
package assignment1;

/**
 * Exception triggered when user gives an input not in the list of choices in
 * the main menu
 */
public class BadUserInputException extends Exception {
	public BadUserInputException() {
	}

	public BadUserInputException(String message) {
		super(message);
	}
}