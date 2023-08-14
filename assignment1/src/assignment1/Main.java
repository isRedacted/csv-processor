package assignment1;

public class Main {
	public static void main(String args[]) {
		UI ui = new UI();
		ui.getLogic().readFile();
		while (!ui.getQuitState()) {
			ui.mainMenu();
		}
	}
}
