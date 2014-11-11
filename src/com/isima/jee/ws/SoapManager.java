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


public class SoapManager {
//	private static final String NAMESPACE_URI = "http://service.web.com/";
//	private static final QName ADD_AUTHOR_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName EDIT_AUTHOR_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName DELETE_AUTHOR_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName GET_AUTHOR_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName FIND_AUTHOR_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName ALL_AUTHORS_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName GET_BOOKS_BY_AUTHOR_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName ADD_BOOK_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName EDIT_BOOK_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName authorIdDELETE_BOOK_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName GET_AUTHOR_OF_BOOK_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName GET_BOOK_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName FIND_BOOK_QName = new QName(NAMESPACE_URI,"add");
//	private static final QName ALL_BOOKS_QName = new QName(NAMESPACE_URI,"add");
//	
//	private MessageFactory messageFactory;
//	private BiblioService webService;
//	public SoapManager() throws SOAPException {
//		messageFactory = MessageFactory.newInstance();
//		webService =  new BiblioService();
//	}
//	@SuppressWarnings("rawtypes")
//	public SOAPMessage handleSOAPRequest(SOAPMessage request) throws SOAPException{
//		SOAPBody soapBody = request.getSOAPBody();
//		Iterator iterator = soapBody.getChildElements();
//		Object response = null;
//		while(iterator.hasNext()){
//			Object next = iterator.next();
//			if(next instanceof SOAPElement){
//				SOAPElement soapElement = (SOAPElement)next;
//				QName qname = soapElement.getElementQName();
//			
//				if(ADD_AUTHOR_QName.equals(qname)){
//					response = appelerAddAuthor(soapElement);
//					break;
//				}else if(EDIT_AUTHOR_QName.equals(qname)){
//					response = appelerEditAuthor(soapElement);
//					break;
//				}else if(DELETE_AUTHOR_QName.equals(qname)){
//					response = appelerDeleteAuthor(soapElement);
//					break;
//				}else if(GET_AUTHOR_QName.equals(qname)){
//					response = appelerGetAuthor(soapElement);
//					break;
//				}else if(FIND_AUTHOR_QName.equals(qname)){
//					response = appelerFindAuthor(soapElement);
//					break;
//				}else if(ALL_AUTHORS_QName.equals(qname)){
//					response = appelerAllAuthors(soapElement);
//					break;
//				}else if(GET_BOOKS_BY_AUTHOR_QName.equals(qname)){
//					response = appelerGetBooksByAuthor(soapElement);
//					break;
//				}else if(ADD_BOOK_QName.equals(qname)){
//					response = appelerAddBook(soapElement);
//					break;
//				}else if(EDIT_BOOK_QName.equals(qname)){
//					response = appelerEditBook(soapElement);
//					break;
//				}else if(DELETE_BOOK_QName.equals(qname)){
//					response = appelerDeleteBook(soapElement);
//					break;
//				}else if(GET_AUTHOR_OF_BOOK_QName.equals(qname)){
//					response = appelerGetAuthorOfBook(soapElement);
//					break;
//				}else if(GET_BOOK_QName.equals(qname)){
//					response = appelerGetBook(soapElement);
//					break;
//				}else if(FIND_BOOK_QName.equals(qname)){
//					response = appelerFindBook(soapElement);
//					break;
//				}else if(ALL_BOOKS_QName.equals(qname)){
//					response = appelerAllBooks(soapElement);
//					break;
//				}
//			}
//		}
//		SOAPMessage soapResponse = messageFactory.createMessage();
//		soapBody = soapResponse.getSOAPBody();
//		if(response != null){
//			JAXB.marshal(response, new SAAJResult(soapBody));
//		}else{
//			SOAPFault fault = soapBody.addFault();
//			fault.setFaultString("Unreconized SOAP request");
//		}
//		return soapResponse;
//		
//	}
//	private Object appelerAddAuthor(SOAPElement soapElement) {
//		AddAuthor addAuthor = JAXB.unmarshal(new DOMSource(soapElement), AddAuthor.class);
//		String firstName = addAuthor.getArg1();
//		String lastName = addAuthor.getArg2();
//		String address = addAuthor.getArg3();
//		int resultat = webService.addAuthor(firstName, lastName, address);
//		AddAuthorResponse addAuthorResponse = new AddAuthorResponse();
//		addAuthorResponse.setReturn(resultat);
//		return addAuthorResponse;
//	}
//	private Object appelerEditAuthor(SOAPElement soapElement) {
//		EditAuthor editAuthor = JAXB.unmarshal(new DOMSource(soapElement), EditAuthor.class);
//		return adapter.addapterAdd(editAuthor);
//	}
//	private Object appelerDeleteAuthor(SOAPElement soapElement) {
//		DeleteAuthor deleteAuthor = JAXB.unmarshal(new DOMSource(soapElement), DeleteAuthor.class);
//		return adapter.addapterAdd(deleteAuthor);
//	}
//	private Object appelerGetAuthor(SOAPElement soapElement) {
//		GetAuthor getAuthor = JAXB.unmarshal(new DOMSource(soapElement), GetAuthor.class);
//		return adapter.addapterAdd(getAuthor);
//	}
//	private Object appelerFindAuthor(SOAPElement soapElement) {
//		FindAuthor findAuthor = JAXB.unmarshal(new DOMSource(soapElement), FindAuthor.class);
//		return adapter.addapterAdd(findAuthor);
//	}
//	private Object appelerAllAuthors(SOAPElement soapElement) {
//		AllAuthors allAuthors = JAXB.unmarshal(new DOMSource(soapElement), AllAuthors.class);
//		return adapter.addapterAdd(allAuthors);
//	}
//	private Object appelerGetBooksByAuthor(SOAPElement soapElement) {
//		GetBooksByAuthor getBooksByAuthor = JAXB.unmarshal(new DOMSource(soapElement), GetBooksByAuthor.class);
//		return adapter.addapterAdd(getBooksByAuthor);
//	}
//	private Object appelerAddBook(SOAPElement soapElement) {
//		AddBook addBook = JAXB.unmarshal(new DOMSource(soapElement), AddBook.class);
//		return adapter.addapterAdd(addBook);
//	}
//	private Object appelerEditBook(SOAPElement soapElement) {
//		EditBook editBook = JAXB.unmarshal(new DOMSource(soapElement), EditBook.class);
//		return adapter.addapterAdd(editBook);
//	}
//	private Object appelerDeleteBook(SOAPElement soapElement) {
//		DeleteBook deleteBook = JAXB.unmarshal(new DOMSource(soapElement), deleteBook.class);
//		return adapter.addapterAdd(deleteBook);
//	}
//	private Object appelerGetAuthorOfBook(SOAPElement soapElement) {
//		GetAuthorOfBook getAuthorOfBook = JAXB.unmarshal(new DOMSource(soapElement), GetAuthorOfBook.class);
//		return adapter.addapterAdd(getAuthorOfBook);
//	}
//	private Object appelerGetBook(SOAPElement soapElement) {
//		GetBook getBook = JAXB.unmarshal(new DOMSource(soapElement), getBook.class);
//		return adapter.addapterAdd(getBook);
//	}
//	private Object appelerFindBook(SOAPElement soapElement) {
//		FindBook findBook = JAXB.unmarshal(new DOMSource(soapElement), FindBook.class);
//		return adapter.addapterAdd(findBook);
//	}
//	private Object appelerAllBooks(SOAPElement soapElement) {
//		AllBooks allBooks = JAXB.unmarshal(new DOMSource(soapElement), AllBooks.class);
//		return adapter.addapterAdd(allBooks);
//	}
	
}
