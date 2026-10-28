package view.panels;

import view.components.MenuCard;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenuPanel extends JPanel {
    private float titleAnimProgress = 0.0f;
    private Timer titleTimer;
    private boolean isTitleHovered = false;

    public MainMenuPanel(CardLayout cardLayout, JPanel parentContainer) {
        // BorderLayout for the main panel structure
        setLayout(new BorderLayout(10, 20));
        setBorder(BorderFactory.createEmptyBorder(25, 30, 30, 30));

        // --- STYLIZED AND ANIMATED HEADER ---
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(new BoxLayout(headerPanel, BoxLayout.Y_AXIS));
        headerPanel.setOpaque(false);
        headerPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        headerPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Main title
        JLabel titleLabel = new JLabel("AlgorithmiX", SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2d = (Graphics2D) g.create();
                g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

                int r = (int) (220 + (255 - 220) * titleAnimProgress);
                int gCol = (int) (225 + (255 - 225) * titleAnimProgress);
                int b = (int) (240 + (255 - 240) * titleAnimProgress);
                setForeground(new Color(r, gCol, b));

                g2d.dispose();
                super.paintComponent(g);
            }
        };
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 36));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Subtitle
        JLabel subtitleLabel = new JLabel("Multi-Tool Computational Suite", SwingConstants.CENTER);
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(140, 150, 175));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(titleLabel);
        headerPanel.add(Box.createRigidArea(new Dimension(0, 4)));
        headerPanel.add(subtitleLabel);

        add(headerPanel, BorderLayout.NORTH);

        // --- TIMER FOR TITLE ANIMATION ---
        titleTimer = new Timer(15, e -> {
            boolean needsRepaint = false;
            if (isTitleHovered && titleAnimProgress < 1.0f) {
                titleAnimProgress += 0.15f;
                if (titleAnimProgress > 1.0f) titleAnimProgress = 1.0f;
                needsRepaint = true;
            } else if (!isTitleHovered && titleAnimProgress > 0.0f) {
                titleAnimProgress -= 0.15f;
                if (titleAnimProgress < 0.0f) titleAnimProgress = 0.0f;
                needsRepaint = true;
            }

            if (needsRepaint) {
                titleLabel.repaint();
            } else {
                titleTimer.stop();
            }
        });

        // Hover events for the header
        headerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent evt) {
                isTitleHovered = true;
                subtitleLabel.setForeground(new Color(180, 200, 240)); // Subtitle also glows slightly
                titleTimer.restart();
            }

            @Override
            public void mouseExited(MouseEvent evt) {
                isTitleHovered = false;
                subtitleLabel.setForeground(new Color(140, 150, 175));
                titleTimer.restart();
            }
        });

        // Grid panel for the feature cards
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 5, 20));
        gridPanel.setOpaque(false);

        // Calculator (Groups Addition, Subtraction, Multiplication, Division)
        gridPanel.add(new MenuCard("Calculator", "/resources/calculator.png", e -> {
            cardLayout.show(parentContainer, "CALCULATOR_PANEL");
        }));

        // Even / Odd
        gridPanel.add(new MenuCard("Even / Odd", "/resources/even_odd.png", e -> {
            cardLayout.show(parentContainer, "EVEN_ODD_PANEL");
        }));

        // Factorial
        gridPanel.add(new MenuCard("Factorial", "/resources/factorial.png", e -> {
            cardLayout.show(parentContainer, "FACTORIAL_PANEL");
        }));

        // Fibonacci
        gridPanel.add(new MenuCard("Fibonacci", "/resources/fibonacci.png", e -> {
            cardLayout.show(parentContainer, "FIBONACCI_PANEL");
        }));

        // Leap Year
        gridPanel.add(new MenuCard("Leap Year", "/resources/leap_years.png", e -> {
            cardLayout.show(parentContainer, "LEAP_YEAR_PANEL");
        }));

        // Prime Number
        gridPanel.add(new MenuCard("Prime Number", "/resources/prime_numbers.png", e -> {
            cardLayout.show(parentContainer, "PRIME_PANEL");
        }));

        // Conversions
        gridPanel.add(new MenuCard("Conversions", "/resources/conversions.png", e -> {
            cardLayout.show(parentContainer, "CONVERSIONS_PANEL");
        }));

        // Roman Numerals
        gridPanel.add(new MenuCard("Roman Numerals", "/resources/roman.png", e -> {
            cardLayout.show(parentContainer, "ROMAN_PANEL");
        }));

        // Capicúa
        gridPanel.add(new MenuCard("Capicúa", "/resources/capicua.png", e -> {
            cardLayout.show(parentContainer, "CAPICUA_PANEL");
        }));

        // Palindrome Text
        gridPanel.add(new MenuCard("Palindrome", "/resources/palindrome.png", e -> {
            cardLayout.show(parentContainer, "PALINDROME_PANEL");
        }));

        // Equations
        gridPanel.add(new MenuCard("Equations", "/resources/equations.png", e -> {
            cardLayout.show(parentContainer, "EQUATIONS_PANEL");
        }));

        // Triangle Types
        gridPanel.add(new MenuCard("Triangles", "/resources/triangules.png", e -> {
            cardLayout.show(parentContainer, "TRIANGLE_PANEL");
        }));

        // Special 1978 Task
        gridPanel.add(new MenuCard("1978", "/resources/1978.png", e -> {
            cardLayout.show(parentContainer, "SPECIAL_1978_PANEL");
        }));

        // Add the grid panel to the center of the main layout
        add(gridPanel, BorderLayout.CENTER);
    }
}