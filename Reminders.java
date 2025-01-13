package BookNexus;

import java.util.Date;
import java.util.List;

public class Reminders {
    private List<Transaction> transactions;

    public Reminders(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void checkReminders() {
        Date currentDate = new Date();
        StringBuilder reminders = new StringBuilder("Reminders:\n");
        boolean found = false;

        for (Transaction transaction : transactions) {
            // Check if the transaction has a due date and if it's within 3 days
            if (transaction.getDueDate() != null && 
                (transaction.getDueDate().getTime() - currentDate.getTime() <= 3 * 24 * 60 * 60 * 1000) && // Due within 3 days
                (transaction.getDueDate().getTime() - currentDate.getTime() >= 0)) { // Ensure it's not overdue
                reminders.append("Reminder: Book ").append(transaction.getBookId())
                        .append(" is due soon (Due Date: ").append(transaction.getDueDate()).append(")\n");
                found = true;
            }
        }

        if (!found) {
            reminders.append("No reminders at this time.");
        }

        System.out.println(reminders.toString()); // Display reminders in console or GUI output area
    }
}
