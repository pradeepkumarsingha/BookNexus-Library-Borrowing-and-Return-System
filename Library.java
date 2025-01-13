package BookNexus;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private List<Book> bookCatalog;

    public Library() {
        bookCatalog = new ArrayList<>();
        initializeBooks();
    }

    private void initializeBooks() {
        bookCatalog.add(new Book("B001", "Programming in java", "balagurusamy"));
        bookCatalog.add(new Book("B002", "C Programming: A Modern Approach", "K. N. King"));
        bookCatalog.add(new Book("B006", "Introduction to the Theory of Computation", "Michael Sipser"));
        bookCatalog.add(new Book("B007", "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin"));
        bookCatalog.add(new Book("B008", "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma, Richard Helm, Ralph Johnson, John Vlissides"));
        bookCatalog.add(new Book("B009", "Artificial Intelligence: A Modern Approach", "Stuart Russell and Peter Norvig"));
        bookCatalog.add(new Book("B010", "The Pragmatic Programmer: Your Journey To Mastery", "Andrew Hunt and David Thomas"));
        bookCatalog.add(new Book("B011", "Computer Networking: A Top-Down Approach", "James Kurose and Keith Ross"));
        bookCatalog.add(new Book("B012", "Introduction to Algorithms", "Thomas H. Cormen, Charles E. Leiserson, Ronald L. Rivest, and Clifford Stein"));
        bookCatalog.add(new Book("B013", "The Mythical Man-Month: Essays on Software Engineering", "Frederick P. Brooks Jr."));
        bookCatalog.add(new Book("B014", "The Art of Computer Programming", "Donald E. Knuth"));
        bookCatalog.add(new Book("B015", "Code Complete: A Practical Handbook of Software Construction", "Steve McConnell"));
        bookCatalog.add(new Book("B016", "Operating System Concepts", "Abraham Silberschatz, Peter B. Galvin, and Greg Gagne"));
        bookCatalog.add(new Book("B017", "Computer Architecture: A Quantitative Approach", "John L. Hennessy and David A. Patterson"));
        bookCatalog.add(new Book("B018", "Artificial Intelligence: Foundations of Computational Agents", "David L. Poole and Alan K. Mackworth"));
        bookCatalog.add(new Book("B019", "Introduction to Machine Learning", "Ethem Alpaydin"));
        bookCatalog.add(new Book("B020", "You Don’t Know JS: Scope & Closures", "Kyle Simpson"));
        bookCatalog.add(new Book("B021", "Deep Learning", "Ian Goodfellow, Yoshua Bengio, and Aaron Courville"));
        bookCatalog.add(new Book("B022", "The C Programming Language", "Brian W. Kernighan and Dennis M. Ritchie"));
        bookCatalog.add(new Book("B023", "Computer Vision: Algorithms and Applications", "Richard Szeliski"));

        
    }

    public List<Book> getBookCatalog() {
        return bookCatalog;
    }
}
