package assignment1;

import java.io.Serializable;

/**
 * Contains all of the social media post data, as well as the associated setters
 * and getters.
 */
public class Post implements Serializable {
	/**
	 * Generated serial ID
	 */
	private static final long serialVersionUID = 2636613401475440782L;
	private int id;
	private String content;
	private String author;
	private int likes;
	private int shares;
	private String dateTime;

	public Post(int id, String content, String author, int likes, int shares, String dateTime) {
		this.id = id;
		this.content = content;
		this.author = author;
		this.likes = likes;
		this.shares = shares;
		this.dateTime = dateTime;
	}

	public int getid() {
		return id;
	}

	public void setid(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getShares() {
		return shares;
	}

	public void setShares(int shares) {
		this.shares = shares;
	}

	public String getDateTime() {
		return dateTime;
	}

	public void setDateTime(String dateTime) {
		this.dateTime = dateTime;
	}

	@Override
	public String toString() {
		return "ID: " + id + " Content: \"" + content + "\" Author: " + author + " Likes: " + likes + " Shares: "
				+ shares + " Date/Time posted: " + dateTime;
	}
}
