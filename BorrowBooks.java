package BookNexus;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BorrowBooks {
    private List<Book> bookCatalog;
    private TransactionRecords transactionRecords;

    // Constructor
    public BorrowBooks(List<Book> books, TransactionRecords transactionRecords) {
        this.bookCatalog = books;
        this.transactionRecords = transactionRecords;
    }

    // Method to borrow a book
    public String borrowBook(String userId, String bookId) {
        for (Book book : bookCatalog) {
            if (book.getId().equals(bookId)) {
                if (book.isAvailable()) {
                    book.setAvailable(false); // Mark the book as borrowed
                    Transaction transaction = new Transaction(userId, bookId, new Date(), calculateDueDate());
                    transactionRecords.addTransaction(transaction); // Record the transaction
                    return "Book borrowed successfully. Due date: " + transaction.getDueDate();
                } else {
                    return "Book is currently not available.";
                }
            }
        }
        return "Book with ID " + bookId + " not found.";
    }

    // Method to calculate the due date (14 days from now)
    private Date calculateDueDate() {
        Calendar calendar = Calendar.getInstance(); // Get current date and time
        calendar.add(Calendar.DAY_OF_MONTH, 14); // Add 14 days
        return calendar.getTime(); // Return as Date object
    }
}
