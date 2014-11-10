package com.isima.jee.models;

import java.util.List;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable
public class Author {
	@Persistent
	private int num;
	@Persistent
	private String firstName;
	@Persistent
	private String lastName;
	@Persistent
	private String adress;
	@Persistent
	private List<Book> books;
	public Author() {
		super();
	}
	public Author(int num, String firstName, String lastName, String adress,
			List<Book> books) {
		super();
		this.num = num;
		this.firstName = firstName;
		this.lastName = lastName;
		this.adress = adress;
		this.books = books;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public List<Book> getBooks() {
		return books;
	}
	public void setBooks(List<Book> books) {
		this.books = books;
	}
	@Override
	public String toString() {
		return "Author [num=" + num + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", adress=" + adress + ", books="
				+ books + "]";
	}
}
