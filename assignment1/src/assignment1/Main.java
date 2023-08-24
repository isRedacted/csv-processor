package assignment1;

/**
 * Instantiates the UI class and provides the main loop for the program, only
 * quitting when the quit state is set to true
 */
public class Main {
	private static UI ui = new UI();

	public static void main(String args[]) {
		getUI().getLogic().readFile("posts.csv", "ID,content,author,likes,shares,date-time");
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
