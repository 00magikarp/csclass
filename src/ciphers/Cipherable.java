package ciphers;

public interface Cipherable {
    public String encode(String m);
    public String decode(String c);
}
