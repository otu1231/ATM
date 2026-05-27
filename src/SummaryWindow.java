import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Okno podsumowania transakcji.
 */
public class SummaryWindow extends BaseWindow {
    private User user; // Zalogowany użytkownik
    private JTable transactionTable;
    private JLabel balanceLabel;
    private PieChartPanel pieChartPanel;

    public SummaryWindow(User user) {
        super("Podsumowanie transakcji", 1200, 800);
        this.user = user;
        initComponents();
    }

    @Override
    protected void initComponents() {
        setLayout(new BorderLayout());

        // Tabela transakcji
        String[] columnNames = {"Kwota", "Typ", "Kategoria"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        transactionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(transactionTable);
        add(scrollPane, BorderLayout.CENTER);

        // Wypełnienie tabeli
        List<Transaction> transactions = user.getTransactions();
        double totalIncome = 0;
        double totalExpense = 0;
        for (Transaction t : transactions) {
            Object[] row = new Object[3];
            row[0] = t.getAmount();
            row[1] = t.getType().toString();
            row[2] = t.getCategory();
            tableModel.addRow(row);
            if (t.getType() == TransactionType.INCOME) {
                totalIncome += t.getAmount();
            } else if (t.getType() == TransactionType.EXPENSE) {
                totalExpense += t.getAmount();
            }
        }
        double balance = totalIncome - totalExpense;

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        balanceLabel = new JLabel("Stan Konta: " + balance);
        balanceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        bottomPanel.add(balanceLabel, BorderLayout.NORTH);

        pieChartPanel = new PieChartPanel(totalIncome, totalExpense);
        bottomPanel.add(pieChartPanel, BorderLayout.CENTER);

        JPanel legendPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel incomeLegend = new JLabel("Dochód");
        incomeLegend.setForeground(Color.GREEN);
        JLabel expenseLegend = new JLabel("Wydatek");
        expenseLegend.setForeground(Color.RED);
        legendPanel.add(incomeLegend);
        legendPanel.add(Box.createHorizontalStrut(20)); // Odstęp między etykietami
        legendPanel.add(expenseLegend);
        bottomPanel.add(legendPanel, BorderLayout.SOUTH);

        JPanel southPanel = new JPanel(new BorderLayout());
        southPanel.add(bottomPanel, BorderLayout.CENTER);
        JPanel backPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton backButton = new JButton("Powrót");
        backPanel.add(backButton);
        southPanel.add(backPanel, BorderLayout.SOUTH);
        add(southPanel, BorderLayout.SOUTH);

        // Powrót do okna dodawania transakcji
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TransactionEntryWindow transactionWindow = new TransactionEntryWindow(user);
                transactionWindow.setVisible(false);
                dispose();
            }
        });
    }
}
