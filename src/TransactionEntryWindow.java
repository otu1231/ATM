import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Okno do dodawania transakcji (wydatków/dochodu).
 */
public class TransactionEntryWindow extends BaseWindow {
    private User user;
    private JTextField amountField;
    private JTextField categoryField;
    private JRadioButton expenseRadio;
    private JRadioButton incomeRadio;
    private JButton addButton;
    private JButton summaryButton;

    public TransactionEntryWindow(User user) {
        super("Dodaj transakcję", 400, 250);
        this.user = user;
        initComponents();
    }

    @Override
    protected void initComponents() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 5, 5));

        panel.add(new JLabel("Kwota:"));
        amountField = new JTextField();
        panel.add(amountField);

        panel.add(new JLabel("Typ:"));
        JPanel typePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        expenseRadio = new JRadioButton("Wydatek");
        incomeRadio = new JRadioButton("Dochód");

        ButtonGroup typeGroup = new ButtonGroup();
        typeGroup.add(expenseRadio);
        typeGroup.add(incomeRadio);
        expenseRadio.setSelected(true);
        typePanel.add(expenseRadio);
        typePanel.add(incomeRadio);
        panel.add(typePanel);

        panel.add(new JLabel("Kategoria:"));
        categoryField = new JTextField();
        panel.add(categoryField);

        addButton = new JButton("Dodaj transakcję");
        panel.add(addButton);

        summaryButton = new JButton("Podsumowanie");
        panel.add(summaryButton);

        add(panel, BorderLayout.CENTER);

        // Dodanie transakcji
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double amount = Double.parseDouble(amountField.getText());
                    String category = categoryField.getText().trim();
                    TransactionType type = expenseRadio.isSelected() ? TransactionType.EXPENSE : TransactionType.INCOME;
                    Transaction transaction = new Transaction(amount, type, category);
                    user.addTransaction(transaction);
                    TransactionRepository.saveTransactionForUser(transaction, user.getId());
                    JOptionPane.showMessageDialog(TransactionEntryWindow.this, "Transakcja dodana pomyślnie!");

                    amountField.setText("");
                    categoryField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(TransactionEntryWindow.this, "Niepoprawna kwota!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Podsumowanie
        summaryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SummaryWindow summaryWindow = new SummaryWindow(user);
                summaryWindow.setVisible(true);
            }
        });
    }
}
