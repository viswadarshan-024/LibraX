package com.library;

import com.library.ui.MenuHandler;

public class LibraryManagementSystem {
    public static void main(String[] args) {
        try {
            MenuHandler menuHandler = new MenuHandler();
            menuHandler.start();
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}