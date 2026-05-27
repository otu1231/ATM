/**
 * Klasa reprezentująca transakcję (dochód lub wydatek).
 */
public class Transaction {
    private double amount;
    private TransactionType type;   // Dochód lub Wydatek
    private String category;

    public Transaction(double amount, TransactionType type, String category) {
        this.amount = amount;
        this.type = type;
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }
}
