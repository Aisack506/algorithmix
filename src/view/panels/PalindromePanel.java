package view.panels;

import model.TextOperations;
import javax.swing.*;
import java.awt.*;

public class PalindromePanel extends JPanel {

    public PalindromePanel(CardLayout cardLayout, JPanel mainContainer) {
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
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Palindrome Text Checker", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // Input Label
        JLabel inputLabel = new JLabel("Enter text/phrase:");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(inputLabel, gbc);

        // Input Field
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 22));
        inputField.setPreferredSize(new Dimension(350, 48));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(inputField, gbc);

        // Action Button
        JButton checkButton = new JButton("Check Palindrome");
        checkButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        checkButton.setPreferredSize(new Dimension(240, 48));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(checkButton, gbc);

        // --- FORCED LARGE SCROLLABLE RESULT AREA ---
        JTextArea resultArea = new JTextArea();
        resultArea.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultArea.setText("Result: ");
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        resultArea.setOpaque(true);
        resultArea.setBackground(new Color(45, 48, 60));
        resultArea.setForeground(new Color(230, 235, 245));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(600, 180));
        scrollPane.setMinimumSize(new Dimension(600, 180)); // Prevents GridBagLayout from squishing it

        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(scrollPane, gbc);

        // Event Listener for Palindrome Check
        checkButton.addActionListener(e -> {
            try {
                String text = inputField.getText().trim();

                if (text.isEmpty()) {
                    resultArea.setText("Result:\nPlease enter some text to check.");
                    return;
                }

                // Reject if it contains numbers or symbols (allows only letters and spaces)
                if (!text.matches("^[a-zA-Z\\s]+$")) {
                    resultArea.setText("Result:\nInvalid input. Numbers and symbols are not accepted. Please use letters only.");
                    return;
                }

                boolean isPal = TextOperations.isPalindrome(text);

                if (isPal) {
                    resultArea.setText("Result:\n\"" + text + "\" is a valid palindrome!");
                } else {
                    resultArea.setText("Result:\n\"" + text + "\" is not a palindrome.");
                }

            } catch (Exception ex) {
                resultArea.setText("Result:\nAn error occurred while evaluating the text.");
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}