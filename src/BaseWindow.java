import javax.swing.*;

/**
 * Abstrakcyjna klasa bazowa dla okien GUI.
 * Dziedziczenie: wszystkie okna dziedziczą wspólne ustawienia.
 */
public abstract class BaseWindow extends JFrame {
    public BaseWindow(String title, int width, int height) {
        super(title);
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Wyśrodkowanie
    }

    // Wymuszenie inicjalizacji komponentów GUI
    protected abstract void initComponents();
}
