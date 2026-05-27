/**
 * Interfejs definiujący operacje na użytkownikach.
 */
public interface UserOperations {
    User login(String username, String password);
    boolean register(String username, String password);
}