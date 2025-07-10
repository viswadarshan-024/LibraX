package com.library.models.items;

import com.library.models.abstractt.LibraryItem;

public class EBook extends LibraryItem {
    private double fileSize;
    private String format;

    public EBook(String id, String title, double fileSize, String format) {
        super(id, title);
        this.fileSize = fileSize;
        this.format = format;
    }

    @Override
    public void displayDetails() {
        System.out.println("EBook [ID: " + id + ", Title: " + title + ", Size: " + fileSize +
                " MB, Format: " + format + ", Available: " + available + "]");
    }

    public double getFileSize() { return fileSize; }
    public String getFormat() { return format; }
}