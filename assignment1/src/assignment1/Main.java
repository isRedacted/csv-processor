package assignment1;

/**
 * Instantiates the UI class and provides the main loop for the program, only
 * quitting when the quit state is set to true
 */
public class Main {
	public static void main(String args[]) {
		UI ui = new UI();
		ui.getLogic().readFile();
		while (!ui.getQuitState()) {
			ui.mainMenu();
		}
	}
}
