package ciphers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherApp {
    public static void main(String[] args) {
        System.out.println("*********************************************************");
        CaesarCipher caesar = new CaesarCipher(3);
        System.out.println(caesar.encode("Happy Holidays 2022!"));
        System.out.println(caesar.decode(caesar.encode("Happy Holidays 2022!")));

        System.out.println("*********************************************************");
        SubstitutionCipher substitution = new SubstitutionCipher("CDEFGHIJKLMNOPQRSTUVWXYZAB");
        System.out.println(substitution.encode("abcDEFghiXYZ"));
        System.out.println(substitution.decode(substitution.encode("abcDEFghiXYZ")));

        System.out.println("*********************************************************");
        XORCipher xor = new XORCipher("101");
        System.out.println(xor.encode("abc")); // 11010111 10111001 00001110
        System.out.println(xor.decode(xor.encode("abc")));

        int cipherChoice = choice("Choose encryption method: [1] Caesar, [2] Substitution, [3] XOR ", 3);
        int direction = choice("[1] Encrypting / [2] Decrypting ", 2);
        String cipherKey = getString("Input a key, or nothing for a random key.");

        if (direction == 2 && cipherKey.isEmpty()) {
            throw new IllegalArgumentException("Cannot decrypt with random key");
        }
    }

    private static String getString(String prompt) {
        System.out.println(prompt);
        Scanner kb = new Scanner(System.in);
        return kb.next();
    }

    private static int choice(String prompt, int numChoices) {
        System.out.println(prompt);
        Scanner kb = new Scanner(System.in);
        int x;

        try {
            x = kb.nextInt();
            if (1 <= x && x <= numChoices) {
                return x;
            } else {
                System.out.println("ERROR: defaulting to option 1");
                return 1;
            }
        } catch (InputMismatchException e) {
            System.out.println("ERROR: defaulting to option 1");
            return 1;
        }
    }
}