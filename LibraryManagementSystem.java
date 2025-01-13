package BookNexus;

import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class LibraryManagementSystem extends Frame {
    private Library library;
    private TransactionRecords transactionRecords;
    private FineCalculation fineCalculator;
    private BorrowBooks borrowBooks;
    private ReturnBooks returnBooks;
    private SearchBooks searchBooks;
    private Reminders reminders;

    private TextField titleOrAuthorField;
    private TextField userIdField;
    private TextField bookIdField;
    private TextField returnDateField;
    private TextArea outputArea;

    public LibraryManagementSystem() {
        library = new Library();
        transactionRecords = new TransactionRecords();
        fineCalculator = new FineCalculation();
        borrowBooks = new BorrowBooks(library.getBookCatalog(), transactionRecords);
        returnBooks = new ReturnBooks(transactionRecords.getTransactions(), fineCalculator, library.getBookCatalog());
        searchBooks = new SearchBooks(library.getBookCatalog());
        reminders = new Reminders(transactionRecords.getTransactions());

        setTitle("Book Nexus Library Management System");
        setSize(600, 500);
        setLayout(new BorderLayout());
        setBackground(new Color(240, 240, 240)); // Light gray background for the frame

        // Create input panels
        Panel inputPanel = new Panel(new GridLayout(5, 2, 10, 10));
        inputPanel.setBackground(new Color(220, 220, 220)); // Slightly darker gray for the input panel

        inputPanel.add(new Label("Search (Title/Author):"));
        titleOrAuthorField = new TextField();
        inputPanel.add(titleOrAuthorField);

        inputPanel.add(new Label("User  ID:"));
        userIdField = new TextField();
        inputPanel.add(userIdField);

        inputPanel.add(new Label("Book ID:"));
        bookIdField = new TextField();
        inputPanel.add(bookIdField);

        inputPanel.add(new Label("Return Date (yyyy-MM-dd):"));
        returnDateField = new TextField();
        inputPanel.add(returnDateField);

        // Create buttons
        Button searchButton = new Button("Search");
        Button borrowButton = new Button("Borrow");
        Button returnButton = new Button("Return");
        Button remindersButton = new Button("Reminders");
        Button transactionsButton = new Button("Transactions");
        Button exitButton = new Button("Exit");

        // Set button colors
        searchButton.setBackground(new Color(100, 149, 237)); // Cornflower blue
        borrowButton.setBackground(new Color(60, 179, 113)); // Medium sea green
        returnButton.setBackground(new Color(255, 165, 0)); // Orange
        remindersButton.setBackground(new Color(255, 20, 147)); // Deep pink
        transactionsButton.setBackground(new Color(255, 69, 0)); // Red orange
        exitButton.setBackground(new Color(255, 0, 0)); // Red

        // Set button foreground color
        Color buttonTextColor = Color.WHITE;
        searchButton.setForeground(buttonTextColor);
        borrowButton.setForeground(buttonTextColor);
        returnButton.setForeground(buttonTextColor);
        remindersButton.setForeground(buttonTextColor);
        transactionsButton.setForeground(buttonTextColor);
        exitButton.setForeground(buttonTextColor);

        Panel buttonPanel = new Panel(new GridLayout(1, 6, 10, 10));
        buttonPanel.add(searchButton);
        buttonPanel.add(borrowButton);
        buttonPanel.add(returnButton);
        buttonPanel.add(remindersButton);
        buttonPanel.add(transactionsButton);
        buttonPanel.add(exitButton);

        outputArea = new TextArea();
        outputArea.setEditable(false);
        outputArea.setBackground(new Color(255, 255, 255)); // White background for output area
        outputArea.setForeground(Color.BLACK); // Black text color

        // Add action listeners
        searchButton.addActionListener(e -> handleSearch());
        borrowButton.addActionListener(e -> handleBorrow());
        returnButton.addActionListener(e -> handleReturn());
        remindersButton.addActionListener(e -> handleReminders());
        transactionsButton.addActionListener(e -> handleTransactions());
        exitButton.addActionListener(e -> System.exit(0));

        add(inputPanel, BorderLayout.NORTH);
        add(outputArea, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }

    // Handle search functionality
    private void handleSearch() {
        String query = titleOrAuthorField.getText().trim();
        if (query.isEmpty()) {
            outputArea.setText("Please enter a title or author to search.");
            return;
        }
        
        List<Book> resultsByTitle = searchBooks.searchByTitle(query);
        List<Book> resultsByAuthor = searchBooks.searchByAuthor(query);

        resultsByTitle.removeAll(resultsByAuthor);
        resultsByTitle.addAll(resultsByAuthor);
        
        if (resultsByTitle.isEmpty()) {
            outputArea.setText("No books found.");
        } else {
            StringBuilder output = new StringBuilder("Search Results:\n");
            for (Book book : resultsByTitle) {
                output.append(book).append("\n");
            }
            outputArea.setText(output.toString());
        }
    }

    // Handle borrow functionality
    private void handleBorrow() {
        String userId = userIdField.getText().trim();
        String bookId = bookIdField.getText().trim();
        if (userId.isEmpty() || bookId.isEmpty()) {
            outputArea.setText("Please enter both User ID and Book ID.");
            return;
        }
        
        String message = borrowBooks.borrowBook(userId, bookId);
        outputArea.setText(message);
    }

    // Handle return functionality
    private void handleReturn() {
        String userId = userIdField.getText().trim();
        String bookId = bookIdField.getText().trim();
        String dateStr = returnDateField.getText().trim(); // Get the return date string

        if (userId.isEmpty() || bookId.isEmpty() || dateStr.isEmpty()) {
            outputArea.setText("Please enter User ID, Book ID, and Return Date(Please use yyyy-MM-dd).");
            return;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date returnDate = sdf.parse(dateStr); // Parse the string into Date object

            String message = returnBooks.returnBook(userId, bookId, returnDate); // Pass the parsed date
            outputArea.setText(message);
        } catch (Exception e) {
            outputArea.setText("Invalid date format. Please use yyyy-MM-dd.");
        }
    }

    // Handle reminders functionality
    private void handleReminders() {
        StringBuilder output = new StringBuilder();
        Date currentDate = new Date();
        boolean found = false;

        for (Transaction transaction : transactionRecords.getTransactions()) {
            // Check if the transaction has a due date and if it's within 3 days
            if (transaction.getDueDate() != null &&
                (transaction.getDueDate().getTime() - currentDate.getTime() <= 3 * 24 * 60 * 60 * 1000)) { // Within 3 days
                output.append("Reminder: Book ").append(transaction.getBookId())
                      .append(" is due soon (Due Date: ").append(transaction.getDueDate()).append(")\n");
                found = true;
            }
        }

        if (!found) {
            output.append("No reminders at this time.");
        }

        outputArea.setText(output.toString()); 
    }

    private void handleTransactions() {
        List<Transaction> transactions = transactionRecords.getTransactions();
        if (transactions.isEmpty()) {
            outputArea.setText("No transactions found.");
        } else {
            StringBuilder output = new StringBuilder("Transaction Records:\n");
            for (Transaction transaction : transactions) {
                output.append("User  ID: ").append(transaction.getUserId())
                      .append(", Book ID: ").append(transaction.getBookId())
                      .append(", Borrow Date: ").append(transaction.getBorrowDate())
                      .append(", Due Date: ").append(transaction.getDueDate())
                      .append(", Return Date: ").append(transaction.getReturnDate())
                      .append(", Fine: rs.").append(transaction.getFine()).append("\n");
            }
            outputArea.setText(output.toString());
        }
    }

    public static void main(String[] args) {
        new LibraryManagementSystem();
    }
}