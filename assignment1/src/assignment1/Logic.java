package assignment1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Handles the logic of the program. Methods relate to reading the csv file and
 * editing or retrieving posts in the posts HashMap.
 */
public class Logic {
	private HashMap<Integer, Post> posts = new HashMap<Integer, Post>(); // The hashmap for all of the csv posts

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
	 * Adds a post to the posts HashMap from input specifications.
	 * 
	 * @param id
	 * @param content
	 * @param author
	 * @param likes
	 * @param shares
	 * @param dateTime
	 */
	public void addPost(int id, String content, String author, int likes, int shares, String dateTime) {
		getPosts().put(id, new Post(id, content, author, likes, shares, dateTime));
		System.out.printf("%nPost successfully added!");
	}

	/**
	 * Removes a post based on ID, warns the user if the ID is not found in the
	 * hashmap.
	 */
	public void removePost(int id) {
		try {
			if (getPosts().get(id) == null) {
				throw new NullPointerException();
			}
			getPosts().remove(id);
			System.out.printf("%nPost " + id + " has been deleted!");
		} catch (NullPointerException | InputMismatchException e) {
			System.out.printf("%nThat post wasn't found!");
		}
	}

	/**
	 * Prints a post based on ID. Or, if the post is not found, prints a warning to
	 * the user.
	 * 
	 * @param id
	 * @return The post requested by the user. Or returns null if the post isn't
	 *         found.
	 */
	public void retrievePost(int id) {
		try {
			if (getPosts().get(id) == null) {
				throw new NullPointerException();
			}
			System.out.print(getPosts().get(id));
		} catch (NullPointerException e) {
			System.out.printf("%nThat post wasn't found!");
		}
	}

	/**
	 * Prints a user specified amount of posts, sorted by either likes or shares
	 * based on the the flag.
	 * 
	 * Compares each post within a temporary posts hashmap and selects a value,
	 * printing it out and removing it from the temporary hashmap. Repeats until
	 * user specification is satisfied or it runs out of posts.
	 * 
	 * @param n
	 * @param flag
	 */
	public void retrieveNPosts(int n, String flag) {
		HashMap<Integer, Post> tempPosts = getPosts();
		if (n > getPosts().size()) {
			n = getPosts().size();
		}
		Post currentHighest = null;
		if (flag.equals("likes")) {
			for (int i = 0; i < n; i++) {
				for (Post key : tempPosts.values()) {
					if (currentHighest == null) {
						currentHighest = key;
					} else if (currentHighest.getLikes() < key.getLikes()) {
						currentHighest = key;
					}
				}
				System.out.printf("%n" + currentHighest);
				tempPosts.remove(currentHighest.getid());
				currentHighest = null;
			}
		} else if (flag.equals("shares")) {
			for (int i = 0; i < n; i++) {
				for (Post key : tempPosts.values()) {
					if (currentHighest == null) {
						currentHighest = key;
					} else if (currentHighest.getShares() < key.getShares()) {
						currentHighest = key;
					}
				}
				System.out.printf("%n" + currentHighest);
				tempPosts.remove(currentHighest.getid());
				currentHighest = null;
			}
		}
	}

	/**
	 * Checks the date and time of the string to ensure it matches a "d/M/yyyy
	 * HH:mm" datetime format
	 * 
	 * @param dateTime
	 * @return True if the date has been validated as the correct format. False if
	 *         not.
	 */
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

}
