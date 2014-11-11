package com.isima.jee.metier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.isima.jee.models.Author;
import com.isima.jee.models.Book;
import com.isima.jee.persistance.PersistanceFactory;
import com.google.appengine.api.datastore.Key;

public class BookActions implements BookActionsInterface {
	private static PersistenceManager pm;
	private AuthorActions auteurAction; 
	
	static {
		pm = PersistanceFactory.getPfm().getPersistenceManager();			
	}
	public BookActions() {
		auteurAction = new AuthorActions();
		Query q = pm.newQuery(Book.class);
		q.setOrdering("num desc");
		q.setRange(0, 1);
		List<Book> list = (List<Book>) q.execute();
		if(list.isEmpty())
			Book.setLastNum(0);
		else
			Book.setLastNum(list.get(0).getNum());
	}
	public int addBook(int authorId, String title, double price, String resume) {
		Author author = auteurAction.getAuthor(authorId);
		Book book = new Book((Book.getLastNum()+1), title, price, resume, authorId);
		if(author == null)
			return 0;
		if(author.getBooks() == null)
			author.setBooks(new ArrayList<Book>());
		author.getBooks().add(book);
		return book.getNum();
	}

	public boolean editBook(int bookId, String title,
			double price, String resume) {
		Book book = getBook(bookId);
		if(book != null){
			book.setPrice(price);
			book.setResume(resume);
			book.setTitle(title);
			return true;
		}else{
			return false;	
		}
	}
	public boolean deleteBook(int bookId) {
		Book b = getBook(bookId);
//		Author a = auteurAction.getAuthor(b.getAuthorId());
//		List<Book> list = new ArrayList<Book>();
//		for(Book bb : a.getBooks()){
//			if(bb.getNum() != bookId)
//				list.add(bb);
//		}
//		a.setBooks(list);
		System.out.println(b);
		pm.deletePersistent(b);
		return true;
	}

	public Book getBook(int bookId) {
		Query query = pm.newQuery(Book.class, "num == bookId");
		query.declareParameters("int bookId");
		List<Book> books = (List<Book>)query.execute(bookId);
		if(! books.isEmpty())
			return books.get(0);
		return null;
	}

	public List<Book> findBook(String filterBy, String value) {
		javax.jdo.Query query = pm.newQuery(Book.class);
		switch (filterBy) {
		case "title":
			query.setFilter("title ==  value");
			break;
		case "price":
			query.setFilter("price == value");
			break;
		case "resume":
			query.setFilter("resume == value");
			break;
		default:
			break;
		}
		query.declareParameters("String value");
		return (List<Book>)query.execute(value);
	}

	public List<Book> allBooks() {
		Extent e = pm.getExtent(Book.class, true);
		Iterator it = e.iterator();
		List<Book> books = new ArrayList<Book>();
		while (it.hasNext()) {
			Book book = (Book)it.next();
			books.add(book);
		}
		return books;
	}
	public Author getAuthorOfBook(int bookId) {
		Book book = getBook(bookId);
		return auteurAction.getAuthor(book.getAuthorId());
	}

}
