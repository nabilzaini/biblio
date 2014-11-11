package com.isima.jee.models;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;
@PersistenceCapable(detachable = "true")
public class Book {
	@PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    private Key id;
	@Persistent
	private  int num;
	@Persistent
	private static int lastNum;
	@Persistent
	private String title;
	@Persistent
	private double price;
	@Persistent
	private String resume;
	@Persistent
	private int authorId;
	@Persistent
	private Author author;
	public Book() {
		super();
	}
	public Book(int num, String title, double price, String resume,
			int authorId) {
		super();
		this.num = num;
		this.title = title;
		this.price = price;
		this.resume = resume;
		this.authorId = authorId;
		if(num > lastNum)
			lastNum = num;
	}
	public static int getLastNum() {
		return lastNum;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
	
	@Override
	public String toString() {
		return "Book [num=" + num + ", title=" + title + ", price=" + price
				+ ", resume=" + resume + ", authorId = " + authorId + "]";
	}
	public static void setLastNum(int n) {
		lastNum = n;
	}
}
