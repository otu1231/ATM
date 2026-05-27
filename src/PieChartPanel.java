import javax.swing.*;
import java.awt.*;

/**
 * Panel rysujący wykres kołowy
 */
public class PieChartPanel extends JPanel {
    private double totalIncome;
    private double totalExpense;

    public PieChartPanel(double totalIncome, double totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        setPreferredSize(new Dimension(300, 300));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        double total = totalIncome + totalExpense;
        if (total == 0) {
            return;
        }
        int incomeAngle = (int) Math.round((totalIncome / total) * 360);
        int expenseAngle = 360 - incomeAngle;

        // Ustalenie pozycji i rozmiaru wykresu
        int diameter = Math.min(getWidth(), getHeight()) - 20;
        int x = (getWidth() - diameter) / 2;
        int y = (getHeight() - diameter) / 2;

        Graphics2D g2d = (Graphics2D) g;
        // Rysowanie dochodu
        g2d.setColor(Color.GREEN);
        g2d.fillArc(x, y, diameter, diameter, 0, incomeAngle);
        // Rysowanie wydatku
        g2d.setColor(Color.RED);
        g2d.fillArc(x, y, diameter, diameter, incomeAngle, expenseAngle);
    }
}
