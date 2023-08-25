package assignment1;

/**
 * Exception thrown if user attempts to add a post that already exists in the
 * posts hashmap
 */
public class DuplicatePostException extends Exception {
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = -7884667136728976122L;

	public DuplicatePostException() {
	}

	public DuplicatePostException(String message) {
		super(message);
	}
}
