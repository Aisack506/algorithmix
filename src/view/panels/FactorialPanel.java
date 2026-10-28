package view.panels;

import model.NumberTheory;
import javax.swing.*;
import java.awt.*;
import java.math.BigInteger;

public class FactorialPanel extends JPanel {

    public FactorialPanel(CardLayout cardLayout, JPanel mainContainer) {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // --- TOP CONTAINER ---
        JPanel topContainer = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        topContainer.setOpaque(false);

        JButton backButton = new JButton("← Back to Menu");
        backButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.addActionListener(e -> cardLayout.show(mainContainer, "MAIN_MENU"));
        topContainer.add(backButton);

        add(topContainer, BorderLayout.NORTH);

        // --- CENTER CONTENT PANEL ---
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Factorial Calculator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // Input Label
        JLabel inputLabel = new JLabel("Enter non-negative integer:");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(inputLabel, gbc);

        // Input Field
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        inputField.setPreferredSize(new Dimension(300, 48));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(inputField, gbc);

        // Action Button
        JButton calcButton = new JButton("Calculate");
        calcButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        calcButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(calcButton, gbc);

        // --- SCROLLABLE RESULT AREA FOR GIANT FACTORIALS ---
        JTextArea resultArea = new JTextArea(4, 25);
        resultArea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultArea.setText("Result: ");
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        resultArea.setOpaque(true);
        resultArea.setBackground(new Color(45, 48, 60));
        resultArea.setForeground(new Color(230, 235, 245));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(600, 140));
        gbc.gridy = 3;
        contentPanel.add(scrollPane, gbc);

        calcButton.addActionListener(e -> {
            try {
                int number = Integer.parseInt(inputField.getText().trim());

                if (number < 0) {
                    resultArea.setText("Result:\nFactorial is not defined for negative numbers.");
                    return;
                }

                int MAX_SAFE_LIMIT = 1000;
                if (number > MAX_SAFE_LIMIT) {
                    resultArea.setText("Result:\nInput too large! Max allowed is " + MAX_SAFE_LIMIT + " to keep the app responsive.");
                    return;
                }

                BigInteger result = NumberTheory.calculateFactorial(number);
                resultArea.setText("Result:\n" + number + "! =\n" + result.toString());

            } catch (NumberFormatException ex) {
                resultArea.setText("Result:\nPlease enter a valid integer.");
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}