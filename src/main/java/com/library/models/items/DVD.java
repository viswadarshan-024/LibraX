package com.library.models.items;

import com.library.models.abstractt.LibraryItem;

public class DVD extends LibraryItem {
    private int duration;
    private String language;

    public DVD(String id, String title, int duration, String language) {
        super(id, title);
        this.duration = duration;
        this.language = language;
    }

    @Override
    public void displayDetails() {
        System.out.println("DVD [ID: " + id + ", Title: " + title + ", Duration: " + duration +
                " mins, Language: " + language + ", Available: " + available + "]");
    }

    public int getDuration() { return duration; }
    public String getLanguage() { return language; }
}