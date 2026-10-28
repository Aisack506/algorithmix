package view.panels;

import model.ConversionOperations;
import javax.swing.*;
import java.awt.*;

public class RomanPanel extends JPanel {

    public RomanPanel(CardLayout cardLayout, JPanel mainContainer) {
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
        JLabel titleLabel = new JLabel("Roman Numerals Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // --- CONVERSION DIRECTION SELECTOR ---
        JLabel modeLabel = new JLabel("Conversion Type:");
        modeLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = 1; gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(modeLabel, gbc);

        String[] modes = { "Integer to Roman", "Roman to Integer" };
        JComboBox<String> modeComboBox = new JComboBox<>(modes);
        modeComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        modeComboBox.setPreferredSize(new Dimension(280, 42));
        gbc.gridx = 1; gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(modeComboBox, gbc);

        // --- SUB-PANELS FOR INPUTS ---
        // Int to Roman Sub-Panel
        JPanel intToRomanPanel = new JPanel(new GridBagLayout());
        intToRomanPanel.setOpaque(false);
        GridBagConstraints igbc = new GridBagConstraints();
        igbc.insets = new Insets(8, 8, 8, 8);
        igbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField intInputField = new JTextField(12);
        intInputField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        igbc.gridx = 0; igbc.gridy = 0; intToRomanPanel.add(new JLabel("Integer (1-3999):"), igbc);
        igbc.gridx = 1; intToRomanPanel.add(intInputField, igbc);

        // Roman to Int Sub-Panel
        JPanel romanToIntPanel = new JPanel(new GridBagLayout());
        romanToIntPanel.setOpaque(false);
        GridBagConstraints rgbc = new GridBagConstraints();
        rgbc.insets = new Insets(8, 8, 8, 8);
        rgbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField romanInputField = new JTextField(12);
        romanInputField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        rgbc.gridx = 0; rgbc.gridy = 0; romanToIntPanel.add(new JLabel("Roman Numeral:"), rgbc);
        rgbc.gridx = 1; romanToIntPanel.add(romanInputField, rgbc);

        // --- WRAPPER FOR SUB-PANELS ---
        JPanel subWrapper = new JPanel(new CardLayout());
        subWrapper.setOpaque(false);
        subWrapper.add(intToRomanPanel, "INT_TO_ROMAN");
        subWrapper.add(romanToIntPanel, "ROMAN_TO_INT");

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(subWrapper, gbc);

        CardLayout subCardLayout = (CardLayout) subWrapper.getLayout();
        modeComboBox.addActionListener(e -> {
            if (modeComboBox.getSelectedIndex() == 0) {
                subCardLayout.show(subWrapper, "INT_TO_ROMAN");
            } else {
                subCardLayout.show(subWrapper, "ROMAN_TO_INT");
            }
        });

        // --- ACTION BUTTON ---
        JButton convertButton = new JButton("Convert");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        convertButton.setPreferredSize(new Dimension(200, 48));
        gbc.gridy = 3;
        contentPanel.add(convertButton, gbc);

        // --- RESULT AREA ---
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

        gbc.gridy = 4;
        contentPanel.add(scrollPane, gbc);

        // --- ACTION LISTENER ---
        convertButton.addActionListener(e -> {
            try {
                int modeIndex = modeComboBox.getSelectedIndex();
                String output = "";

                if (modeIndex == 0) {
                    int val = Integer.parseInt(intInputField.getText().trim());
                    output = val + " in Roman Numerals is: " + ConversionOperations.intToRoman(val);
                } else {
                    String romanVal = romanInputField.getText().trim().toUpperCase();
                     int val = ConversionOperations.romanToInt(romanVal);
                    output = romanVal + " in Integer is: " + val;
                }

                resultArea.setText("Result:\n" + output);

            } catch (NumberFormatException ex) {
                resultArea.setText("Result:\nPlease enter a valid integer number.");
            } catch (Exception ex) {
                resultArea.setText("Result:\nAn error occurred during conversion.");
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}