package ciphers;

public class CaesarCipher implements Cipherable {
    private int shift;

    public CaesarCipher() {
        shift = (int) (Math.random() * 26);
    }

    public CaesarCipher(int s) {
        shift = s;
    }

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

    public String decode(String in) {
        shift = 26 - shift;
        String answer = encode(in);
        shift += 26;
        return answer;
    }

    public int getShift() {
        return shift;
    }
}
