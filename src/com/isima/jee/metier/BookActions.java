package com.isima.jee.metier;

import java.util.List;

import com.isima.jee.models.Author;
import com.isima.jee.models.Book;

public class BookActions implements BookActionsInterface {

	@Override
	public int addBook(int authorId, String title, double price, String resume) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean editBook(int bookId, int authorId, String title,
			double price, String resume) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(int bookId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Book getBook(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> findBook(String filterBy, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> allBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Author getAuthorOfBook(int bookId) {
		// TODO Auto-generated method stub
		return null;
	}

}
