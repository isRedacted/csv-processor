package assignment1;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class LogicTest {
	private Logic logic;
	private ArrayList<Post> manualSort;
	private Post post1;
	private Post post2;
	private Post post3;

	@Before
	public void setUp() {
		logic = new Logic();
	}

	@After
	public void tearDown() {
	}

	@Test 
	public void testAddPost_Succeed() {
		post1 = new Post(1, "", "", 0, 0, "1/01/2023 12:12");
		logic.addPost(1, "", "", 0, 0, "1/01/2023 12:12");
		assertEquals(logic.getPosts().get(1).toString(), "ID: 1 Content: \"\" Author:  Likes: 0 Shares: 0 Date/Time posted: 1/01/2023 12:12");
	}
	
	@Test(expected = IOException.class)
	public void testReadFile_FileNotFound() throws FileFormatException, IOException {
		logic.readFile("", "ID,content,author,likes,shares,date-time");
	}

	@Test(expected = FileFormatException.class)
	public void testReadFile_FileFormatError() throws FileFormatException, IOException {
		logic.readFile("posts.csv", "");
	}

	@Test
	public void testReadFile_Succeed() throws FileFormatException, IOException {
		logic.readFile("posts.csv", "ID,content,author,likes,shares,date-time");
	}

	@Test(expected = NullPointerException.class)
	public void testRetrievePost_NullPointer() {
		logic.retrievePost(-1);
	}

	@Test
	public void testRetrievePost_Succeed() {
		logic.getPosts().put(1, new Post(1, null, null, 0, 0, null));
		assertEquals(logic.retrievePost(1), logic.getPosts().get(1));
	}

	@Test(expected = NullPointerException.class)
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
		post1 = new Post(1, "", "", 1, 3, "1/01/2023 12:12");
		post2 = new Post(2, "", "", 2, 2, "1/01/2023 12:12");
		post3 = new Post(3, "", "", 3, 1, "1/01/2023 12:12");
		logic.getPosts().put(1, post1);
		logic.getPosts().put(2, post2);
		logic.getPosts().put(3, post3);
		manualSort = new ArrayList<Post>();
		manualSort.add(logic.getPosts().get(3));
		manualSort.add(logic.getPosts().get(2));
		assertEquals(logic.retrieveNPosts(2, "likes"), manualSort);
	}

	@Test
	public void testRetrieveNPosts_SortedListShares() {
		post1 = new Post(1, "", "", 1, 3, "1/01/2023 12:12");
		post2 = new Post(2, "", "", 2, 2, "1/01/2023 12:12");
		post3 = new Post(3, "", "", 3, 1, "1/01/2023 12:12");
		logic.getPosts().put(1, post1);
		logic.getPosts().put(2, post2);
		logic.getPosts().put(3, post3);
		manualSort = new ArrayList<Post>();
		manualSort.add(logic.getPosts().get(1));
		manualSort.add(logic.getPosts().get(2));
		assertEquals(logic.retrieveNPosts(2, "shares"), manualSort);
	}

	@Test
	public void testRetrieveNPosts_SortedListExceedMaximum() {
		post1 = new Post(1, "", "", 1, 3, "1/01/2023 12:12");
		post2 = new Post(2, "", "", 2, 2, "1/01/2023 12:12");
		post3 = new Post(3, "", "", 3, 1, "1/01/2023 12:12");
		logic.getPosts().put(1, post1);
		logic.getPosts().put(2, post2);
		logic.getPosts().put(3, post3);
		manualSort = new ArrayList<Post>();
		manualSort.add(logic.getPosts().get(3));
		manualSort.add(logic.getPosts().get(2));
		manualSort.add(logic.getPosts().get(1));
		assertEquals(logic.retrieveNPosts(4, "likes"), manualSort);
	}

	@Test(expected = NumberFormatException.class)
	public void testRetrieveNPosts_InvalidNumber() {
		post1 = new Post(1, "", "", 1, 3, "1/01/2023 12:12");
		post2 = new Post(2, "", "", 2, 2, "1/01/2023 12:12");
		post3 = new Post(3, "", "", 3, 1, "1/01/2023 12:12");
		logic.getPosts().put(1, post1);
		logic.getPosts().put(2, post2);
		logic.getPosts().put(3, post3);
		manualSort = new ArrayList<Post>();
		manualSort.add(logic.getPosts().get(2));
		manualSort.add(logic.getPosts().get(1));
		logic.retrieveNPosts(-1, "likes");
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
