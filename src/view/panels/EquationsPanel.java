package view.panels;

import model.GeometryAndAlgebra;
import javax.swing.*;
import java.awt.*;

public class EquationsPanel extends JPanel {

    public EquationsPanel(CardLayout cardLayout, JPanel mainContainer) {
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
        gbc.insets = new Insets(12, 12, 12, 12);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        JLabel titleLabel = new JLabel("Equation Solver", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // --- TYPE SELECTOR (Linear vs Quadratic) ---
        JLabel typeLabel = new JLabel("Equation Type:");
        typeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(typeLabel, gbc);

        String[] equationTypes = { "Linear (ax + b = 0)", "Quadratic (ax² + bx + c = 0)" };
        JComboBox<String> typeComboBox = new JComboBox<>(equationTypes);
        typeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        typeComboBox.setPreferredSize(new Dimension(320, 42));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(typeComboBox, gbc);

        // --- DYNAMIC INPUT PANELS ---
        JPanel linearFieldsPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        linearFieldsPanel.setOpaque(false);
        JTextField linAField = new JTextField();
        linAField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        JTextField linBField = new JTextField();
        linBField.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        linearFieldsPanel.add(new JLabel("Coefficient a:"));
        linearFieldsPanel.add(linAField);
        linearFieldsPanel.add(new JLabel("Coefficient b:"));
        linearFieldsPanel.add(linBField);

        JPanel quadFieldsPanel = new JPanel(new GridLayout(3, 2, 10, 10));
        quadFieldsPanel.setOpaque(false);
        JTextField quadAField = new JTextField();
        quadAField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        JTextField quadBField = new JTextField();
        quadBField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        JTextField quadCField = new JTextField();
        quadCField.setFont(new Font("Segoe UI", Font.PLAIN, 18));

        quadFieldsPanel.add(new JLabel("Coefficient a:"));
        quadFieldsPanel.add(quadAField);
        quadFieldsPanel.add(new JLabel("Coefficient b:"));
        quadFieldsPanel.add(quadBField);
        quadFieldsPanel.add(new JLabel("Coefficient c:"));
        quadFieldsPanel.add(quadCField);

        JPanel dynamicInputsWrapper = new JPanel(new CardLayout());
        dynamicInputsWrapper.setOpaque(false);
        dynamicInputsWrapper.add(linearFieldsPanel, "LINEAR");
        dynamicInputsWrapper.add(quadFieldsPanel, "QUADRATIC");

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(dynamicInputsWrapper, gbc);

        CardLayout inputsCardLayout = (CardLayout) dynamicInputsWrapper.getLayout();
        typeComboBox.addActionListener(e -> {
            if (typeComboBox.getSelectedIndex() == 0) {
                inputsCardLayout.show(dynamicInputsWrapper, "LINEAR");
            } else {
                inputsCardLayout.show(dynamicInputsWrapper, "QUADRATIC");
            }
        });

        // Action Button
        JButton solveButton = new JButton("Solve Equation");
        solveButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        solveButton.setPreferredSize(new Dimension(220, 48));
        gbc.gridy = 3;
        contentPanel.add(solveButton, gbc);

        // --- FORCED LARGE SCROLLABLE RESULT AREA ---
        JTextArea resultArea = new JTextArea();
        resultArea.setFont(new Font("Segoe UI", Font.BOLD, 18));
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setEditable(false);
        resultArea.setOpaque(true);
        resultArea.setBackground(new Color(45, 48, 60));
        resultArea.setForeground(new Color(230, 235, 245));

        JScrollPane scrollPane = new JScrollPane(resultArea);
        scrollPane.setPreferredSize(new Dimension(600, 180));
        scrollPane.setMinimumSize(new Dimension(600, 150)); // Prevents GridBagLayout from crushing it

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(scrollPane, gbc);

        // Event Listener for Solving
        solveButton.addActionListener(e -> {
            try {
                if (typeComboBox.getSelectedIndex() == 0) {
                    double a = Double.parseDouble(linAField.getText().trim());
                    double b = Double.parseDouble(linBField.getText().trim());
                    String solution = GeometryAndAlgebra.solveLinear(a, b);
                    resultArea.setText("Result:\nLinear Equation Solution -> " + solution);
                } else {
                    double a = Double.parseDouble(quadAField.getText().trim());
                    double b = Double.parseDouble(quadBField.getText().trim());
                    double c = Double.parseDouble(quadCField.getText().trim());

                    String[] solutions = GeometryAndAlgebra.solveQuadratic(a, b, c);
                    StringBuilder sb = new StringBuilder("Result:\nQuadratic Solutions:\n");
                    for (String sol : solutions) {
                        sb.append("• ").append(sol).append("\n");
                    }
                    resultArea.setText(sb.toString().trim());
                }
            } catch (NumberFormatException ex) {
                resultArea.setText("Result:\nPlease enter valid numeric values for coefficients.");
            } catch (ArithmeticException ex) {
                resultArea.setText("Result:\nCalculation Error: " + ex.getMessage());
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}