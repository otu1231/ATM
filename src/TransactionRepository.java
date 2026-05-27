import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa odpowiedzialna za przechowywanie transakcji w pliku.
 */
public class TransactionRepository {
    private static final String FILE_NAME = "OOP_Finance_Manager/transactions.txt";

    // Zapisanie transakcji użytkownika
    public static void saveTransactionForUser(Transaction transaction, int userId) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            // Format: userId;amount;type;category
            bw.write(userId + ";" + transaction.getAmount() + ";" + transaction.getType() + ";" + transaction.getCategory());
            bw.newLine();
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania transakcji: " + e.getMessage());
        }
    }

    // Wczytanie transakcji użytkownika
    public static List<Transaction> loadTransactionsForUser(int userId) {
        List<Transaction> transactions = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Format: userId; amount; type; category
                String[] parts = line.split(";");
                if (parts.length >= 4) {
                    int fileUserId = Integer.parseInt(parts[0]);
                    if (fileUserId == userId) {
                        double amount = Double.parseDouble(parts[1]);
                        TransactionType type = TransactionType.valueOf(parts[2]);
                        String category = parts[3];
                        transactions.add(new Transaction(amount, type, category));
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania transakcji: " + e.getMessage());
        }
        return transactions;
    }
}
