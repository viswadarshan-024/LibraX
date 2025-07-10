package com.library.models;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowRecord {
    private String memberId;
    private String itemId;
    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;
    private double fine;
    private static final int BORROWING_PERIOD_DAYS = 14;

    public BorrowRecord(String memberId, String itemId) {
        this.memberId = memberId;
        this.itemId = itemId;
        this.borrowDate = LocalDate.now();
        this.dueDate = borrowDate.plusDays(BORROWING_PERIOD_DAYS);
        this.fine = 0.0;
    }

    public boolean isOverdue() {
        return LocalDate.now().isAfter(dueDate) && returnDate == null;
    }

    public int getDaysLate() {
        LocalDate compareDate = returnDate != null ? returnDate : LocalDate.now();
        return (int) ChronoUnit.DAYS.between(dueDate, compareDate);
    }

    public void markReturned() {
        this.returnDate = LocalDate.now();
    }

    public void calculateFine() {
        int daysLate = getDaysLate();
        if (daysLate > 0) {
            this.fine = daysLate * 2.0;
        }
    }

    public String getMemberId() { return memberId; }
    public String getItemId() { return itemId; }
    public LocalDate getBorrowDate() { return borrowDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnDate() { return returnDate; }
    public double getFine() { return fine; }
    public void setFine(double fine) { this.fine = fine; }
}