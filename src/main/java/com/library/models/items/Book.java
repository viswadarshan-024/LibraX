package com.library.models.items;

import com.library.models.abstractt.LibraryItem;

public class Book extends LibraryItem {
    private String author;
    private String genre;

    public Book(String id, String title, String author, String genre) {
        super(id, title);
        this.author = author;
        this.genre = genre;
    }

    @Override
    public void displayDetails() {
        System.out.println("Book [ID: " + id + ", Title: " + title + ", Author: " + author +
                ", Genre: " + genre + ", Available: " + available + "]");
    }

    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
}