package com.isima.jee.ws;

import java.util.List;

import com.isima.jee.metier.AuthorActions;
import com.isima.jee.metier.AuthorActionsInterface;
import com.isima.jee.metier.BookActions;
import com.isima.jee.metier.BookActionsInterface;
import com.isima.jee.models.Author;
import com.isima.jee.models.Book;

public class BiblioService implements AuthorActionsInterface, BookActionsInterface {
	private AuthorActionsInterface authorActions;
	private BookActionsInterface bookActions;
	public BiblioService() {
		authorActions = new AuthorActions();
		bookActions = new BookActions();
	}
	public int addBook(int authorId, String title, double price, String resume) {
		
		return bookActions.addBook(authorId, title, price, resume);
	}

	public boolean editBook(int bookId, String title,
			double price, String resume) {
		return bookActions.editBook(bookId, title, price, resume);
	}

	public boolean deleteBook(int bookId) {
		return bookActions.deleteBook(bookId);
	}

	public Book getBook(int bookId) {
		return bookActions.getBook(bookId);
	}

	public List<Book> findBook(String filterBy, String value) {
		return bookActions.findBook(filterBy, value);
	}

	public List<Book> allBooks() {
		return bookActions.allBooks();
	}

	public Author getAuthorOfBook(int bookId) {
		return bookActions.getAuthorOfBook(bookId);
	}

	public int addAuthor(String firstName, String lastName, String address) {
		
		return authorActions.addAuthor(firstName, lastName, address);
	}

	public boolean editAuthor(int authorId, String firstName, String lastName,
			String address) {
		return authorActions.editAuthor(authorId, firstName, lastName, address);
	}

	public boolean deleteAuthor(int authorId) {
		return authorActions.deleteAuthor(authorId);
	}

	public Author getAuthor(int authorId) {
		return authorActions.getAuthor(authorId);
	}

	public List<Author> findAuthor(String filterBy, String value) {
		return authorActions.findAuthor(filterBy, value);
	}

	public List<Author> allAuthors() {
		return authorActions.allAuthors();
	}

	public List<Book> getBooksByAuthor(int authorId) {
		return authorActions.getBooksByAuthor(authorId);
	}

}
