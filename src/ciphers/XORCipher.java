package ciphers;

public class XORCipher implements Cipherable {
    private String key;

    public XORCipher() {
        key = "1";
    }
    public XORCipher(String k) {
        if (k == null || k.length() == 0) {
            throw new IllegalArgumentException("Illegal key");
        }
        for (int i = 0; i < k.length(); i++) {
            if (k.charAt(i) != '0' && k.charAt(i) != '1') {
                throw new IllegalArgumentException("Illegal key");
            }
        }

        key = k;
    }

    public String encode(String m) {
        String mAsBinaryString = "";
        String answer = "";

        for (char c : m.toCharArray()) {
            mAsBinaryString += get8BitBinString(c);
        }

        for (int i = 0; i < mAsBinaryString.length(); i++) {
            answer += boolToChar(charToBool(mAsBinaryString.charAt(i)) ^ charToBool(key.charAt(i % key.length())));
        }
        return answer;
    }

    public String decode(String m) {
        String answerAsBin = "";
        String answer = "";

        for (int i = 0; i < m.length(); i++) {
            answerAsBin += boolToChar(charToBool(m.charAt(i)) ^ charToBool(key.charAt(i % key.length())));
        }

        for (int i = 0; i < answerAsBin.length(); i += 8) {
            answer += (char) Integer.parseInt(
                    answerAsBin.substring(i, i + 8),
                    2);
        }
        return answer;
    }
    
    private boolean charToBool(char c) {
        return c == '1';
    }

    private char boolToChar(boolean b) {
        return b ? '1' : '0';
    }

    private String get8BitBinString(char c) {
        String b = Integer.toBinaryString((int) c);
        return "0".repeat(8 - b.length()) + b;
    }

    public String getKey() {
        return key;
    }
}
