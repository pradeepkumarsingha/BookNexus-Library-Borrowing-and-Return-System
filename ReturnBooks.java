
package BookNexus;

import java.util.Date;
import java.util.List;

public class ReturnBooks {
    private List<Transaction> transactions;
    private FineCalculation fineCalculator;
    private List<Book> bookCatalog;

    // Constructor
    public ReturnBooks(List<Transaction> transactions, FineCalculation fineCalculator, List<Book> bookCatalog) {
        this.transactions = transactions;
        this.fineCalculator = fineCalculator;
        this.bookCatalog = bookCatalog; // Initialize the book catalog
    }

    // Method to return a book with a specified return date
    public String returnBook(String userId, String bookId, Date returnDate) {
        for (Transaction transaction : transactions) {
            if (transaction.getUserId().equals(userId) && transaction.getBookId().equals(bookId) && transaction.getReturnDate() == null) {
                // Set the return date provided by the user
                transaction.setReturnDate(returnDate); 
                double fine = fineCalculator.calculateFine(transaction); // Calculate any fines
                transaction.setFine(fine); // Set the fine

                // Mark the book as available again
                for (Book book : bookCatalog) {
                    if (book.getId().equals(bookId)) {
                        book.setAvailable(true);
                        break; // Exit loop once the correct book is found
                    }
                }

                return "Book returned successfully. Fine: $" + fine;
            }
        }
        return "No active transaction found for this user and book.";
    }
}
