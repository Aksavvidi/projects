package testbed.review;

public class Crypto {

    public static void main(String[] args) {
        String s = "abc";
        String s1 = cipher(s);
        System.out.println(s1);

        String s2 = decipher(s1);
        System.out.println(s2);
    }

    public static String cipher(String s) {
        StringBuilder encrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            encrypted.append(++ch);
        }
        System.out.println(encrypted);
        return encrypted.toString();
    }

    public static String decipher(String s) {
        StringBuilder decrypted = new StringBuilder();
        char ch;

        for (int i = 0; i < s.length(); i++) {
            ch = s.charAt(i);
            decrypted.append(--ch);
        }

        return decrypted.toString();
    }
}
