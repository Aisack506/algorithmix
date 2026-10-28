package view;
import view.panels.*;
import javax.swing.*;
import java.awt.*;

// "extends Jframe" makes MainWindow a native desktop window
public class MainWindow extends JFrame {

    public MainWindow() {
        // Windows setup construction
        setTitle("Multi-Tool Application");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // A layout manager in Java Swing decides how components inside a panel are arranged
        //  CardLayout stacks multiple full-size panels on top of each other in the exact same spot
        //  Forces only one panel to be painted/displayed at a time, keeping the rest invisible in the background.
        CardLayout cardLayout = new CardLayout();
        // Create a standard panel, but we're passing it cardLayout as its rulebook
        JPanel mainContainer = new JPanel(cardLayout);

        // Instantiate all your feature panels, passing the card layout and container
        MainMenuPanel mainMenuPanel = new MainMenuPanel(cardLayout, mainContainer);
        CalculatorPanel calculatorPanel = new CalculatorPanel(cardLayout, mainContainer);
        EvenOddPanel evenOddPanel = new EvenOddPanel(cardLayout, mainContainer);
        FactorialPanel factorialPanel = new FactorialPanel(cardLayout, mainContainer);
        FibonacciPanel fibonacciPanel = new FibonacciPanel(cardLayout, mainContainer);
        LeapYearPanel leapYearPanel = new LeapYearPanel(cardLayout, mainContainer);
        PrimePanel primePanel = new PrimePanel(cardLayout, mainContainer);
        CapicuaPanel capicuaPanel = new CapicuaPanel(cardLayout, mainContainer);
        Special1978Panel special1978Panel = new Special1978Panel(cardLayout, mainContainer);
        EquationsPanel equationsPanel = new EquationsPanel(cardLayout, mainContainer);
        PalindromePanel palindromePanel = new PalindromePanel(cardLayout, mainContainer);
        ConversionsPanel conversionsPanel = new ConversionsPanel(cardLayout, mainContainer);
        TrianglePanel trianglePanel = new TrianglePanel(cardLayout, mainContainer);
        RomanPanel romanPanel = new RomanPanel(cardLayout, mainContainer);

        // Add panels to the CardLayout container with unique string keys
        mainContainer.add(mainMenuPanel, "MAIN_MENU");
        mainContainer.add(calculatorPanel, "CALCULATOR_PANEL");
        mainContainer.add(evenOddPanel, "EVEN_ODD_PANEL");
        mainContainer.add(factorialPanel, "FACTORIAL_PANEL");
        mainContainer.add(fibonacciPanel, "FIBONACCI_PANEL");
        mainContainer.add(leapYearPanel, "LEAP_YEAR_PANEL");
        mainContainer.add(primePanel, "PRIME_PANEL");
        mainContainer.add(capicuaPanel, "CAPICUA_PANEL");
        mainContainer.add(special1978Panel, "SPECIAL_1978_PANEL");
        mainContainer.add(equationsPanel, "EQUATIONS_PANEL");
        mainContainer.add(palindromePanel, "PALINDROME_PANEL");
        mainContainer.add(conversionsPanel, "CONVERSIONS_PANEL");
        mainContainer.add(trianglePanel, "TRIANGLE_PANEL");
        mainContainer.add(romanPanel, "ROMAN_PANEL");


        // Add the container to the frame (MainWindow) so it actually appears on our monitor screen
        add(mainContainer);

        // Start by showing the main menu
        cardLayout.show(mainContainer, "MAIN_MENU");
    }
}