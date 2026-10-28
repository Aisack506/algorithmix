package view.panels;
import model.ConversionOperations;
import javax.swing.*;
import java.awt.*;

public class ConversionsPanel extends JPanel {

    public ConversionsPanel(CardLayout cardLayout, JPanel mainContainer) {
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
        JLabel titleLabel = new JLabel("Unit Converter", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 32));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(titleLabel, gbc);

        // --- CATEGORY SELECTOR ---
        JLabel categoryLabel = new JLabel("Conversion Category:");
        categoryLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        gbc.gridy = 1; gbc.gridwidth = 1; // "gbc.gridy" to place this category under the previous (1), gbc.gridwidth to specify how many columns this category must have
        gbc.anchor = GridBagConstraints.EAST;
        contentPanel.add(categoryLabel, gbc);

        String[] categories = { "Length & Distance", "Temperature", "Weight & Mass" };
        JComboBox<String> categoryComboBox = new JComboBox<>(categories);
        categoryComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        categoryComboBox.setPreferredSize(new Dimension(300, 42));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        contentPanel.add(categoryComboBox, gbc);

        // --- DYNAMIC SUB-PANELS FOR EACH CATEGORY ---
        // Length Panel
        JPanel lengthPanel = new JPanel(new GridBagLayout());
        lengthPanel.setOpaque(false);
        GridBagConstraints lgbc = new GridBagConstraints();
        lgbc.insets = new Insets(8, 8, 8, 8);
        lgbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField lenInputField = new JTextField(12);
        lenInputField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        String[] lengthUnits = { "Meters to Feet", "Feet to Meters", "Kilometers to Miles", "Miles to Kilometers", "Centimeters to Inches", "Inches to Centimeters" };
        JComboBox<String> lenUnitBox = new JComboBox<>(lengthUnits);
        lenUnitBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        lgbc.gridx = 0; lgbc.gridy = 0; lengthPanel.add(new JLabel("Value:"), lgbc);
        lgbc.gridx = 1; lengthPanel.add(lenInputField, lgbc);
        lgbc.gridx = 0; lgbc.gridy = 1; lengthPanel.add(new JLabel("Direction:"), lgbc);
        lgbc.gridx = 1; lengthPanel.add(lenUnitBox, lgbc);

        // Temperature Panel
        JPanel tempPanel = new JPanel(new GridBagLayout());
        tempPanel.setOpaque(false);
        GridBagConstraints tgbc = new GridBagConstraints();
        tgbc.insets = new Insets(8, 8, 8, 8);
        tgbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField tempInputField = new JTextField(12);
        tempInputField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        String[] tempUnits = { "Celsius to Fahrenheit", "Fahrenheit to Celsius", "Celsius to Kelvin", "Kelvin to Celsius" };
        JComboBox<String> tempUnitBox = new JComboBox<>(tempUnits);
        tempUnitBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        tgbc.gridx = 0; tgbc.gridy = 0; tempPanel.add(new JLabel("Value:"), tgbc);
        tgbc.gridx = 1; tempPanel.add(tempInputField, tgbc);
        tgbc.gridx = 0; tgbc.gridy = 1; tempPanel.add(new JLabel("Direction:"), tgbc);
        tgbc.gridx = 1; tempPanel.add(tempUnitBox, tgbc);

        // Weight Panel
        JPanel weightPanel = new JPanel(new GridBagLayout());
        weightPanel.setOpaque(false);
        GridBagConstraints wgbc = new GridBagConstraints();
        wgbc.insets = new Insets(8, 8, 8, 8);
        wgbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField weightInputField = new JTextField(12);
        weightInputField.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        String[] weightUnits = { "Kilograms to Pounds", "Pounds to Kilograms", "Grams to Ounces", "Ounces to Grams" };
        JComboBox<String> weightUnitBox = new JComboBox<>(weightUnits);
        weightUnitBox.setFont(new Font("Segoe UI", Font.PLAIN, 16));

        wgbc.gridx = 0; wgbc.gridy = 0; weightPanel.add(new JLabel("Value:"), wgbc);
        wgbc.gridx = 1; weightPanel.add(weightInputField, wgbc);
        wgbc.gridx = 0; wgbc.gridy = 1; weightPanel.add(new JLabel("Direction:"), wgbc);
        wgbc.gridx = 1; weightPanel.add(weightUnitBox, wgbc);

        // --- WRAPPER FOR DYNAMIC PANELS (CardLayout) ---
        JPanel dynamicSubWrapper = new JPanel(new CardLayout());
        dynamicSubWrapper.setOpaque(false);
        dynamicSubWrapper.add(lengthPanel, "LENGTH");
        dynamicSubWrapper.add(tempPanel, "TEMPERATURE");
        dynamicSubWrapper.add(weightPanel, "WEIGHT");

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(dynamicSubWrapper, gbc);

        CardLayout subCardLayout = (CardLayout) dynamicSubWrapper.getLayout();
        categoryComboBox.addActionListener(e -> {
            int index = categoryComboBox.getSelectedIndex();
            if (index == 0) subCardLayout.show(dynamicSubWrapper, "LENGTH");
            else if (index == 1) subCardLayout.show(dynamicSubWrapper, "TEMPERATURE");
            else if (index == 2) subCardLayout.show(dynamicSubWrapper, "WEIGHT");
        });

        // --- ACTION BUTTON ---
        JButton convertButton = new JButton("Convert Unit");
        convertButton.setFont(new Font("Segoe UI", Font.BOLD, 18));
        convertButton.setPreferredSize(new Dimension(220, 48));
        gbc.gridy = 3;
        contentPanel.add(convertButton, gbc);

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
        scrollPane.setPreferredSize(new Dimension(600, 160));
        scrollPane.setMinimumSize(new Dimension(600, 160));

        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(scrollPane, gbc);

        // Conversion Logic
        convertButton.addActionListener(e -> {
            try {
                int catIndex = categoryComboBox.getSelectedIndex();
                double val;
                String output = "";

                if (catIndex == 0) { // Length
                    val = Double.parseDouble(lenInputField.getText().trim());
                    int unitIndex = lenUnitBox.getSelectedIndex();
                    switch (unitIndex) {
                        case 0: output = val + " Meters = " + ConversionOperations.metersToFeet(val) + " Feet"; break;
                        case 1: output = val + " Feet = " + ConversionOperations.feetToMeters(val) + " Meters"; break;
                        case 2: output = val + " Kilometers = " + ConversionOperations.kilometersToMiles(val) + " Miles"; break;
                        case 3: output = val + " Miles = " + ConversionOperations.milesToKilometers(val) + " Kilometers"; break;
                        case 4: output = val + " Centimeters = " + ConversionOperations.centimetersToInches(val) + " Inches"; break;
                        case 5: output = val + " Inches = " + ConversionOperations.inchesToCentimeters(val) + " Centimeters"; break;
                    }
                } else if (catIndex == 1) { // Temperature
                    val = Double.parseDouble(tempInputField.getText().trim());
                    int unitIndex = tempUnitBox.getSelectedIndex();
                    switch (unitIndex) {
                        case 0: output = val + " °C = " + ConversionOperations.celsiusToFahrenheit(val) + " °F"; break;
                        case 1: output = val + " °F = " + ConversionOperations.fahrenheitToCelsius(val) + " °C"; break;
                        case 2: output = val + " °C = " + ConversionOperations.celsiusToKelvin(val) + " K"; break;
                        case 3: output = val + " K = " + ConversionOperations.kelvinToCelsius(val) + " °C"; break;
                    }
                } else { // Weight
                    val = Double.parseDouble(weightInputField.getText().trim());
                    int unitIndex = weightUnitBox.getSelectedIndex();
                    switch (unitIndex) {
                        case 0: output = val + " Kilograms = " + ConversionOperations.kilogramsToPounds(val) + " Pounds"; break;
                        case 1: output = val + " Pounds = " + ConversionOperations.poundsToKilograms(val) + " Kilograms"; break;
                        case 2: output = val + " Grams = " + ConversionOperations.gramsToOunces(val) + " Ounces"; break;
                        case 3: output = val + " Ounces = " + ConversionOperations.ouncesToGrams(val) + " Grams"; break;
                    }
                }

                resultArea.setText("Result:\n" + output);

            } catch (NumberFormatException ex) {
                resultArea.setText("Result:\nPlease enter a valid numeric value.");
            } catch (Exception ex) {
                resultArea.setText("Result:\nAn error occurred during conversion.");
            }
        });

        add(contentPanel, BorderLayout.CENTER);
    }
}