import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Klasa zarządzająca operacjami na użytkownikach, implementuje interfejs UserOperations.
 */
public class UserDatabase implements UserOperations {
    private List<User> users;
    private String fileName;

    public UserDatabase(String fileName) {
        this.fileName = fileName;
        users = new ArrayList<>();
        loadUsers();
    }

    // Wczytanie użytkowników z pliku
    private void loadUsers() {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int id = 1;
            while ((line = br.readLine()) != null) {
                String username = line.trim();
                String password = br.readLine();
                if (password != null) {
                    password = password.trim();
                    users.add(new User(id++, username, password));
                }
            }
        } catch (IOException e) {
            System.err.println("Błąd podczas wczytywania bazy użytkowników: " + e.getMessage());
        }
    }

    // Implementacja logiki z interfejsu (logowanie, rejestracja)
    @Override
    public User login(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public boolean register(String username, String password) {
        // Czy login już istnieje
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return false;
            }
        }
        // Zapis do pliku, dodanie do listy
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName, true))) {
            bw.write(username);
            bw.newLine();
            bw.write(password);
            bw.newLine();
            users.add(new User(users.size() + 1, username, password));
            return true;
        } catch (IOException e) {
            System.err.println("Błąd podczas rejestracji: " + e.getMessage());
            return false;
        }
    }
}
