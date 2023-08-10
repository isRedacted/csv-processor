package assignment1;

import java.util.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class UI {
	private Scanner userInput = new Scanner(System.in); // Reads input from the user in the console
	private String userChoice; // Where the user's choice is stored so the program doesn't prompt the user
								// again unnecessarily
	private HashMap<Integer, Post> posts = new HashMap<Integer, Post>(); // The hashmap for all of the csv posts
	private boolean quitState = false; // Reads whether the user has opted to quit

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
			userChoice = userInput.next();

			switch (userChoice) {
			case "1":
				addPost();
				break;
			case "2":
				removePost();
				break;
			case "3":
				retrievePost();
				break;
			case "4":
				retrievePostsByLikes();
				break;
			case "5":
				retrievePostsByShares();
			case "q":
			case "Q":
				setQuitState(true);
				break;
			default:
				throw new BadUserInputException();
			}
		} catch (BadUserInputException e) {
			System.out.printf("Please only choose from the shown menu items!");
		}
	}

	/**
	 * Reads the posts.csv file, splits it by the comma delimiter, and creates new
	 * post objects into the posts hashmap. Catches exceptions related to file not
	 * found, and file improperly formatted.
	 */
	public void readFile() {
		try {
			List<String> readPosts = Files.readAllLines(Paths.get("posts.csv"));

			if (!readPosts.get(0).equals("ID,content,author,likes,shares,date-time")) {
				throw new FileFormatException();
			}

			for (int i = 1; i < readPosts.size(); i++) {
				String[] splitPost = readPosts.get(i).split(",");

				Post newPost = new Post(Integer.parseInt(splitPost[0]), splitPost[1], splitPost[2],
						Integer.parseInt(splitPost[3]), Integer.parseInt(splitPost[4]), splitPost[5]);
				posts.put(newPost.getid(), newPost);
			}

		} catch (FileFormatException e) {
			System.out.println(
					"Error! One or more of the posts in the file was formatted incorrectly. Posts only partially loaded.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error! File not found or file is corrupt. No posts were loaded.");
		} // TODO: Catch exception for when a record is improperly formatted
	}

	/**
	 * Adds a post from user specifications. Checks for validity of user data.
	 */
	public void addPost() {
	}

	/**
	 * Removes a post based on ID, throws an error if ID is not found in the
	 * hashmap.
	 */
	public void removePost() {
		int id;
		System.out.printf("%nWhich post would you like to delete? (Or type \"Q\" to go back)");
		System.out.printf("%n> ");
		try {
			id = userInput.nextInt();
			if (getPosts().get(id) == null) {
				throw new NullPointerException();
			}
			getPosts().remove(id);
		} catch (NullPointerException | InputMismatchException e) {
			System.out.printf("%nThat post wasn't found!");
		}
	}

	/**
	 * Retrieves a post based on ID, throws an error if ID is not found in the
	 * hashmap.
	 */
	public void retrievePost() {
		int id;
		System.out.printf("%nWhich post would you like to retrieve? (Or type \"Q\" to go back)");
		System.out.printf("%n> ");
		try {
			id = userInput.nextInt();
			if (getPosts().get(id) == null) {
				throw new NullPointerException();
			}
			System.out.print(getPosts().get(id));
		} catch (NullPointerException | InputMismatchException e) {
			System.out.printf("%nThat post wasn't found!");
		}
	}

	/**
	 * 
	 */
	public void retrievePostsByLikes() {
	}

	/**
	 * 
	 */
	public void retrievePostsByShares() {
	}

	public boolean dateTimeValidator(String dateTime) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
		try {
			LocalDate.parse(dateTime, dateTimeFormatter);
			return true;
		} catch (DateTimeParseException e) {
			return false;
		}
	}

	public HashMap<Integer, Post> getPosts() {
		return posts;
	}

	public void setPosts(HashMap<Integer, Post> posts) {
		this.posts = posts;
	}

	public boolean getQuitState() {
		return quitState;
	}

	public void setQuitState(boolean quitState) {
		this.quitState = quitState;
	}
}
