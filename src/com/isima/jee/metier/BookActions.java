package com.isima.jee.metier;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.jdo.Extent;
import javax.jdo.PersistenceManager;
import com.isima.jee.models.Author;
import com.isima.jee.models.Book;
import com.isima.jee.persistance.PersistanceFactory;


public class BookActions implements BookActionsInterface {
	private static PersistenceManager pm;
	private AuthorActions auteurAction; 
	
	static {
		pm = PersistanceFactory.getPfm().getPersistenceManager();			
	}
	public BookActions() {
		auteurAction = new AuthorActions();
	}
	public int addBook(int authorId, String title, double price, String resume) {
		Author author = auteurAction.getAuthor(authorId);
		Book book = new Book((Book.getLastNum()+1), title, price, resume, author);
		return book.getNum();
	}

	public boolean editBook(int bookId, int authorId, String title,
			double price, String resume) {
		Book book = getBook(bookId);
		if(book != null){
			Author author = auteurAction.getAuthor(authorId);
			book.setAuthor(author);
			book.setNum(Book.getLastNum()+1);
			book.setPrice(price);
			book.setResume(resume);
			book.setTitle(title);
			return true;
		}else{
			return false;	
		}
	}
	public boolean deleteBook(int bookId) {
		Book book =  getBook(bookId);
		if(book != null){
			try {
				pm.deletePersistent(book);
				return true;	
			} catch (Exception e) {
				return false;
			}
		}else{
			return false;
		}
	}

	public Book getBook(int bookId) {
		javax.jdo.Query query = pm.newQuery(Book.class, "num == " + bookId  );
		query.declareParameters("int bookId");
		return (Book) query.execute();
	}

	public List<Book> findBook(String filterBy, String value) {
		javax.jdo.Query query = pm.newQuery(Book.class);
		switch (filterBy) {
		case "title":
			query.setFilter("title == " + value);
			break;
		case "price":
			query.setFilter("price == " + value);
			break;
		case "resume":
			query.setFilter("resume == " + value);
			break;
		default:
			break;
		}
		query.declareParameters("String value");
		return (List<Book>)query.execute();
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
		return book.getAuthor();
	}

}
