import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * <p>Program Description: Quiz the user on converting to and from two's complement.</p>
 * <p>Date: 10/11/24</p>
 * Testing Scenarios:<ul>
 *  <li>Conversion:
 *      <ol><li>4 -> 00000100</li>
 *      <li>-20 -> 11101100</li>
 *      <li>00101111 -> 47</li>
 *      <li>01111111 -> 127</li>
 *      <li>-128 -> 10000000</li></ol></li>
 *  <li>All restricted input validation</li>
 *  <li>Answer X corrections and get score of X</li></ul>
 */
public class TwosComplementQuizzingProgram {
    /**
     * Main quiz method.
     * @param args args
     */
    public static void main(String[] args) {
        try (Scanner kb = new Scanner(System.in)) {
            while (true) {
                showDirections();
                int directionChoice = getInt(kb, "Input mode: ", 0, 1);
                int score = 0;

                for (int i = 0; i < 3; i++) {
                    int current = getRandIntRange(-128, 127);
                    if (directionChoice == 0) {
                        score += checkStringAnswer(kb, "Convert " + current + " to Two's Complement: ", toTwosComplement(current)) ? 1 : 0;
                    } else {
                        score += checkIntAnswer(kb, "Convert " + toTwosComplement(current) + " to decimal: ", current) ? 1 : 0;
                    }
                }

                System.out.println("Hooray! Final score: " + score);
                int continuePlaying = getInt(kb, "Nice job! Would you like to play again? (0 - No, 1 - Yes) ", 0, 1);
                if (continuePlaying == 0) break;
            }
        }
    }

    /**
     * Method to show the directions of the quiz.
     */
    public static void showDirections() {
        System.out.println("""
                Choose a direction of conversion:
                0 - Decimal -> Complement
                1 - Complement -> Decimal
                """);
    }

    /**
     * Generates a random {@code int} in the range of lower to upper, inclusive on both ends.
     * @param lower The lower bound of the random number.
     * @param upper The higher bound of the random number.
     * @return The random number generated.
     */
    public static int getRandIntRange(int lower, int upper) {
        return (int) (Math.random() * (upper - lower)) + lower;
    }

    /**
     * Takes an input {@code String} from the user using the inputted scanner, and then compares it to the expected answer.
     * Meant for checking if a user inputs a "correct" answer.
     * @param scanner The current scanner to be in use.
     * @param prompt Prompt for the user preceding text input.
     * @param expectedAnswer The expected ("correct") input.
     * @return True if the user inputted the expected ("correct") answer.
     */
    public static boolean checkStringAnswer(Scanner scanner, String prompt, String expectedAnswer) {
        String str;

        System.out.println(prompt);
        str = scanner.next();
        if (str.equals(expectedAnswer)) {
            System.out.println("Correct!");
            return true;
        }
        System.out.println("Wrong!");
        return false;
    }

    /**
     * Takes an input {@code int} from the user using the inputted scanner, and then compares it to the expected answer.
     * Meant for checking if a user inputs a "correct" answer.
     * @param scanner The current scanner to be in use.
     * @param prompt Prompt for the user preceding text input.
     * @param expectedAnswer The expected ("correct") input.
     * @return True if the user inputted the expected ("correct") answer.
     */
    public static boolean checkIntAnswer(Scanner scanner, String prompt, int expectedAnswer) {
        int answer = getInt(scanner, prompt);
        if (answer == expectedAnswer) {
            System.out.println("Correct!");
            return true;
        }
        System.out.println("Wrong!");
        return false;
    }

    /**
     * Get an {@code int} from the user. Input is protected such that when the user does not input a proper integer,
     * it reprompts the user.
     * @param scanner The current scanner to be in use.
     * @param prompt Prompt for the user preceding text input.
     * @return The integer inputted by the user.
     */
    public static int getInt(Scanner scanner, String prompt) {
        return getInt(scanner, prompt, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    /**
     * Get an {@code int} from the user. Input is protected such that when the user does not input a proper integer,
     * it reprompts the user. If the user doesn't input a number between the bounds, it reprompts the user.
     * @param scanner The current scanner to be in use.
     * @param prompt Prompt for the user preceding text input.
     * @param lowerBound The lowest number allowed to be inputted by the user, inclusive.
     * @param upperBound The highest number allowed to be inputted by the user, inclusive.
     * @return The integer inputted by the user.
     */
    public static int getInt(Scanner scanner, String prompt, int lowerBound, int upperBound) {
        int i;

        while (true) {
            try {
                System.out.println(prompt);
                i = scanner.nextInt();
                if (lowerBound <= i && i <= upperBound) {
                    return i;
                }
                System.out.println("Wrong/invalid input; try again!");
            } catch (InputMismatchException e) {
                System.out.println("Not an integer; please try again!");
                scanner.nextLine();
            }
        }
    }

    /**
     * Convert an {@code int} to its binary representation, using Two's Complement. Converts it to an 8-bit string.
     * Only takes numbers from -128 to 127, inclusive (byte worth of integer).
     * @param n The number to convert to a binary string.
     * @return The number, in binary, as an 8-bit string.
     */
    public static String toTwosComplement(int n) {
        String nAsBinString = Integer.toBinaryString(n);
        if (Math.signum(n) == 1.0) {
            return "0".repeat(8 - nAsBinString.length()) + nAsBinString;
        } else {
            return Integer.toBinaryString(n).substring(nAsBinString.length() - 8);
        }
    }
}