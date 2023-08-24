package assignment1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	private Logic logic;
	private ByteArrayOutputStream errorMessage;
	private ByteArrayOutputStream outputMessage;

	@Before
	public void setUp() {
		logic = new Logic();
		errorMessage = new ByteArrayOutputStream();
		outputMessage = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errorMessage));
		System.setOut(new PrintStream(outputMessage));
	}

	@After
	public void tearDown() {
		System.setErr(System.err);
		System.setOut(System.out);
	}

	@Test
	public void testReadFile_FileNotFound() {
		logic.readFile("", "ID,content,author,likes,shares,date-time");
		assertEquals("Error! File not found or file is corrupt. No posts were loaded.\n"
				+ "", errorMessage.toString());
	}

	@Test
	public void testReadFile_FileFormatError() {
		logic.readFile("posts.csv", "");
		assertEquals("Error! The file was formatted incorrectly. No posts were loaded.\n"
				+ "", errorMessage.toString());
	}

	@Test
	public void testReadFile_FileFound() {
		logic.readFile("posts.csv", "ID,content,author,likes,shares,date-time");
		assertEquals("", errorMessage.toString());
	}
	
	@Test (expected = NullPointerException.class)
	public void testRetrievePost_NullPointer() {
		logic.retrievePost(-1);
	}
	
	@Test
	public void testRetrievePost_Succeed() {
		logic.getPosts().put(1, new Post(1, null, null, 0, 0, null));
		assertEquals("ID: 1 Content: \"null\" Author: null Likes: 0 Shares: 0 Date/Time posted: null", logic.retrievePost(1).toString());
	}
	
	@Test (expected = NullPointerException.class)
	public void testDeletePost_NullPointer() {
		logic.removePost(-1);
	}
	
	@Test
	public void testDeletePost_Succeed() {
		logic.getPosts().put(1, new Post(1, null, null, 0, 0, null));
		logic.removePost(1);
		assertEquals(logic.getPosts().get(1), null);
	}
	
	@Test
	public void testRetrieveNPosts_SortedListLikes() {
		logic.getPosts().put(1, new Post(1, "", "", 1, 2, "1/01/2023 12:12"));
		logic.getPosts().put(2, new Post(2, "", "", 2, 1, "1/01/2023 12:12"));
		logic.retrieveNPosts(2, "likes");
		assertEquals(
				"\n" + "ID: 2 Content: \"\" Author:  Likes: 2 Shares: 1 Date/Time posted: 1/01/2023 12:12\n"
						+ "ID: 1 Content: \"\" Author:  Likes: 1 Shares: 2 Date/Time posted: 1/01/2023 12:12",
				outputMessage.toString());
	}

	@Test
	public void testRetrieveNPosts_SortedListShares() {
		logic.getPosts().put(1, new Post(1, "", "", 2, 1, "1/01/2023 12:12"));
		logic.getPosts().put(2, new Post(2, "", "", 1, 2, "1/01/2023 12:12"));
		logic.retrieveNPosts(2, "shares");
		assertEquals(
				"\n" + "ID: 2 Content: \"\" Author:  Likes: 1 Shares: 2 Date/Time posted: 1/01/2023 12:12\n"
						+ "ID: 1 Content: \"\" Author:  Likes: 2 Shares: 1 Date/Time posted: 1/01/2023 12:12",
				outputMessage.toString());
	}

	@Test
	public void testRetrieveNPosts_SortedListLikesExceedMaximum() {
		logic.getPosts().put(1, new Post(1, "", "", 1, 2, "1/01/2023 12:12"));
		logic.getPosts().put(2, new Post(2, "", "", 2, 1, "1/01/2023 12:12"));
		logic.retrieveNPosts(3, "likes");
		assertEquals(
				"\n" + "ID: 2 Content: \"\" Author:  Likes: 2 Shares: 1 Date/Time posted: 1/01/2023 12:12\n"
						+ "ID: 1 Content: \"\" Author:  Likes: 1 Shares: 2 Date/Time posted: 1/01/2023 12:12",
				outputMessage.toString());
	}

	@Test
	public void testDateTimeValidator_NoDate() {
		boolean invalidDate = logic.dateTimeValidator("");
		assertEquals(false, invalidDate);
	}

	@Test
	public void testDateTimeValidator_ImproperlyFormattedDate() {
		boolean invalidFormatDate = logic.dateTimeValidator("12/13/2023 12:12");
		assertEquals(false, invalidFormatDate);
	}

	@Test
	public void testDateTimeValidator_Succeed() {
		boolean validDate = logic.dateTimeValidator("1/01/2023 12:12");
		assertEquals(true, validDate);
	}
}
