package com.isima.jee.metier;

import java.util.List;

import com.isima.jee.models.Author;
import com.isima.jee.models.Book;

public class AuthorActions implements AuthorActionsInterface {

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
