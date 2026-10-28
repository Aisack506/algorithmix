package view.panels;

import model.GeometryAndAlgebra;
import javax.swing.*;
import java.awt.*;

public class TrianglePanel extends JPanel {

    public TrianglePanel(CardLayout cardLayout, JPanel mainContainer) {
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
        JLabel titleLabel = new JLabel("Triangle Classifier", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // --- SIDE A ---
        JLabel lblSideA = new JLabel("Side A:");
        lblSideA.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblSideA, gbc);

        JTextField txtSideA = new JTextField(12);
        txtSideA.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(txtSideA, gbc);

        // --- SIDE B ---
        JLabel lblSideB = new JLabel("Side B:");
        lblSideB.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblSideB, gbc);

        JTextField txtSideB = new JTextField(12);
        txtSideB.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(txtSideB, gbc);

        // --- SIDE C ---
        JLabel lblSideC = new JLabel("Side C:");
        lblSideC.setFont(new Font("Segoe UI", Font.BOLD, 18));
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(lblSideC, gbc);

        JTextField txtSideC = new JTextField(12);
        txtSideC.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(txtSideC, gbc);

        // --- ACTION BUTTON ---
        JButton classifyButton = new JButton("Classify Triangle");
        classifyButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        classifyButton.setPreferredSize(new Dimension(220, 48));
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(classifyButton, gbc);

        // --- LARGE SCROLLABLE RESULT AREA ---
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
        scrollPane.setPreferredSize(new Dimension(600, 150));
        scrollPane.setMinimumSize(new Dimension(600, 150));

        gbc.gridy = 5;
        contentPanel.add(scrollPane, gbc);

        // --- ACTION LISTENER ---
        classifyButton.addActionListener(e -> {
            try {
                double a = Double.parseDouble(txtSideA.getText().trim());
                double b = Double.parseDouble(txtSideB.getText().trim());
                double c = Double.parseDouble(txtSideC.getText().trim());

                String result = GeometryAndAlgebra.classifyTriangle(a, b, c);
                resultArea.setText("Result:\n" + result);

            } catch (NumberFormatException ex) {
                resultArea.setText("Result:\nPlease enter valid numeric values for all sides.");
            } catch (Exception ex) {
                resultArea.setText("Result:\nAn error occurred while evaluating the triangle.");
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}