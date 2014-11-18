package com.isima.jee.ws;

import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXB;
import javax.xml.namespace.QName;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SAAJResult;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPMessage;
import javax.xml.transform.dom.DOMSource;

import com.isima.jee.models.Author;
import com.isima.jee.models.Book;
import com.isima.jee.ws.jaxws.AddAuthor;
import com.isima.jee.ws.jaxws.AddAuthorResponse;
import com.isima.jee.ws.jaxws.AddBook;
import com.isima.jee.ws.jaxws.AddBookResponse;
import com.isima.jee.ws.jaxws.AllAuthors;
import com.isima.jee.ws.jaxws.AllAuthorsResponse;
import com.isima.jee.ws.jaxws.AllBooks;
import com.isima.jee.ws.jaxws.AllBooksResponse;
import com.isima.jee.ws.jaxws.DeleteAuthor;
import com.isima.jee.ws.jaxws.DeleteAuthorResponse;
import com.isima.jee.ws.jaxws.DeleteBook;
import com.isima.jee.ws.jaxws.DeleteBookResponse;
import com.isima.jee.ws.jaxws.EditAuthor;
import com.isima.jee.ws.jaxws.EditAuthorResponse;
import com.isima.jee.ws.jaxws.EditBook;
import com.isima.jee.ws.jaxws.EditBookResponse;
import com.isima.jee.ws.jaxws.FindAuthor;
import com.isima.jee.ws.jaxws.FindAuthorResponse;
import com.isima.jee.ws.jaxws.FindBook;
import com.isima.jee.ws.jaxws.FindBookResponse;
import com.isima.jee.ws.jaxws.GetAuthor;
import com.isima.jee.ws.jaxws.GetAuthorOfBook;
import com.isima.jee.ws.jaxws.GetAuthorOfBookResponse;
import com.isima.jee.ws.jaxws.GetAuthorResponse;
import com.isima.jee.ws.jaxws.GetBook;
import com.isima.jee.ws.jaxws.GetBookResponse;
import com.isima.jee.ws.jaxws.GetBooksByAuthor;
import com.isima.jee.ws.jaxws.GetBooksByAuthorResponse;


public class SoapManager {
	private static final String NAMESPACE_URI = "http://ws.jee.isima.com/";
	private static final QName ADD_AUTHOR_QName = new QName(NAMESPACE_URI,"addAuthor");
	private static final QName EDIT_AUTHOR_QName = new QName(NAMESPACE_URI,"editAuthor");
	private static final QName DELETE_AUTHOR_QName = new QName(NAMESPACE_URI,"deleteAuthor");
	private static final QName GET_AUTHOR_QName = new QName(NAMESPACE_URI,"getAuthor");
	private static final QName FIND_AUTHOR_QName = new QName(NAMESPACE_URI,"findAuthor");
	private static final QName ALL_AUTHORS_QName = new QName(NAMESPACE_URI,"allAuthors");
	private static final QName GET_BOOKS_BY_AUTHOR_QName = new QName(NAMESPACE_URI,"getBooksByAuthor");
	private static final QName ADD_BOOK_QName = new QName(NAMESPACE_URI,"addBook");
	private static final QName EDIT_BOOK_QName = new QName(NAMESPACE_URI,"editBook");
	private static final QName DELETE_BOOK_QName = new QName(NAMESPACE_URI,"deleteBook");
	private static final QName GET_AUTHOR_OF_BOOK_QName = new QName(NAMESPACE_URI,"getAuthorOfBook");
	private static final QName GET_BOOK_QName = new QName(NAMESPACE_URI,"getBook");
	private static final QName FIND_BOOK_QName = new QName(NAMESPACE_URI,"findBook");
	private static final QName ALL_BOOKS_QName = new QName(NAMESPACE_URI,"allBooks");
	
	private MessageFactory messageFactory;
	private BiblioService webService;
	public SoapManager() throws SOAPException {
		messageFactory = MessageFactory.newInstance();
		webService =  new BiblioService();
	}
	@SuppressWarnings("rawtypes")
	public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException{
		SOAPBody soapBody = request.getSOAPBody();
		Iterator iterator = soapBody.getChildElements();
		Object response = null;
		while(iterator.hasNext()){
			Object next = iterator.next();
			if(next instanceof SOAPElement){
				SOAPElement soapElement = (SOAPElement)next;
				QName qname = soapElement.getElementQName();
			
				if(ADD_AUTHOR_QName.equals(qname)){
					response = appelerAddAuthor(soapElement);
					break;
				}else if(EDIT_AUTHOR_QName.equals(qname)){
					response = appelerEditAuthor(soapElement);
					break;
				}else if(DELETE_AUTHOR_QName.equals(qname)){
					response = appelerDeleteAuthor(soapElement);
					break;
				}else if(GET_AUTHOR_QName.equals(qname)){
					response = appelerGetAuthor(soapElement);
					break;
				}else if(FIND_AUTHOR_QName.equals(qname)){
					response = appelerFindAuthor(soapElement);
					break;
				}else if(ALL_AUTHORS_QName.equals(qname)){
					response = appelerAllAuthors(soapElement);
					break;
				}else if(GET_BOOKS_BY_AUTHOR_QName.equals(qname)){
					response = appelerGetBooksByAuthor(soapElement);
					break;
				}else if(ADD_BOOK_QName.equals(qname)){
					response = appelerAddBook(soapElement);
					break;
				}else if(EDIT_BOOK_QName.equals(qname)){
					response = appelerEditBook(soapElement);
					break;
				}else if(DELETE_BOOK_QName.equals(qname)){
					response = appelerDeleteBook(soapElement);
					break;
				}else if(GET_AUTHOR_OF_BOOK_QName.equals(qname)){
					response = appelerGetAuthorOfBook(soapElement);
					break;
				}else if(GET_BOOK_QName.equals(qname)){
					response = appelerGetBook(soapElement);
					break;
				}else if(FIND_BOOK_QName.equals(qname)){
					response = appelerFindBook(soapElement);
					break;
				}else if(ALL_BOOKS_QName.equals(qname)){
					response = appelerAllBooks(soapElement);
					break;
				}
			}
		}
		SOAPMessage soapResponse = messageFactory.createMessage();
		soapBody = soapResponse.getSOAPBody();
		if(response != null){
			JAXB.marshal(response, new SAAJResult(soapBody));
		}else{
			SOAPFault fault = soapBody.addFault();
			fault.setFaultString("Unreconized SOAP request");
		}
		return soapResponse;
		
	}
	private Object appelerAddAuthor(SOAPElement soapElement) {
		AddAuthor addAuthor = JAXB.unmarshal(new DOMSource(soapElement), AddAuthor.class);
		int resultat = webService.addAuthor(addAuthor.getArg0(), addAuthor.getArg1(), addAuthor.getArg2());
		AddAuthorResponse addAuthorResponse = new AddAuthorResponse();
		addAuthorResponse.setReturn(resultat);
		return addAuthorResponse;
	}
	private Object appelerEditAuthor(SOAPElement soapElement) {
		EditAuthor editAuthor = JAXB.unmarshal(new DOMSource(soapElement), EditAuthor.class);
		boolean resultat = webService.editAuthor(editAuthor.getArg0(), editAuthor.getArg1(), editAuthor.getArg2(), editAuthor.getArg3());
		EditAuthorResponse editAuthorResponse = new EditAuthorResponse();
		editAuthorResponse.setReturn(resultat);
		return editAuthorResponse;
	}
	private Object appelerDeleteAuthor(SOAPElement soapElement) {
		DeleteAuthor deleteAuthor = JAXB.unmarshal(new DOMSource(soapElement), DeleteAuthor.class);
		boolean resultat  = webService.deleteAuthor(deleteAuthor.getArg0());
		DeleteAuthorResponse deleteAuthorResponse = new DeleteAuthorResponse();
		deleteAuthorResponse.setReturn(resultat);
		return deleteAuthorResponse;
	}
	private Object appelerGetAuthor(SOAPElement soapElement) {
		GetAuthor getAuthor = JAXB.unmarshal(new DOMSource(soapElement), GetAuthor.class);
		Author resultat =  webService.getAuthor(getAuthor.getArg0());
		GetAuthorResponse getAuthorResponse =  new GetAuthorResponse();
		getAuthorResponse.setReturn(resultat);
		return getAuthorResponse;
	}
	private Object appelerFindAuthor(SOAPElement soapElement) {
		FindAuthor findAuthor = JAXB.unmarshal(new DOMSource(soapElement), FindAuthor.class);
		List<Author> resultat = webService.findAuthor(findAuthor.getArg0(), findAuthor.getArg1());
		FindAuthorResponse finAuthorResponse = new FindAuthorResponse();
		finAuthorResponse.setReturn(resultat);
		return finAuthorResponse;
	}
	private Object appelerAllAuthors(SOAPElement soapElement) {
		List<Author> resultat = webService.allAuthors();
		AllAuthorsResponse allAuthorsResponse = new AllAuthorsResponse();
		allAuthorsResponse.setReturn(resultat);
		return allAuthorsResponse;
	}
	private Object appelerGetBooksByAuthor(SOAPElement soapElement) {
		GetBooksByAuthor getBooksByAuthor = JAXB.unmarshal(new DOMSource(soapElement), GetBooksByAuthor.class);
		List<Book> resultat = webService.getBooksByAuthor(getBooksByAuthor.getArg0());
		GetBooksByAuthorResponse getBooksAuthorResponse = new GetBooksByAuthorResponse();
		getBooksAuthorResponse.setReturn(resultat);
		return getBooksAuthorResponse;

	}
	private Object appelerAddBook(SOAPElement soapElement) {
		AddBook addBook = JAXB.unmarshal(new DOMSource(soapElement), AddBook.class);
		int resultat = webService.addBook(addBook.getArg0(), addBook.getArg1(), addBook.getArg2(), addBook.getArg3());
		AddBookResponse addBookResponse =  new AddBookResponse();
		addBookResponse.setReturn(resultat);
		return addBookResponse;
	}
	private Object appelerEditBook(SOAPElement soapElement) {
		EditBook editBook = JAXB.unmarshal(new DOMSource(soapElement), EditBook.class);
		boolean resultat = webService.editBook(editBook.getArg0(), editBook.getArg1(), editBook.getArg2(), editBook.getArg3());
		EditBookResponse editBookResponse = new EditBookResponse();
		editBookResponse.setReturn(resultat);
		return editBookResponse;
	}
	private Object appelerDeleteBook(SOAPElement soapElement) {
		DeleteBook deleteBook = JAXB.unmarshal(new DOMSource(soapElement), DeleteBook.class);
		boolean resultat = webService.deleteBook(deleteBook.getArg0());
		DeleteBookResponse deleteBookResponse = new DeleteBookResponse();
		deleteBookResponse.setReturn(resultat);
		return deleteBookResponse;
		
	}
	private Object appelerGetAuthorOfBook(SOAPElement soapElement) {
		GetAuthorOfBook getAuthorOfBook = JAXB.unmarshal(new DOMSource(soapElement), GetAuthorOfBook.class);
		Author resultat = webService.getAuthorOfBook(getAuthorOfBook.getArg0());
		GetAuthorOfBookResponse getAuthorOfBookResponse = new GetAuthorOfBookResponse();
		getAuthorOfBookResponse.setReturn(resultat);
		return getAuthorOfBookResponse;
	
	}
	private Object appelerGetBook(SOAPElement soapElement) {
		GetBook getBook = JAXB.unmarshal(new DOMSource(soapElement), GetBook.class);
		Book resultat = webService.getBook(getBook.getArg0());
		GetBookResponse getBookResponse = new GetBookResponse();
		getBookResponse.setReturn(resultat);
		return getBookResponse;
	}
	private Object appelerFindBook(SOAPElement soapElement) {
		FindBook findBook = JAXB.unmarshal(new DOMSource(soapElement), FindBook.class);
		List<Book> resultat = webService.findBook(findBook.getArg0(), findBook.getArg1());
		FindBookResponse findBookResponse = new FindBookResponse();
		findBookResponse.setReturn(resultat);
		return findBookResponse;
	}
	private Object appelerAllBooks(SOAPElement soapElement) {
		List<Book> resultat = webService.allBooks();
		AllBooksResponse allBooksResponse = new AllBooksResponse();
		allBooksResponse.setReturn(resultat);
		return allBooksResponse;
	}
	
}
