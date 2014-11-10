package com.isima.jee.ws;

import java.util.List;

import com.isima.jee.metier.AuthorActionsInterface;
import com.isima.jee.metier.BookActionsInterface;
import com.isima.jee.models.Author;
import com.isima.jee.models.Book;

public class BiblioService implements AuthorActionsInterface, BookActionsInterface {

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

	@Override
	public int addAuthor(String firstName, String lastName, String address) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean editAuthor(int authorId, String firstName, String lastName,
			String address) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAuthor(int authorId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Author getAuthor(int authorId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> findAuthor(String filterBy, String value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Author> allAuthors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByAuthor(int authorId) {
		// TODO Auto-generated method stub
		return null;
	}

}
