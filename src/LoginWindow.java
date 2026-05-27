import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

/**
 * Okno logowania
 */
public class LoginWindow extends BaseWindow {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton goToRegisterButton;
    private UserDatabase userDatabase;

    public LoginWindow(UserDatabase userDatabase) {
        super("Logowanie", 300, 200);
        this.userDatabase = userDatabase;
        initComponents();
    }

    @Override
    protected void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Nazwa użytkownika:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Hasło:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        loginButton = new JButton("Zaloguj");
        panel.add(loginButton);

        goToRegisterButton = new JButton("Rejestracja");
        panel.add(goToRegisterButton);

        add(panel);

        // Logika logowania
        loginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                User user = userDatabase.login(username, password);
                if (user != null) {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Zalogowano na konto " + user.getUsername());
                    // Wczytanie transakcji użytkownika
                    List<Transaction> loadedTransactions = TransactionRepository.loadTransactionsForUser(user.getId());
                    for (Transaction t : loadedTransactions) {
                        user.addTransaction(t);
                    }
                    // Okno dodawania transakcji
                    TransactionEntryWindow transactionWindow = new TransactionEntryWindow(user);
                    transactionWindow.setVisible(true);
                    dispose();
                }
                else {
                    JOptionPane.showMessageDialog(LoginWindow.this, "Błędna nazwa użytkownika lub hasło!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Logika rejestracji
        goToRegisterButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                RegistrationWindow regWindow = new RegistrationWindow(userDatabase);
                regWindow.setVisible(true);
                dispose();
            }
        });
    }
}
