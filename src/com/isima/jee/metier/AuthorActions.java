package com.isima.jee.metier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.isima.jee.models.Author;
import com.isima.jee.models.Book;
import com.isima.jee.persistance.PersistanceFactory;

public class AuthorActions implements AuthorActionsInterface {
	private PersistenceManager pm = PersistanceFactory.getPfm().getPersistenceManager();
	
	public AuthorActions(){
		Query q = pm.newQuery(Author.class);
		q.setOrdering("num desc");
		q.setRange(0, 1);
		List<Author> list = (List<Author>) q.execute();
		if(list.isEmpty())
			Author.setLastNum(0);
		else
			Author.setLastNum(list.get(0).getNum());
	}
	public void open(){
		
	}
	
	/**
	 * Returns the added author num or 0 when having error
	 */
	public int addAuthor(String firstName, String lastName, String address) {
		int num = Author.getLastNum() + 1;
		Author a = new Author(num, firstName, lastName, address, new ArrayList<Book>());
		Transaction tx = pm.currentTransaction();
		try {
		    tx.begin();
		    pm.makePersistent(a);
		    tx.commit();
		} catch (Exception e) {
			num = 0;
		    // ... handle exceptions
		} finally {
		    if (tx.isActive())
		        tx.rollback();
		}
		return num;
	}
	
	public boolean editAuthor(int authorId, String firstName, String lastName, String address) {
		Author a = getAuthor(authorId);
		if( a == null )
			return false;
		a.setFirstName(firstName);
		a.setLastName(lastName);
		a.setAdress(address);
		return true;
	}

	@Override
	public boolean deleteAuthor(int authorId) {
		Query q = pm.newQuery(Author.class);
		q.setFilter("num == numParam");
		q.declareParameters("int numParam");
		q.deletePersistentAll(authorId);
		return true;
	}

	@Override
	public Author getAuthor(int authorId) {
		Query q = pm.newQuery(Author.class);
		q.setFilter("num == numParam");
		q.declareParameters("int numParam");
		List<Author> results = (List<Author>) q.execute(authorId);
		if (!results.isEmpty()) {
			for (Author a : results) {
				return a;
		    }
		}
		return null;
	}

	@Override
	public List<Author> findAuthor(String filterBy, String value) {
		String[] fields = new String[] {"firstName", "lastName", "adress"};
		if(! Arrays.asList(fields).contains(filterBy))
			return null;
		Query q = pm.newQuery(Author.class);
		q.setFilter(filterBy + " == param");
		q.declareParameters("String param");
		return (List<Author>) q.execute(value);
	}

	@Override
	public List<Author> allAuthors() {
		Query q = pm.newQuery(Author.class);
		return (List<Author>) q.execute();
	}

	@Override
	public List<Book> getBooksByAuthor(int authorId) {
		Query q = pm.newQuery(Book.class);
		q.setFilter("authorId == numParam");
		q.declareParameters("int numParam");
		List<Book> results = (List<Book>) q.execute(authorId);
		return results;
	}

}
