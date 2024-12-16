package ciphers;

//Split this Application Class into an Application and an Object

public class CipherApp {

    public static void main(String[] args) {
        System.out.println("*********************************************************\n\n");
        String plainText = "Happy Holidays 2022!";
        CaesarCipher caesar = new CaesarCipher(3);
        String cipherText = caesar.encode(plainText);
        String decodedText = caesar.decode(cipherText);
        System.out.println("PlainText =   " + plainText);
        System.out.println("CipherText =  " + cipherText);
        System.out.println("DecodedText = " + decodedText);

        System.out.println("*********************************************************\n\n");
        SubstitutionCipher substitution2 = new SubstitutionCipher("QWERTYUIOPASDFGHJKLZXCVBNM");
        SubstitutionCipher substitution = new SubstitutionCipher("12345678901234567890123456");
    }
}