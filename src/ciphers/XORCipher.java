package ciphers;

/**
 * Class for encrypting and decrypting textual data with a bit-by-bit
 * XOR cipher.
 * <p>
 * Can either be used with a random key or a key chosen by the user.
 * Keys are binary where <code>key.length() > 0</code>.
 * <p>
 * This class contains an encode and decode method, which works as
 * the standard XOR cipher. For all values <code>s</code> and {@link XORCipher}
 * <code>X</code>, it should be that <code>s == X.decode(X.encode(s))</code>
 * is <code>true</code> and if <code>s == t</code> is true, then
 * <code>X.encode(s) == X.encode(t)</code> is true.
 */
public class XORCipher implements Cipherable {
    private String key;

    /**
     * Creates a new {@link XORCipher} object with a random key.
     * The key is random lengt from 5 to 24 and is binary.
     */
    public XORCipher() {
        char[] possible = {'0', '1'};
        key = "";
        for (int i = 0; i < (int) (Math.random() * 20) + 5; i++) {
            key += possible[(int) (Math.random() * 2)];
        }
    }

    /**
     * Creates a new {@link XORCipher} object with key <code>k</code>.
     * @param k key. Must be a binary String of length > 0.
     */
    public XORCipher(String k) {
        if (k == null || k.isEmpty()) {
            throw new IllegalArgumentException("Illegal key");
        }
        for (int i = 0; i < k.length(); i++) {
            if (k.charAt(i) != '0' && k.charAt(i) != '1') {
                throw new IllegalArgumentException("Illegal key");
            }
        }

        key = k;
    }

    /**
     * Encodes a given string using a bit-by-bit XOR cipher.
     * @param m message, in ASCII
     * @return the encrypted message, in hexadecimal
     */
    public String encode(String m) {
        String mAsBinaryString = "";
        String answerAsBin = "";
        String answer = "";

        for (char c : m.toCharArray()) {
            mAsBinaryString += get8BitBinString(c);
        }

        for (int i = 0; i < mAsBinaryString.length(); i++) {
            answerAsBin += String.valueOf(mAsBinaryString.charAt(i) ^ key.charAt(i % key.length()));
            if ((i + 1) % 4 == 0) {
                // every 4 bits, convert to hex
                answer += Integer.toString(Integer.parseInt(answerAsBin.substring(i - 3, i + 1), 2), 16);
            }
        }
        return answer;
    }

    /**
     * Decodes a given string using a bit-by-bit XOR cipher.
     * @param m message, in hexademical
     * @return the decrypted message, in ASCII
     */
    public String decode(String m) {
        String old = "";

        for (int i = 0; i < m.length(); i++) {
            old += get4BitBinString(Integer.parseInt(Character.toString(m.charAt(i)), 16));
        }
        String answerAsBin = "";
        String answer = "";

        for (int i = 0; i < old.length(); i++) {
            answerAsBin += String.valueOf(old.charAt(i) ^ key.charAt(i % key.length()));
            if ((i + 1) % 8 == 0) {
                // every 8 bits, convert to ASCII
                answer += (char) Integer.parseInt(answerAsBin.substring(i - 7, i + 1), 2);
            }
        }

        return answer;
    }

    /**
     * Get the proper 4 bit binary string for integer argument. Used for
     * conversions to/from hexadecimal.
     * @param n input
     * @return 4-bit binary string
     */
    private String get4BitBinString(int n) {
        String b = Integer.toBinaryString(n);
        return "0".repeat(4 - b.length()) + b;
    }

    /**
     * Get the proper 8 bit binary string for character argument. Used for
     * conversions to/from binary.
     * @param c input
     * @return 8-bit binary string
     */
    private String get8BitBinString(char c) {
        String b = Integer.toBinaryString((int) c);
        return "0".repeat(8 - b.length()) + b;
    }

    /**
     * @return key
     */
    public String getKey() {
        return key;
    }
}
