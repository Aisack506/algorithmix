# AlgorithmiX

> A modern, responsive multi-tool desktop application built in Java Swing using a clean service-oriented architecture.

---

## Features

- **Arithmetic Calculator:** Handles basic operations (Addition, Subtraction, Multiplication, Division) with real-time computation.
- **Number Theory & Utilities:** Features advanced mathematical functions including Even/Odd validation, Factorial, Fibonacci sequences, and more.
- **Triangle Classifier:** Validates triangle side lengths and classifies triangles by sides (Equilateral, Isosceles, Scalene) and angles (Right, Acute, Obtuse) with visual feedback.
- **Roman Numeral & Number Converter:** Performs real-time bidirectional conversion between Integers (1–3999) and Roman numerals with strict validation.
- **Text Operations:** Includes string utilities like Palindrome verification and text analysis.
- **Algebra & Geometry:** Solves equations and handles geometric calculations.
- **Modern UI/UX:** Built with FlatLaf themes, custom components, responsive `GridBagLayout` layouts, and a clean card-based navigation sidebar.
- **Native Desktop Packaging:** Includes support for generating a fully self-contained Windows executable (`.exe`) via JDK's `jpackage`.
---

## Tech Stack & Architecture

* **Language:** Java 21
* **UI Framework:** Java Swing & AWT
* **Look & Feel:** [FlatLaf 3.7.2](https://www.formdev.com/flatlaf/)
* **Build System:** Apache Maven
* **Architecture:** Model-View-Controller (MVC) / Service-Oriented Logic
* **Deployment:** Executable JAR & `jpackage` Native App Image

---

## Project Structure

```text
src/
├── controller/     # Event handling and application flow
├── model/          # Core business logic & calculation services
│   ├── CalculatorOperations.java
│   ├── ConversionOperations.java
│   └── GeometryAndAlgebra.java
│   └── ...
├── resources/      # UI icons, themes, and application assets
├── view/           # Swing UI components & modular panels
│   ├── components/ # Reusable buttons, inputs, and cards
│   ├── panels/     # Individual module panels
│   └── MainWindow.java
└── Main.java       # Application entry point
```
---

## Getting Started

**Prerequisites**
* JDK 21 or higher installed on your system.
* Maven (optional, for CLI builds).

**Running from Source**

Clone the repository:
```text
git clone [https://github.com/Aisack506/algorithmix.git](https://github.com/Aisack506/algorithmix.git)
cd algorithmix
```

Run directly via Maven:
```text
mvn clean compile exec:java -Dexec.mainClass="Main"
```

Or run the compiled JAR file:
```text
java -jar out/artifacts/AlgorithmiX_jar/AlgorithmiX.jar
```

---

## Building a Standalone Native Executable (.exe)
AlgorithmiX can be packaged into a standalone Windows app bundled with its own embedded JRE (no Java pre-installation required for end users):

```text
jpackage --type app-image `
  --name AlgorithmiX `
  --input out/artifacts/AlgorithmiX_jar `
  --main-jar AlgorithmiX.jar `
  --main-class Main `
  --app-version 1.0
```
---

## Author
Kevin Díaz - Systems Engineering Student & Software Developer

---

## About The Project
This application was developed as a university coursework project for Systems Engineering. To implement the required features, I chose to design a responsive multi-tool desktop environment demonstrating clean code principles, Object-Oriented Programming (OOP), and a Service-Oriented MVC architecture in Java Swing.
