package assignment1;

public class Main {
	public static void main(String args[]) {
		UI ui = new UI();
		ui.readFile();
		while (!ui.getQuitState()) {
			ui.mainMenu();
		}
	}
}
