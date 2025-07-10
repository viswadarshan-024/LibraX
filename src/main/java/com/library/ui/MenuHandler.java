package com.library.ui;

import com.library.services.LibrarySystem;
import com.library.models.abstractt.Member;
import com.library.models.users.Student;
import com.library.interfaces.ExamEnrollable;
import java.util.Scanner;
import java.util.List;
import com.library.models.abstractt.LibraryItem;
import com.library.models.BorrowRecord;
import com.library.models.items.Book;
import com.library.models.items.Magazine;
import com.library.models.items.DVD;
import com.library.models.items.EBook;
import com.library.models.users.Admin;

public class MenuHandler {
    private LibrarySystem library;
    private Scanner scanner;
    private Member currentUser;

    public MenuHandler() {
        this.library = new LibrarySystem();
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        System.out.println("=== SMART LIBRARY MANAGEMENT SYSTEM ===");

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else if (currentUser instanceof Admin) {
                showAdminMenu();
            } else {
                showStudentMenu();
            }
        }
    }

    private void showLoginMenu() {
        System.out.println("\n=== LOGIN MENU ===");
        System.out.println("1. Admin Login");
        System.out.println("2. Student Login");
        System.out.println("3. Register New Student");
        System.out.println("4. Exit");
        System.out.print("Choose option: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    studentLogin();
                    break;
                case 3:
                    registerStudent();
                    break;
                case 4:
                    System.out.println("Thank you for using the Library Management System!");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private void adminLogin() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        if (library.authenticateUser(username, password)) {
            currentUser = library.getUser(username);
            System.out.println("Admin login successful!");
        } else {
            System.out.println("Invalid credentials!");
        }
    }

    private void studentLogin() {
        System.out.print("Enter Student ID: ");
        String studentId = scanner.nextLine();

        if (library.authenticateUser(studentId, "")) {
            currentUser = library.getUser(studentId);
            System.out.println("Student login successful! Welcome, " + currentUser.getName());
        } else {
            System.out.println("Invalid Student ID!");
        }
    }

    private void registerStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter student ID: ");
        String id = scanner.nextLine();

        library.registerStudent(name, id);
    }

    private void showAdminMenu() {
        System.out.println("\n=== ADMIN MENU ===");
        System.out.println("1. Add Library Item");
        System.out.println("2. Remove Library Item");
        System.out.println("3. View All Items");
        System.out.println("4. View All Users");
        System.out.println("5. View All Borrowed Items");
        System.out.println("6. Generate Reports");
        System.out.println("7. Search Items");
        System.out.println("8. Logout");
        System.out.print("Choose option: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addLibraryItem();
                    break;
                case 2:
                    removeLibraryItem();
                    break;
                case 3:
                    library.displayAllItems();
                    break;
                case 4:
                    library.displayAllUsers();
                    break;
                case 5:
                    library.displayBorrowedItems();
                    break;
                case 6:
                    library.generateReport();
                    break;
                case 7:
                    searchItems();
                    break;
                case 8:
                    currentUser = null;
                    System.out.println("Logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private void showStudentMenu() {
        System.out.println("\n=== STUDENT MENU ===");
        System.out.println("1. Search Books");
        System.out.println("2. View Available Items");
        System.out.println("3. Borrow Item");
        System.out.println("4. Return Item");
        System.out.println("5. View Borrowing History");
        System.out.println("6. Check Due Dates & Fines");
        System.out.println("7. Enroll in Exam");
        System.out.println("8. Logout");
        System.out.print("Choose option: ");

        try {
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    searchItems();
                    break;
                case 2:
                    library.displayAvailableItems();
                    break;
                case 3:
                    borrowItem();
                    break;
                case 4:
                    returnItem();
                    break;
                case 5:
                    library.displayStudentHistory(currentUser.getId());
                    break;
                case 6:
                    checkDueDatesAndFines();
                    break;
                case 7:
                    if (currentUser instanceof ExamEnrollable) {
                        ((ExamEnrollable) currentUser).enrollInExam();
                    }
                    break;
                case 8:
                    currentUser = null;
                    System.out.println("Logged out successfully!");
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine();
        }
    }

    private void addLibraryItem() {
        System.out.println("\n=== ADD LIBRARY ITEM ===");
        System.out.println("1. Book");
        System.out.println("2. Magazine");
        System.out.println("3. DVD");
        System.out.println("4. EBook");
        System.out.print("Choose item type: ");

        try {
            int type = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter item ID: ");
            String id = scanner.nextLine();
            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            LibraryItem item = null;

            switch (type) {
                case 1:
                    System.out.print("Enter author: ");
                    String author = scanner.nextLine();
                    System.out.print("Enter genre: ");
                    String genre = scanner.nextLine();
                    item = new Book(id, title, author, genre);
                    break;
                case 2:
                    System.out.print("Enter issue number: ");
                    String issueNumber = scanner.nextLine();
                    System.out.print("Enter publisher: ");
                    String publisher = scanner.nextLine();
                    item = new Magazine(id, title, issueNumber, publisher);
                    break;
                case 3:
                    System.out.print("Enter duration (minutes): ");
                    int duration = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Enter language: ");
                    String language = scanner.nextLine();
                    item = new DVD(id, title, duration, language);
                    break;
                case 4:
                    System.out.print("Enter file size (MB): ");
                    double fileSize = scanner.nextDouble();
                    scanner.nextLine();
                    System.out.print("Enter format: ");
                    String format = scanner.nextLine();
                    item = new EBook(id, title, fileSize, format);
                    break;
                default:
                    System.out.println("Invalid item type.");
                    return;
            }

            library.addItem(item);
            System.out.println("Item added successfully!");

        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine();
        }
    }

    private void removeLibraryItem() {
        System.out.print("Enter item ID to remove: ");
        String itemId = scanner.nextLine();

        if (library.removeItem(itemId)) {
            System.out.println("Item removed successfully!");
        } else {
            System.out.println("Failed to remove item. Item may not exist or is currently borrowed.");
        }
    }

    private void searchItems() {
        System.out.print("Enter search keyword (title or author): ");
        String keyword = scanner.nextLine();

        List<LibraryItem> results = library.searchItem(keyword);

        if (results.isEmpty()) {
            System.out.println("No items found matching your search.");
        } else {
            System.out.println("\n=== SEARCH RESULTS ===");
            for (LibraryItem item : results) {
                item.displayDetails();
            }
        }
    }

    private void borrowItem() {
        System.out.print("Enter item ID to borrow: ");
        String itemId = scanner.nextLine();

        library.borrowItem(currentUser.getId(), itemId);
    }

    private void returnItem() {
        System.out.print("Enter item ID to return: ");
        String itemId = scanner.nextLine();

        library.returnItem(currentUser.getId(), itemId);
    }

    private void checkDueDatesAndFines() {
        System.out.println("\n=== DUE DATES & FINES ===");

        if (currentUser instanceof Student) {
            Student student = (Student) currentUser;
            List<String> borrowedItems = student.getBorrowedItems();

            if (borrowedItems.isEmpty()) {
                System.out.println("No items currently borrowed.");
                return;
            }

            double totalFine = 0.0;

            for (BorrowRecord record : student.getBorrowHistory()) {
                if (record.getReturnDate() == null) {
                    LibraryItem item = library.getItem(record.getItemId());
                    System.out.println("Item: " + item.getTitle());
                    System.out.println("  Borrowed: " + record.getBorrowDate());
                    System.out.println("  Due Date: " + record.getDueDate());

                    if (record.isOverdue()) {
                        int daysLate = record.getDaysLate();
                        double fine = student.calculateFine(daysLate);
                        totalFine += fine;
                        System.out.println("  Status: OVERDUE (" + daysLate + " days late)");
                        System.out.println("  Fine: ₹" + fine);
                    } else {
                        System.out.println("  Status: On Time");
                    }
                    System.out.println();
                }
            }

            System.out.println("Total Pending Fine: ₹" + totalFine);
        }
    }
}