package com.isima.jee.metier;

import java.util.List;

import com.isima.jee.models.Author;
import com.isima.jee.models.Book;

public interface AuthorActionsInterface {
	int addAuthor(String firstName, String lastName, String address);
    boolean editAuthor(int authorId, String firstName, String lastName, String address);
    boolean deleteAuthor(int authorId);
    Author getAuthor(int authorId);
    List<Author> findAuthor(String filterBy, String value);
    List<Author> allAuthors();
    List<Book> getBooksByAuthor(int authorId);
}
