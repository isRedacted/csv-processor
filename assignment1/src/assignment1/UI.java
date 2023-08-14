package assignment1;

import java.time.format.DateTimeParseException;
import java.util.*;

public class UI {
	private Scanner userInput = new Scanner(System.in); // Reads input from the user in the console
	private String userChoice; // Where the user's choice is stored so the program doesn't prompt the user
								// again unnecessarily
	private boolean quitState = false; // Reads whether the user has opted to quit
	private Logic logic = new Logic(); // The class containing all the backend methods for social media post analysing

	/**
	 * Creates the menu interface and lets the user choose from the list of methods.
	 */
	public void mainMenu() {
		System.out.printf("%nWELCOME TO SOCIAL MEDIA ANALYZER XTREME PRO DELUXE");
		System.out.printf("%n-----------------------------------------------------------------------------");
		System.out.printf("%n> Main menu");
		System.out.printf("%n-----------------------------------------------------------------------------");
		System.out.printf("%n1) Add post");
		System.out.printf("%n2) Delete existing post by ID");
		System.out.printf("%n3) Retrieve post by ID");
		System.out.printf("%n4) Retrieve top N posts by likes");
		System.out.printf("%n5) Retrieve top N posts by shares");
		System.out.printf("%nQ) Quit");
		System.out.printf("%nWhat would you like to do?");
		System.out.printf("%n> ");
		try {
			setUserChoice(getUserInput().next());

			switch (getUserChoice()) {
			case "1": // Add post
				System.out.printf("%nWhat is the ID?");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				int id = Integer.valueOf(getUserChoice());

				System.out.printf("%nWhat is the content of the post?");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				String content = getUserChoice();

				System.out.printf("%nWho is the author?");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				String author = getUserChoice();

				System.out.printf("%nHow many likes does this post have?");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				int likes = Integer.valueOf(getUserChoice());

				System.out.printf("%nHow many shares does this post have?");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				int shares = Integer.valueOf(getUserChoice());

				System.out.printf("%nWhat is the date of the post (In DD/MM/YYYY format)?");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				String dateTime = getUserChoice();
				
				System.out.printf("%nWhat time was it posted (In HH:MM format)?");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				dateTime += " " + getUserChoice();
				if (!getLogic().dateTimeValidator(dateTime)) {
					throw new DateTimeParseException(null, dateTime, 0);
				}

				getLogic().addPost(id, content, author, likes, shares, dateTime);
				break;
			case "2": // Remove post
				System.out.printf("%nWhich post would you like to delete? (Or type \"Q\" to go back)");
				System.out.printf("%n> ");
				getUserChoice();
				break;
			case "3": // Retrieve post
				System.out.printf("%nWhich post would you like to retrieve? (Or type \"Q\" to go back)");
				System.out.printf("%n> ");
				setUserChoice(getUserInput().next());
				getLogic().retrievePost(Integer.valueOf(getUserChoice()));
				break;
			case "4": // Retrieve N posts by likes in descending order
				getLogic().retrievePostsByLikes();
				break;
			case "5": // Retrieve N posts by shares in descending order
				getLogic().retrievePostsByShares();
				break;
			case "q":
			case "Q": // Quit
				setQuitState(true);
				break;
			default:
				System.out.println("Executed");
				throw new BadUserInputException();
			}
		} catch (BadUserInputException e) {
			e.printStackTrace();
			System.out.printf("Please only choose from the shown menu items!");
		} catch (NumberFormatException e) {
			System.out.printf("Please only type numbers for that choice!");
		} catch (DateTimeParseException e) {
			System.out.printf("Please enter a valid date/time in the required format!");
		}
	}

	public boolean getQuitState() {
		return quitState;
	}

	public void setQuitState(boolean quitState) {
		this.quitState = quitState;
	}

	public Scanner getUserInput() {
		return userInput;
	}

	public void setUserInput(Scanner userInput) {
		this.userInput = userInput;
	}

	public String getUserChoice() {
		return userChoice;
	}

	public void setUserChoice(String userChoice) {
		this.userChoice = userChoice;
	}

	public Logic getLogic() {
		return logic;
	}

	public void setLogic(Logic logic) {
		this.logic = logic;
	}
}