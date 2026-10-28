package model;
import java.util.ArrayList;
import java.util.List;

public class CalculatorOperations {
    public static void main(String[] args) {
        System.out.println(calculator("2x2/2+2x5-25-10/10x2"));
    }

    public static double calculator(String expression) {
        List<String> tokenization = tokenize(expression);
        List<String> postfix = shuntingYard(tokenization);
        return evaluatePostfix(postfix);
    }

    private static List<String> tokenize(String expr) {
        // Splits the raw expression string into an ordered list of individual numeric and operator tokens.
        List<String> operators = new ArrayList<>(List.of("x", "/", "+", "-"));
        List<String> tokenization = new ArrayList<String>();
        for (int i = 0; i < expr.length(); i++ ) {
            if (operators.contains(String.valueOf(expr.charAt(i)))) {
                tokenization.add(String.valueOf(expr.charAt(i)));
                continue;
            }

            StringBuilder number = new StringBuilder();
            for (int k = i; k < expr.length(); k++) {
                char digit = expr.charAt(k);
                if (operators.contains(String.valueOf(digit))) {
                    break;
                }
                number.append(digit);
            }
            tokenization.add(number.toString());
            i += number.length() - 1;

        }
        return tokenization;
    }

    private static List<String> shuntingYard(List<String> tokens) {
        // Converts the tokenized infix expression into postfix notation (RPN) using an operator stack to enforce precedence.
        ArrayList<String> highPriority = new ArrayList<>(List.of("x","/"));
        ArrayList<String> lowPriority = new ArrayList<>(List.of("+","-"));

        ArrayList<String> output = new ArrayList<String>();
        ArrayList<String> stack = new ArrayList<String>();
        for (String digit : tokens) {
            if (!highPriority.contains(digit) && !lowPriority.contains(digit)) {
                output.add(digit);
                continue;
            }

            if (stack.isEmpty()) {
                stack.add(digit);
                continue;
            }

            for (int k = stack.size() - 1; k >= 0; k--) {
                if (lowPriority.contains(digit) && highPriority.contains(stack.get(k))) {
                    String pop = stack.remove(k);
                    output.add(pop);
                } else if ((lowPriority.contains(digit) && lowPriority.contains(stack.get(k))) || (highPriority.contains(digit) && highPriority.contains(stack.get(k)))) {
                    String pop = stack.remove(k);
                    output.add(pop);
                }
            }
            stack.add(digit);
        }

        while (!stack.isEmpty()) {
            String remaining = stack.removeLast();
            output.add(remaining);
        }

        return output;
    }

    private static double evaluatePostfix(List<String> postfix) {
        // Evaluates the postfix expression sequentially using a value stack to compute the final result.

        List<String> operators = new ArrayList<>(List.of("x", "/", "+", "-"));
        ArrayList<Double> output = new ArrayList<>();
        for (String digit : postfix) {
            if (!operators.contains(digit)) {
                output.add(Double.valueOf(digit));
                continue;
            }

            double firstDigit = output.get(output.size() - 2);
            double secondDigit = output.getLast();
            output.remove(output.size() - 2);
            output.removeLast();

            switch (digit) {
                case "x" -> output.add(firstDigit * secondDigit);
                case "/" -> {
                    if (secondDigit == 0) throw new ArithmeticException("Division by zero");
                    output.add(firstDigit / secondDigit);
                }
                case "+" -> output.add(firstDigit + secondDigit);
                default -> output.add(firstDigit - secondDigit);
            }
        }

        return output.getFirst();
    }
}
