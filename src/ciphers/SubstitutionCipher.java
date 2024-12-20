package ciphers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Class for encrypting and decrypting textual data with a substitution
 * cipher.
 * <p>
 * Can either be used with a random key or a key chosen by the user.
 * Keys are alphabetical where <code>key.length() = 26</code> and
 * contains each unique letter once, non-case sensitive.
 * <p>
 * This class contains an encode and decode method, which works as
 * the standard substitution cipher. For all values <code>s</code> and
 * {@link SubstitutionCipher} <code>S</code>, it should be that <code>s ==
 * S.decode(S.encode(s))</code> is <code>true</code> and if <code>s == t</code>
 * is true, then <code>S.encode(s) == S.encode(t)</code> is true.
 */
public class SubstitutionCipher implements Cipherable {
    private String shuffledUpper;
    private String shuffledLower;

    /**
     * Generate a new {@link SubstitutionCipher} Object with a random key.
     */
    public SubstitutionCipher() {
        String cur = "";

        while (cur.length() < 26) {
            char c = (char) ((int) (Math.random() * 26) + 65);
            if (cur.indexOf(c) == -1) {
                cur += c;
            }
        }

        shuffledUpper = cur;
        shuffledLower = cur.toLowerCase();
    }

    /**
     * Create a new {@link SubstitutionCipher} object with a key provided.
     * Key must be all alphabetical and have each letter appear exactly once,
     * for a length of 26 characters.
     * @param s key
     */
    public SubstitutionCipher(String s) {
        if (s.length() != 26) {
            throw new IllegalArgumentException("Illegally formatted key - improper length");
        }
        s = s.toUpperCase();

        int[] count = new int[26];

        for (int i = 0; i < 26; i++) {
            if (!Character.isLetter(s.charAt(i))) {
                throw new IllegalArgumentException("Illegally formatted key - illegal character");
            }
            count[(int) s.charAt(i) - 65]++;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] == 0 || count[i] == 2) {
                throw new IllegalArgumentException("Illegally formatted key - character appears too little/many times");
            }
        }

        shuffledUpper = s;
        shuffledLower = s.toLowerCase();
    }

    /**
     * Encodes an inputted ASCII string <code>m</code> with the substitution cipher.
     * If a character cannot be encrypted with substitution, it is left as the original.
     * @param m message to be encrypted
     * @return encrypted message
     */
    public String encode(String m) {
        char[] old = m.toCharArray();
        char[] answer = m.toCharArray();

        for (int i = 0; i < m.length(); i++) {
            if (!Character.isLetter(old[i])) {
                answer[i] = old[i];
                continue;
            }

            if (Character.isUpperCase(old[i])) {
                answer[i] = shuffledUpper.charAt(old[i] - 65);
            } else {
                answer[i] = shuffledLower.charAt(old[i] - 97);
            }
        }
        return new String(answer);
    }

    /**
     * Decodes an inputted ASCII string <code>m</code> with the substitution cipher.
     * @param c message to be decrypted
     * @return decrypted message
     */
    public String decode(String c) {
        char[] old = c.toCharArray();
        char[] answer = c.toCharArray();

        for (int i = 0; i < c.length(); i++) {
            if (!Character.isLetter(old[i])) {
                answer[i] = old[i];
                continue;
            }

            if (Character.isUpperCase(old[i])) {
                answer[i] = (char) (shuffledUpper.indexOf(old[i]) + 65);
            } else {
                answer[i] = (char) (shuffledLower.indexOf(old[i]) + 97);
            }
        }
        return new String(answer);
    }

    /**
     * @return key
     */
    public String getShuffled() {
        return shuffledUpper;
    }
}
