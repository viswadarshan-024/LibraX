package com.library.services;

import com.library.models.abstractt.*;
import com.library.models.items.*;
import com.library.models.users.*;
import com.library.models.BorrowRecord;
import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;

public class LibrarySystem {
    private HashMap<String, LibraryItem> items;
    private HashMap<String, Member> users;
    private List<BorrowRecord> records;

    public LibrarySystem() {
        this.items = new HashMap<>();
        this.users = new HashMap<>();
        this.records = new ArrayList<>();
        initializeDefaultData();
    }

    private void initializeDefaultData() {
        users.put("admin", new Admin("Library Admin", "admin"));

        addItem(new Book("B001", "Java Programming", "John Doe", "Programming"));
        addItem(new Book("B002", "Data Structures", "Jane Smith", "Computer Science"));
        addItem(new Book("B003", "Web Development", "Bob Johnson", "Programming"));

        addItem(new Magazine("M001", "Tech Today", "Issue 45", "Tech Publications"));
        addItem(new Magazine("M002", "Science Monthly", "Issue 12", "Science Press"));

        addItem(new DVD("D001", "Introduction to Programming", 120, "English"));
        addItem(new DVD("D002", "Database Design", 90, "English"));

        addItem(new EBook("E001", "Python Basics", 15.5, "PDF"));
        addItem(new EBook("E002", "Machine Learning", 25.0, "EPUB"));
    }

    public void addItem(LibraryItem item) {
        items.put(item.getId(), item);
    }

    public boolean removeItem(String itemId) {
        if (items.containsKey(itemId)) {
            LibraryItem item = items.get(itemId);
            if (item.isAvailable()) {
                items.remove(itemId);
                return true;
            } else {
                System.out.println("Cannot remove item - currently borrowed");
                return false;
            }
        }
        return false;
    }

    public List<LibraryItem> searchItem(String keyword) {
        List<LibraryItem> results = new ArrayList<>();
        String lowerKeyword = keyword.toLowerCase();

        for (LibraryItem item : items.values()) {
            if (item.getTitle().toLowerCase().contains(lowerKeyword)) {
                results.add(item);
            }
            if (item instanceof Book) {
                Book book = (Book) item;
                if (book.getAuthor().toLowerCase().contains(lowerKeyword)) {
                    results.add(item);
                }
            }
        }
        return results;
    }

    public boolean borrowItem(String memberId, String itemId) {
        try {
            Member member = users.get(memberId);
            LibraryItem item = items.get(itemId);

            if (member == null || item == null) {
                System.out.println("Invalid member or item ID");
                return false;
            }

            if (!item.isAvailable()) {
                System.out.println("Item is not available for borrowing");
                return false;
            }

            if (member instanceof Student) {
                Student student = (Student) member;
                if (student.getBorrowedItems().size() >= student.getMaxBorrowedItems()) {
                    System.out.println("Maximum borrowing limit reached");
                    return false;
                }
            }

            member.borrowItem(item);
            BorrowRecord record = new BorrowRecord(memberId, itemId);
            records.add(record);

            if (member instanceof Student) {
                ((Student) member).addToBorrowHistory(record);
            }

            System.out.println("Item borrowed successfully! Due date: " + record.getDueDate());
            return true;

        } catch (Exception e) {
            System.out.println("Error borrowing item: " + e.getMessage());
            return false;
        }
    }

    public boolean returnItem(String memberId, String itemId) {
        try {
            Member member = users.get(memberId);
            LibraryItem item = items.get(itemId);

            if (member == null || item == null) {
                System.out.println("Invalid member or item ID");
                return false;
            }

            BorrowRecord record = null;
            for (BorrowRecord r : records) {
                if (r.getMemberId().equals(memberId) && r.getItemId().equals(itemId) && r.getReturnDate() == null) {
                    record = r;
                    break;
                }
            }

            if (record == null) {
                System.out.println("No active borrow record found");
                return false;
            }

            record.markReturned();
            record.calculateFine();
            item.setAvailable(true);

            if (member instanceof Student) {
                ((Student) member).returnItem(itemId);
            }

            if (record.getFine() > 0) {
                System.out.println("Item returned with fine: ₹" + record.getFine());
            } else {
                System.out.println("Item returned successfully!");
            }

            return true;

        } catch (Exception e) {
            System.out.println("Error returning item: " + e.getMessage());
            return false;
        }
    }

    public double calculateFine(String memberId) {
        double totalFine = 0.0;
        for (BorrowRecord record : records) {
            if (record.getMemberId().equals(memberId) && record.getReturnDate() == null) {
                if (record.isOverdue()) {
                    int daysLate = record.getDaysLate();
                    totalFine += daysLate * 2.0;
                }
            }
        }
        return totalFine;
    }

    public void generateReport() {
        System.out.println("\n=== LIBRARY REPORT ===");

        System.out.println("\n--- OVERDUE ITEMS ---");
        boolean hasOverdue = false;
        for (BorrowRecord record : records) {
            if (record.isOverdue()) {
                hasOverdue = true;
                LibraryItem item = items.get(record.getItemId());
                Member member = users.get(record.getMemberId());
                System.out.println("Item: " + item.getTitle() + " | Member: " + member.getName() +
                        " | Days Late: " + record.getDaysLate() + " | Fine: ₹" + (record.getDaysLate() * 2.0));
            }
        }
        if (!hasOverdue) {
            System.out.println("No overdue items");
        }

        System.out.println("\n--- FINE SUMMARY ---");
        HashMap<String, Double> finesByMember = new HashMap<>();
        for (BorrowRecord record : records) {
            if (record.getFine() > 0) {
                finesByMember.put(record.getMemberId(),
                        finesByMember.getOrDefault(record.getMemberId(), 0.0) + record.getFine());
            }
        }

        if (finesByMember.isEmpty()) {
            System.out.println("No fines recorded");
        } else {
            double totalFines = 0.0;
            for (Map.Entry<String, Double> entry : finesByMember.entrySet()) {
                Member member = users.get(entry.getKey());
                System.out.println("Member: " + member.getName() + " | Total Fine: ₹" + entry.getValue());
                totalFines += entry.getValue();
            }
            System.out.println("Total Fines Collected: ₹" + totalFines);
        }

        System.out.println("\n--- STATISTICS ---");
        System.out.println("Total Items: " + items.size());
        System.out.println("Total Users: " + (users.size() - 1));
        System.out.println("Total Borrow Records: " + records.size());

        long availableItems = items.values().stream().filter(LibraryItem::isAvailable).count();
        System.out.println("Available Items: " + availableItems);
        System.out.println("Borrowed Items: " + (items.size() - availableItems));
    }

    public void displayAllItems() {
        System.out.println("\n=== ALL LIBRARY ITEMS ===");
        if (items.isEmpty()) {
            System.out.println("No items in the library");
            return;
        }

        for (LibraryItem item : items.values()) {
            item.displayDetails();
        }
    }

    public void displayAvailableItems() {
        System.out.println("\n=== AVAILABLE ITEMS ===");
        boolean hasAvailable = false;
        for (LibraryItem item : items.values()) {
            if (item.isAvailable()) {
                item.displayDetails();
                hasAvailable = true;
            }
        }
        if (!hasAvailable) {
            System.out.println("No available items");
        }
    }

    public void displayAllUsers() {
        System.out.println("\n=== ALL REGISTERED USERS ===");
        for (Member member : users.values()) {
            if (!(member instanceof Admin)) {
                member.displayDetails();
            }
        }
    }

    public void displayBorrowedItems() {
        System.out.println("\n=== ALL BORROWED ITEMS ===");
        boolean hasBorrowed = false;
        for (BorrowRecord record : records) {
            if (record.getReturnDate() == null) {
                hasBorrowed = true;
                LibraryItem item = items.get(record.getItemId());
                Member member = users.get(record.getMemberId());
                System.out.println("Item: " + item.getTitle() + " | Borrowed by: " + member.getName() +
                        " | Due Date: " + record.getDueDate() +
                        " | Status: " + (record.isOverdue() ? "OVERDUE" : "On Time"));
            }
        }
        if (!hasBorrowed) {
            System.out.println("No items currently borrowed");
        }
    }

    public void registerStudent(String name, String id) {
        if (users.containsKey(id)) {
            System.out.println("User ID already exists");
            return;
        }
        users.put(id, new Student(name, id));
        System.out.println("Student registered successfully");
    }

    public boolean authenticateUser(String id, String password) {
        if (id.equals("admin") && password.equals("admin123")) {
            return true;
        }
        return users.containsKey(id);
    }

    public Member getUser(String id) {
        return users.get(id);
    }

    public LibraryItem getItem(String id) {
        return items.get(id);
    }

    public void displayStudentHistory(String studentId) {
        Member member = users.get(studentId);
        if (member instanceof Student) {
            Student student = (Student) member;
            System.out.println("\n=== BORROWING HISTORY FOR " + student.getName() + " ===");

            List<BorrowRecord> history = student.getBorrowHistory();
            if (history.isEmpty()) {
                System.out.println("No borrowing history");
                return;
            }

            for (BorrowRecord record : history) {
                LibraryItem item = items.get(record.getItemId());
                String status = record.getReturnDate() != null ? "Returned" : "Borrowed";
                System.out.println("Item: " + item.getTitle() + " | Borrowed: " + record.getBorrowDate() +
                        " | Due: " + record.getDueDate() + " | Status: " + status);
                if (record.getFine() > 0) {
                    System.out.println("  Fine: ₹" + record.getFine());
                }
            }

            double currentFine = calculateFine(studentId);
            if (currentFine > 0) {
                System.out.println("\nCurrent pending fine: ₹" + currentFine);
            }
        }
    }
}