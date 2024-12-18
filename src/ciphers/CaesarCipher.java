package ciphers;

/**
 * Class for encrypting and decrypting textual data with a Caesar
 * cipher.
 * <p>
 * Can either be used with a random shift or a shift chosen by the user.
 * Shift is meant to be inputted as <code>0 <= shift < 26</code>, however
 * this is taken care of by the class itself if a value outisde of the
 * accepted range is inputted.
 * <p>
 * This class contains an encode and decode method, which works as
 * the standard Caesar cipher. For all values <code>s</code> and
 * {@link CaesarCipher} <code>C</code>, it should be that <code>s ==
 * C.decode(C.encode(s))</code> is <code>true</code> and if <code>s == t</code>
 * is true, then <code>C.encode(s) == C.encode(t)</code> is true.
 */
public class CaesarCipher implements Cipherable {
    private int shift;

    /**
     * Create a new {@link CaesarCipher} object with a random shift.
     */
    public CaesarCipher() {
        shift = (int) (Math.random() * 26);
    }

    /**
     * Create a new {@link CaesarCipher} object with shift <code>s</code>.
     * @param s shift
     */
    public CaesarCipher(int s) {
        shift = s % 26;
    }

    /**
     * Encodes an inputted ASCII string <code>in</code> with the Caesar cipher.
     * If a character cannot be encrypted with substitution, it is left as the original.
     * @param in message to be encrypted
     * @return encrypted message
     */
    public String encode(String in) {
        String upper = in.toUpperCase();

        String answer = "";
        for (int i = 0; i < in.length(); i++) {
            if (Character.isLetter(upper.charAt(i))) {
                char ch = (char) (upper.charAt(i) + shift);
                if (ch > 'Z') {
                    ch = (char) (ch - 26);
                }
                if (Character.isLowerCase(in.charAt(i))) {
                    ch = Character.toLowerCase(ch);
                }
                answer += ch;
            } else {
                answer += upper.charAt(i);
            }
        }
        return answer;
    }

    /**
     * Decodes an inputted encrypted ASCII string <code>in</code> with the Caesar cipher.
     * @param in message to be decrypted
     * @return decrypted message
     */
    public String decode(String in) {
        shift = 26 - shift;
        String answer = encode(in);
        shift += 26;
        return answer;
    }

    /**
     * @return shift
     */
    public int getShift() {
        return shift;
    }
}
