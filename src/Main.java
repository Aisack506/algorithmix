import view.MainWindow;
import com.formdev.flatlaf.FlatDarkLaf;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Initialize FlatLaf Dark Mode for a sleek, modern desktop look
        try {
            UIManager.setLookAndFeel(new FlatDarkLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf. Falling back to default look.");
        }

        // Launch the user interface safely on Swing's Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            MainWindow mainWindow = new MainWindow(); // Initialize dashboard
            mainWindow.setVisible(true); // Renders windows on the computer
        });
    }
}
