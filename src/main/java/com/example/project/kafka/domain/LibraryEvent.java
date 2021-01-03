package com.example.project.kafka.domain;



public class LibraryEvent {

	private Integer libraryEventId;
	private Book book;
	
	public LibraryEvent(Integer libraryEventId, Book book) {
		this.libraryEventId = libraryEventId;
		this.book = book;
	}
	
	public LibraryEvent() {}

	public Integer getLibraryEventId() {
		return libraryEventId;
	}

	public void setLibraryEventId(Integer libraryEventId) {
		this.libraryEventId = libraryEventId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}	
	
}
