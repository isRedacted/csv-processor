package assignment1;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LogicTest {
	private Logic logic;
	private ByteArrayOutputStream errorMessage;

	@Before
	public void setUp() {
		logic = new Logic();
		errorMessage = new ByteArrayOutputStream();
		System.setErr(new PrintStream(errorMessage));
	}

	@After
	public void tearDown() {
		System.setErr(System.err);
	}

	/*
	 * readFile() tests
	 */
	@Test
	public void testReadFile_FileNotFound() {
		logic.readFile("", "ID,content,author,likes,shares,date-time");
		assertEquals("Error! File not found or file is corrupt. No posts were loaded.\r\n", errorMessage.toString());
	}

	@Test
	public void testReadFile_FileFormatError() {
		logic.readFile("posts.csv", "");
		assertEquals("Error! The file was formatted incorrectly. No posts were loaded.\r\n", errorMessage.toString());
	}

	@Test
	public void testReadFile_FileFound() {
		logic.readFile("posts.csv", "ID,content,author,likes,shares,date-time");
		assertEquals("", errorMessage.toString());
	}
	
	@Test (expected = NullPointerException.class)
	public void testAddPost() {
		logic.addPost(null, null, null, 0, 0, null);
	}
}
