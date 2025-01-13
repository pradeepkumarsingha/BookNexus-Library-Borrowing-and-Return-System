/* 
package BookNexus;

import java.util.ArrayList;
import java.util.List;

public class TransactionRecords {
    private List<Transaction> transactions;

    public TransactionRecords() {
        this.transactions = new ArrayList<>();
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }
}
*/
package BookNexus;

import java.util.ArrayList;

import java.util.List;

public class TransactionRecords {

    //Attributs

    private List<Transaction> transactions;

    //Constructeur

    public TransactionRecords() {

        this.transactions = new ArrayList<>();

    }

    //Méthodes

    public void addTransaction(Transaction transaction) {

        transactions.add(transaction);

    }

public List<Transaction> getTransactions() {

        return transactions;

    }

}


