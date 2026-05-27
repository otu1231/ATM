/**
 * Klasa startowa aplikacji.
 */
public class Main {
    public static void main(String[] args) {
        // Utworzenie bazy użytkowników
        UserDatabase userDatabase = new UserDatabase("OOP_Finance_Manager/Baza_uzytkownikow.txt");
        // Okno logowania
        LoginWindow loginWindow = new LoginWindow(userDatabase);
        loginWindow.setVisible(true);
    }
}
