/**
 * 
 */
package assignment1;

/**
 * Exception triggered when user inputs content into a new post but which
 * contains a forbidden character.
 */
public class ContentFormatException extends Exception {
	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = -2708352986814084989L;

	public ContentFormatException() {
	}

	public ContentFormatException(String message) {
		super(message);
	}
}