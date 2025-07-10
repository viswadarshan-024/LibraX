package com.library.models.items;

import com.library.models.abstractt.LibraryItem;

public class Magazine extends LibraryItem {
    private String issueNumber;
    private String publisher;

    public Magazine(String id, String title, String issueNumber, String publisher) {
        super(id, title);
        this.issueNumber = issueNumber;
        this.publisher = publisher;
    }

    @Override
    public void displayDetails() {
        System.out.println("Magazine [ID: " + id + ", Title: " + title + ", Issue: " + issueNumber +
                ", Publisher: " + publisher + ", Available: " + available + "]");
    }

    public String getIssueNumber() { return issueNumber; }
    public String getPublisher() { return publisher; }
}