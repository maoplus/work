package format;

public class CharsetTest {
    public static String test = "No lions. No tigers, but bears …";
    
    static void showEncodedLength(String s, String encoding) throws Exception {
        byte[] b = s.getBytes(encoding);
        System.out.println(encoding + " byte length: " + b.length);
    }
    
    public static void main(String[] args) throws Exception {
        System.out.println("String length: " + CharsetTest.test.length());
        showEncodedLength(CharsetTest.test, "ASCII");
        showEncodedLength(CharsetTest.test, "ISO8859_1");
        showEncodedLength(CharsetTest.test, "UTF8");
    }
}
