package com.library.models.users;

import com.library.interfaces.FinesApplicable;
import com.library.interfaces.ExamEnrollable;
import com.library.models.abstractt.Member;
import com.library.models.abstractt.LibraryItem;
import com.library.models.BorrowRecord;
import java.util.*;

public class Student extends Member implements FinesApplicable, ExamEnrollable {
    private List<String> borrowedItems;
    private List<BorrowRecord> borrowHistory;
    private static final int MAX_BORROWED_ITEMS = 3;
    private static final double FINE_PER_DAY = 2.0;

    public Student(String name, String id) {
        super(name, id);
        this.borrowedItems = new ArrayList<>();
        this.borrowHistory = new ArrayList<>();
    }

    @Override
    public void borrowItem(LibraryItem item) {
        if (borrowedItems.size() >= MAX_BORROWED_ITEMS) {
            throw new IllegalStateException("Maximum borrowing limit reached (3 items)");
        }
        borrowedItems.add(item.getId());
        item.setAvailable(false);
    }

    @Override
    public double calculateFine(int daysLate) {
        return daysLate > 0 ? daysLate * FINE_PER_DAY : 0.0;
    }

    @Override
    public void enrollInExam() {
        System.out.println(name + " has been enrolled in the exam.");
    }

    public void returnItem(String itemId) {
        borrowedItems.remove(itemId);
    }

    public void addToBorrowHistory(BorrowRecord record) {
        borrowHistory.add(record);
    }

    public List<String> getBorrowedItems() { return new ArrayList<>(borrowedItems); }
    public List<BorrowRecord> getBorrowHistory() { return new ArrayList<>(borrowHistory); }
    public int getMaxBorrowedItems() { return MAX_BORROWED_ITEMS; }
}