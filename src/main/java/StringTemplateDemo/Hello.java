package StringTemplateDemo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.stringtemplate.v4.ST;

public class Hello {
    public static void main(String[] args) throws IOException {
        
        ST hello = new ST(readByLines("f:/t/a.txt"));
//        List<String> tradestatus = new ArrayList<String>();
//         tradestatus.add("13903049");
//         tradestatus.add("13883223");
//        hello.add("tradeId", "TRADEID/MC00022065");
//        hello.add("profileId", 973);
        hello.add("facilityId", "CUSIP/1111003C1T");
        hello.add("profileId", 973);
        String value = hello.render();
        value = value.replace(":973", "973").replace(":CUSIP/1111003C1T", "'CUSIP/1111003C1T'");
        System.out.println(value);
    }
   
    private static String readByLines(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
}