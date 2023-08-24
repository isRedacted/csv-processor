package assignment1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
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
	public void readFile(String filename, String format) {
		try {
			List<String> readPosts = Files.readAllLines(Paths.get(filename));

			if (!readPosts.get(0).equals(format)) {
				throw new FileFormatException();
			}

			for (int i = 1; i < readPosts.size(); i++) {
				String[] splitPost = readPosts.get(i).split(",");

				Post newPost = new Post(Integer.parseInt(splitPost[0]), splitPost[1], splitPost[2],
						Integer.parseInt(splitPost[3]), Integer.parseInt(splitPost[4]), splitPost[5]);
				posts.put(newPost.getid(), newPost);
			}

		} catch (FileFormatException e) {
			System.err.println("Error! The file was formatted incorrectly. No posts were loaded.");
		} catch (IOException e) {
			System.err.println("Error! File not found or file is corrupt. No posts were loaded.");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Error! One or more posts aren't formatted correctly. Posts partially loaded.");
		}
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
		if (getPosts().get(id) == null) {
			throw new NullPointerException();
		} else {
			getPosts().remove(id);
		}
	}

	/**
	 * Returns a post based on ID. Or, if the post is not found, prints a warning to
	 * the user.
	 * 
	 * @param id
	 * @return The post requested as per the ID, otherwise throws a
	 *         NullPointerException
	 */
	public Post retrievePost(int id) {
		if (getPosts().get(id) == null) {
			throw new NullPointerException();
		} else {
			return getPosts().get(id);
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
