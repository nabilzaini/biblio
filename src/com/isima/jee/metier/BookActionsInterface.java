package com.isima.jee.metier;

import java.util.List;

import com.isima.jee.models.Author;
import com.isima.jee.models.Book;

public interface BookActionsInterface {
	int addBook(int authorId, String title, double price, String resume);
    boolean editBook(int bookId, int authorId, String title, double price, String resume);
    boolean deleteBook(int bookId);
    Book getBook(int bookId);
    List<Book> findBook(String filterBy, String value);
    List<Book> allBooks();
    Author getAuthorOfBook(int bookId);
}
