package com.isima.jee.ws;

import java.io.PrintWriter;

import com.isima.jee.models.Author;
import com.isima.jee.models.Book;

public class Tester {
	private BiblioService bs;
	private PrintWriter pw;
	public Tester(BiblioService b, PrintWriter p){
		bs = b;
		pw = p;
	}
	public void run(){
		testAuthor();
		testBook();
	}
	private void log(int spaces, String txt, String color){
		for(int i = 0; i < spaces; i++)
			pw.print(' ');
		pw.print("<span style='color: " + color + "'>" + txt + "</span><br>");
	}
	private void info(int spaces, String txt){
		log(spaces, txt, "#000");
	}
	private void success(int spaces, String txt){
		log(spaces, txt, "green");
	}
	private void error(int spaces, String txt){
		log(spaces, txt, "red");
	}
	public void testAuthor(){
		info(0,"Test Author :");
		info(4, "Adding Amine:");
		int n = bs.addAuthor("Amine", "Ben hammou", "Aubiere");
		success(6, "Author Amine added with num = " + n);
		
		info(4, "Retreiving Amine:");
		Author a = bs.getAuthor(n);
		if(a == null)
			error(6, "Not Found !");
		else
			success(6, "Found with name = " + a.getFirstName() + " & num = " + a.getNum());
		
		info(4, "Editting Amine: changing name to Nabil");
		bs.editAuthor(n, "Nabil", "Zaini", "Aubiere");
		a = bs.getAuthor(n);
		info(6, "New infos : name = " + a.getFirstName());
		
		info(4, "Deleting Author:");
		if(bs.deleteAuthor(n))
			success(6, "Author deleted !");
		else
			error(6, "Error while deleting !");
		
		info(4, "Trying to retreive the deleted Author:");
		a = bs.getAuthor(n);
		if(a == null)
			success(6, "Author not found !");
		else
			error(6, "Author found !");
	}
	
	public void testBook(){
		info(0,"Test Book :");
		int an = bs.addAuthor("Amine","Ben hammou", "Aubiere");

		info(4, "Adding Book:");
		int n = bs.addBook(an, "Naruto", 7.5, "Aubiere");
		success(6, "Book Naruto added with num = " + n);
		
		info(4, "Retreiving Naruto:");
		int k = 0;
		Book a = bs.getBook(n);
		if(a == null)
			error(6, "Not Found !");
		else
			success(6, "Found with name = " + a.getTitle() + " & num = " + a.getNum());
		
		info(4, "Editting Naruto: changing name to Luffy");
		bs.editBook(n, "Luffy", 88, "Aubiere");
		a = bs.getBook(n);
		if( a != null )
			info(6, "New infos : name = " + a.getTitle());
		else
			error(6, "Not Found :p");
		
		info(4, "Adding an other Book:");
		int n2 = bs.addBook(an, "Bleach", 7.5, "Aubiere");
		success(6, "Book added with num = " + n2);
		
		info(4, "Deleting Book:");
		if(bs.deleteBook(n))
			success(6, "Book deleted !");
		else
			error(6, "Error while deleting !");
		
		info(4, "Trying to retreive the deleted Book:");
		a = bs.getBook(n);
		if(a == null)
			success(6, "Book not found !");
		else
			error(6, "Book found !");
	}
}
