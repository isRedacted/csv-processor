package assignment1;

import java.io.IOException;

/**
 * Instantiates the UI class and provides the main loop for the program, only
 * quitting when the quit state is set to true. Also reads the csv file and handles any errors
 */
public class Main {
	private static UI ui = new UI();

	public static void main(String args[]) {
		try {
			getUI().getLogic().readFile("posts.csv", "ID,content,author,likes,shares,date-time");
		} catch (FileFormatException e) {
			System.err.println("Error! The file was formatted incorrectly. No posts were loaded.");
		} catch (IOException e) {
			System.err.println("Error! File not found or file is corrupt. No posts were loaded.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error! One or more posts aren't formatted correctly. Posts partially loaded.");
		}
		run();
	}

	public static void run() {
		while (!ui.getQuitState()) {
			System.out.printf("%n");
			ui.mainMenu();
		}
	}

	public static UI getUI() {
		return ui;
	}

	public void setUI(UI ui) {
		Main.ui = ui;
	}
}
