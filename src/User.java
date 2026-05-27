import java.util.ArrayList;
import java.util.List;

/**
 * Klasa reprezentująca użytkownika.
 */
public class User {
    private int id;
    private String username;
    private String password;
    private List<Transaction> transactions;

    public User(int id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.transactions = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
}
