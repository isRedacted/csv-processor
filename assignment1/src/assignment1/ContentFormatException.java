/**
 * 
 */
package assignment1;

/**
 * Exception triggered when user gives an input not in the list of choices in
 * the main menu
 */
public class ContentFormatException extends Exception {
	public ContentFormatException() {
	}

	public ContentFormatException(String message) {
		super(message);
	}
}