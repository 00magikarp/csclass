package ciphers;

public class SubstitutionCipher implements Cipherable {
    private String shuffledUpper;
    private String shuffledLower;
    private static final String ALPHABET_UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHABET_LOWER = "abcdefghijklmnopqrstuvwxyz";

    public SubstitutionCipher() {
        shuffledUpper = ALPHABET_UPPER;
        shuffledLower = ALPHABET_LOWER;
    }

    public SubstitutionCipher(String s) {
        if (s.length() != 26) {
            throw new IllegalArgumentException("Illegally formatted key - improper length");
        }

        int count[] = new int[26];

        for (int i = 0; i < 26; i++) {
            if ((int) s.charAt(i) < 65 || (int) s.charAt(i) > 90) {
                throw new IllegalArgumentException("Illegally formatted key - illegal character");
            }
            count[(int) s.charAt(i) - 65]++;
        }

        for (int i = 0; i < 26; i++) {
            if (count[i] == 0 || count[i] == 2) {
                throw new IllegalArgumentException("Illegally formatted key - character appears too little/many times");
            }
        }

        shuffledUpper = s.toUpperCase();
        shuffledLower = s.toLowerCase();
    }

    public String encode(String m) {
        // TODO: not implemented
        char[] old = m.toCharArray();
        char[] answer = m.toCharArray();
        int curCharAsInt;

        for (int i = 0; i < m.length(); i++) {
            curCharAsInt = (int) old[i];
            if (curCharAsInt < 65 ||
                    curCharAsInt >= 91 && curCharAsInt <= 96 ||
                    curCharAsInt > 122) {
                throw new IllegalArgumentException("Cannot decipher argument");
            }

            if (ALPHABET_UPPER.indexOf(old[i]) != -1) {
                answer[i] = shuffledUpper.charAt(ALPHABET_UPPER.indexOf(old[i]));
            } else {
                answer[i] = shuffledLower.charAt(ALPHABET_LOWER.indexOf(old[i]));
            }
        }
        return new String(answer);
    }

    public String decode(String c) {
        char old[] = c.toCharArray();
        char answer[] = c.toCharArray();
        int curCharAsInt;

        for (int i = 0; i < c.length(); i++) {
            curCharAsInt = (int) old[i];
            if (curCharAsInt < 65 ||
                    curCharAsInt >= 91 && curCharAsInt <= 96 ||
                    curCharAsInt > 122) {
                throw new IllegalArgumentException("Cannot decipher argument");
            }

            if (ALPHABET_UPPER.indexOf(old[i]) != -1) {
                answer[i] = ALPHABET_UPPER.charAt(shuffledUpper.indexOf(old[i]));
            } else {
                answer[i] = ALPHABET_LOWER.charAt(shuffledLower.indexOf(old[i]));
            }
        }
        return new String(answer);
    }

    public String getShuffled() {
        return shuffledUpper;
    }
}
