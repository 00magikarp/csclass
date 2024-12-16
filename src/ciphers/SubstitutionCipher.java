package ciphers;

import java.util.Arrays;

public class SubstitutionCipher implements Cipherable {
	private String shuffled;
	private static final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	
	public SubstitutionCipher() {
		shuffled = alphabet;
	}
	
	public SubstitutionCipher(String s) {
		if (s.length() != 26) {
			throw new IllegalArgumentException("Illegally formatted key");
		}

		int count[] = new int[26];

		for (int i = 0; i < 26; i++) {
			if ((int) s.charAt(i) < 65 || (int) s.charAt(i) > 90) {
				throw new IllegalArgumentException("Illegally formatted key");
			}
			count[(int) s.charAt(i) - 65]++;
		}

		System.out.println(s);
		System.out.println(Arrays.toString(count));

		for (int i = 0; i < 26; i++) {
			if (count[i] == 0 || count[i] == 2) {
				throw new IllegalArgumentException("Illegally formatted key");
			}
		}

		shuffled = s;
	}
	
	public String encode(String m) {
		// TODO: not implemented
		/*
	    char[] old = m.toCharArray();
		char[] answer = m.toCharArray();

		for (int i = 0; i < m.length(); i++) {
			answer[i] = shuffled.charAt(alphabet.indexOf(old[i]));
		}
		 */
		return "";
	  }

	  public String decode(String s) {
	    // TODO: not implemented
		return "";
	  }
}
