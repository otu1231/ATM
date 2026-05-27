import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Okno rejestracji
 */
public class RegistrationWindow extends BaseWindow {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;
    private JButton goToLoginButton;
    private UserDatabase userDatabase;

    public RegistrationWindow(UserDatabase userDatabase) {
        super("Rejestracja", 300, 200);
        this.userDatabase = userDatabase;
        initComponents();
    }

    @Override
    protected void initComponents() {
        JPanel panel = new JPanel(new GridLayout(4, 2));

        panel.add(new JLabel("Nowa nazwa użytkownika:"));
        usernameField = new JTextField();
        panel.add(usernameField);

        panel.add(new JLabel("Nowe hasło:"));
        passwordField = new JPasswordField();
        panel.add(passwordField);

        registerButton = new JButton("Zarejestruj się");
        panel.add(registerButton);

        goToLoginButton = new JButton("Powrót do logowania");
        panel.add(goToLoginButton);

        add(panel);

        // Logika Rejestracji
        registerButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                boolean success = userDatabase.register(username, password);
                if (success) {
                    JOptionPane.showMessageDialog(RegistrationWindow.this, "Rejestracja zakończona sukcesem!");
                } else {
                    JOptionPane.showMessageDialog(RegistrationWindow.this, "Użytkownik o takiej nazwie już istnieje lub wystąpił błąd!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Powrót do Logowania
        goToLoginButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginWindow loginWindow = new LoginWindow(userDatabase);
                loginWindow.setVisible(true);
                dispose();
            }
        });
    }
}
