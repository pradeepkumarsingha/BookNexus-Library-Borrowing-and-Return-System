package BookNexus;

import java.util.Date;

public class Transaction {
    private String userId;
    private String bookId;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private double fine;

    public Transaction(String userId, String bookId, Date borrowDate, Date dueDate) {
        this.userId = userId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.returnDate = null; // Not returned yet
        this.fine = 0.0; // No fine initially
    }

    // Getters and Setters
    public String getUserId() {
        return userId;
    }

    public String getBookId() {
        return bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }
}
