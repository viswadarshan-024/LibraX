package com.library.models.abstractt;

import java.time.LocalDate;

public abstract class LibraryItem {
    protected String id;
    protected String title;
    protected boolean available;
    protected LocalDate addedDate;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.available = true;
        this.addedDate = LocalDate.now();
    }

    public abstract void displayDetails();

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
    public LocalDate getAddedDate() { return addedDate; }
}