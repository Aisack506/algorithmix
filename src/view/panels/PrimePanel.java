package view.panels;

import model.NumberTheory;
import javax.swing.*;
import java.awt.*;

public class PrimePanel extends JPanel {

    public PrimePanel(CardLayout cardLayout, JPanel mainContainer) {
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
        JLabel titleLabel = new JLabel("Prime Number Checker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // Input Label
        JLabel inputLabel = new JLabel("Enter integer:");
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
        JButton checkButton = new JButton("Check");
        checkButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        checkButton.setPreferredSize(new Dimension(200, 50));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(checkButton, gbc);

        // --- SCROLLABLE RESULT AREA ---
        JTextArea resultArea = new JTextArea(4, 25);
        resultArea.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultArea.setText("Result: ");
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        resultArea.setOpaque(true);
        resultArea.setBackground(new Color(45, 48, 60));
        resultArea.setForeground(new Color(230, 235, 245));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(600, 120));
        gbc.gridy = 3;
        contentPanel.add(scrollPane, gbc);

        // Event Listener
        checkButton.addActionListener(e -> {
            try {
                int number = Integer.parseInt(inputField.getText().trim());
                boolean isPrime = NumberTheory.isPrime(number);
                resultArea.setText("Result:\n" + number + " is " + (isPrime ? "Prime" : "Not Prime"));
            } catch (Throwable ex) {
                resultArea.setText("Result:\nPlease enter a valid integer.");
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}