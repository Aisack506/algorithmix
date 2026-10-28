package view.panels;

import model.NumberTheory;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class FibonacciPanel extends JPanel {

    public FibonacciPanel(CardLayout cardLayout, JPanel mainContainer) {
        setLayout(new BorderLayout(20, 20));
        setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // --- TOP CONTAINER (Back Button) ---
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
        JLabel titleLabel = new JLabel("Fibonacci Sequence Generator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // Input Label
        JLabel inputLabel = new JLabel("Enter max elements:");
        inputLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(inputLabel, gbc);

        // Input Field
        JTextField inputField = new JTextField();
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        inputField.setPreferredSize(new Dimension(220, 42));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(inputField, gbc);

        // Generate Button
        JButton genButton = new JButton("Generate");
        genButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        genButton.setPreferredSize(new Dimension(160, 45));
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(genButton, gbc);

        // --- SCROLLABLE RESULT AREA ---
        JTextArea resultArea = new JTextArea(4, 25);
        resultArea.setFont(new Font("Segoe UI", Font.BOLD, 16));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        resultArea.setOpaque(true);
        resultArea.setBackground(new Color(45, 48, 60));
        resultArea.setForeground(new Color(230, 235, 245));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(500, 100));
        gbc.gridy = 3;
        contentPanel.add(scrollPane, gbc);

        genButton.addActionListener(e -> {
            try {
                int max = Integer.parseInt(inputField.getText().trim());
                if (max <= 0) {
                    resultArea.setText("Result: Please enter a number greater than 0.");
                } else if (max > 10000) {
                    resultArea.setText("Result: Input too large! Max allowed elements is 90 to prevent number overflow.");
                } else {
                    List<Integer> sequence = NumberTheory.calculateFibonacci(max);
                    String result = sequence.toString().replace("[", "").replace("]", "");
                    resultArea.setText("Result:\n" + result);
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Result: Please enter a valid integer.");
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}