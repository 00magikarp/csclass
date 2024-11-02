import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Write a program that acts as a 5 function (+ - * / and %) calulator.
 */

public class FiveCalculator {
    public static void main(String[] args) {
        String[] options = {"+", "-", "*", "/", "%"};
        Scanner kb = new Scanner(System.in);

        // main calc loop
        while (true) {
            double x = getDouble(kb, "Operand 1 (double): ");
            OPERATION operation = getChoice(kb, "Operation (" + Arrays.toString(options) + "): ", options);
            double y = getDouble(kb, "Operand 2 (double): ");

            if (y == 0 &&
                    (operation == OPERATION.DIVIDE || operation == OPERATION.MODULO)) {
                System.out.println("ERROR - Cannot DIVIDE by 0");
                continue;
            }

            switch (operation) {
                case ADD -> System.out.println(x + " + " + y + " = " + (x + y));
                case SUBTRACT -> System.out.println(x + " - " + y + " = " + (x - y));
                case MULTIPLY -> System.out.println(x + " * " + y + " = " + (x * y));
                case DIVIDE -> System.out.println(x + " / " + y + " = " + (x / y));
                case MODULO -> System.out.println(x + " % " + y + " = " + (x % y));
            }

        }
    }

    public static double getDouble(Scanner kb, String prompt) {
        while (true) {
            try {
                System.out.println(prompt);
                return kb.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("ERROR - Invalid operand input");
                kb.next(); // clear current input
            }
        }
    }

    public static OPERATION getChoice(Scanner kb, String prompt, String[] options) {
        String choice;

        do {
            System.out.println(prompt);
            choice = kb.next();
        } while (!Arrays.asList(options).contains(choice));

        return switch (choice) {
            case "+" -> OPERATION.ADD;
            case "-" -> OPERATION.SUBTRACT;
            case "*" -> OPERATION.MULTIPLY;
            case "/" -> OPERATION.DIVIDE;
            case "%" -> OPERATION.MODULO;
            default -> OPERATION.ADD; // shouldn't happen
        };
    }

    public enum OPERATION {
        ADD, SUBTRACT, MULTIPLY, DIVIDE, MODULO
    }
}
