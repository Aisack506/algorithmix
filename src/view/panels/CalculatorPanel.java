package view.panels;
import model.CalculatorOperations;
import javax.swing.*;
import java.awt.*;

public class CalculatorPanel extends JPanel {

    private final JTextField displayField;
    private boolean startOfNewExpression = true;

    public CalculatorPanel(CardLayout cardLayout, JPanel mainContainer) {
        // Defines horizontal and vertical separation between components
        setLayout(new BorderLayout(15, 15));
        // Add border to a component
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // --- TOP CONTAINER ---
        JPanel topContainer = new JPanel(new BorderLayout(0, 10));
        topContainer.setOpaque(false);

        JButton backButton = new JButton("← Back to Menu");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 12));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> {
            cardLayout.show(mainContainer, "MAIN_MENU");
        });

        JPanel backButtonWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        backButtonWrapper.setOpaque(false);
        backButtonWrapper.add(backButton);
        topContainer.add(backButtonWrapper, BorderLayout.NORTH);

        // --- TOP DISPLAY SCREEN ---
        displayField = new JTextField("0");
        displayField.setFont(new Font("Segoe UI", Font.BOLD, 70));
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setEditable(false);
        displayField.setBackground(new Color(40, 42, 54));
        displayField.setForeground(Color.WHITE);
        displayField.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        topContainer.add(displayField, BorderLayout.CENTER);

        add(topContainer, BorderLayout.NORTH);

        // Creates an empty button grid structure
        JPanel buttonGrid = new JPanel(new GridLayout(4, 4, 10, 10));

        // Define buttons
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "x",
                "1", "2", "3", "-",
                "0", "C", "=", "+"
        };

        // Add each button to "buttonGrid"
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Segoe UI", Font.BOLD, 60));

            btn.addActionListener(e -> {
                String command = e.getActionCommand();
                String currentText = displayField.getText();

                if (command.equals("C")) {
                    displayField.setText("0");
                    startOfNewExpression = true;
                }
                else if (command.equals("=")) {
                    // Call backend
                    try {
                        double result = CalculatorOperations.calculator(currentText);
                        // Format
                        if (result == (long) result) {
                            displayField.setText(String.valueOf((long) result));
                        } else {
                            displayField.setText(String.valueOf(result));
                        }
                        startOfNewExpression = false; // The next number will be restarted
                    } catch (Exception ex) {
                        displayField.setText("Error");
                        startOfNewExpression = true;
                    }
                }
                else {
                    // Add numbers or expression to the window
                    if (startOfNewExpression || currentText.equals("0") || currentText.equals("Error")) {
                        displayField.setText(command);
                    } else {
                        displayField.setText(currentText + command);
                    }
                    startOfNewExpression = false;
                }
            });

            buttonGrid.add(btn);
        }

        // Display button grid
        add(buttonGrid, BorderLayout.CENTER);
    }
}