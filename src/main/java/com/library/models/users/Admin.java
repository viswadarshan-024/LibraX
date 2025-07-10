package com.library.models.users;

import com.library.models.abstractt.Member;
import com.library.models.abstractt.LibraryItem;

public class Admin extends Member {
    public Admin(String name, String id) {
        super(name, id);
    }

    @Override
    public void borrowItem(LibraryItem item) {
        throw new UnsupportedOperationException("Admin cannot borrow items");
    }

    @Override
    public void displayDetails() {
        System.out.println("Admin - " + super.name + ", ID: " + super.id);
    }
}