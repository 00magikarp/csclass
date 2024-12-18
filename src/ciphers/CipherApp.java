package ciphers;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CipherApp {
    public static void main(String[] args) {
        int cipherChoice = choice("Choose encryption method: [1] Caesar, [2] Substitution, [3] XOR ", 3);
        int direction = choice("[1] Encrypting / [2] Decrypting ", 2);
        String cipherKey = getString("Input a key, or a period [.] for a random key.");

        if (direction == 2 && cipherKey.equals(".")) {
            throw new IllegalArgumentException("Cannot decrypt with random key");
        }

        String text = getString(String.format("Input code to %s: ", direction == 1 ? "encode" : "decode"));

        if (cipherChoice == 1) {
            CaesarCipher c;
            if (cipherKey.equals(".")) {
                c = new CaesarCipher();
                System.out.println("Random key: " + c.getShift());
            } else {
                c = new CaesarCipher(Integer.valueOf(cipherKey));
            }

            if (direction == 1) {
                System.out.println("Encoded text: " + c.encode(text));
            } else {
                System.out.println("Decoded text: " + c.decode(text));
            }
        } else if (cipherChoice == 2) {
            SubstitutionCipher c;
            if (cipherKey.equals(".")) {
                c = new SubstitutionCipher();
                System.out.println("Random key: " + c.getShuffled());
            } else {
                c = new SubstitutionCipher(cipherKey);
            }

            if (direction == 1) {
                System.out.println("Encoded text: " + c.encode(text));
            } else {
                System.out.println("Decoded text: " + c.decode(text));
            }
        } else {
            XORCipher c;
            if (cipherKey.equals(".")) {
                c = new XORCipher();
                System.out.println("Random key: " + c.getKey());
            } else {
                c = new XORCipher(cipherKey);
            }

            if (direction == 1) {
                System.out.println("Encoded text: " + c.encode(text));
            } else {
                System.out.println("Decoded text: " + c.decode(text));
            }
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
        int x = 0;

        while (!(1 <= x && x <= 3)) {
            try {
                x = kb.nextInt();
                if (!(1 <= x && x <= numChoices)) {
                    System.out.println("ERROR: input outside of expected options");
                }
            } catch (InputMismatchException e) {
                System.out.println("ERROR: input not proper");
            }
        }
        return x;
    }
}