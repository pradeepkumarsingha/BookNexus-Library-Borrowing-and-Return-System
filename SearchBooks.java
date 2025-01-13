package BookNexus;

import java.util.ArrayList;
import java.util.List;

public class SearchBooks {
    private List<Book> bookCatalog;

    // Constructor
    public SearchBooks(List<Book> books) {
        this.bookCatalog = books;
    }

    // Method to search for books by title
    public List<Book> searchByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookCatalog) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // Method to search for books by author
    public List<Book> searchByAuthor(String author) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : bookCatalog) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    // Method to display search results
    public void displaySearchResults(List<Book> books) {
        if (books.isEmpty()) {
            System.out.println("No books found.");
        } else {
            System.out.println("Search Results:");
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
}