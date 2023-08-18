package assignment1;

/**
 * Exception thrown when the csv file has been formatted incorrectly. This is
 * usually due to the columns declared not being of the expected posts format.
 */
public class FileFormatException extends Exception {
	/**
	 * Generated serial UID
	 */
	private static final long serialVersionUID = 4081589834706391863L;

	public FileFormatException() {
	}

	public FileFormatException(String message) {
		super(message);
	}
}
