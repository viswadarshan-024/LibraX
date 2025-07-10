package com.library.models.abstractt;

import com.library.models.abstractt.LibraryItem;

public abstract class Member {
    protected String name;
    protected String id;

    public Member(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public abstract void borrowItem(LibraryItem item);

    public void displayDetails() {
        System.out.println("Name: " + name + ", ID: " + id);
    }

    public String getName() { return name; }
    public String getId() { return id; }
}