package IO;

import java.util.Arrays;

import org.springframework.util.StringUtils;

public class ShouldReturnMethod {
    public static void main(String[] args) {
       prtMethod("Error finding workflow status for [{}] a parent trade for {}"); 
    }
   
    
    public static void prtMethod(String value){
        if (StringUtils.hasText(value)) {
            String methodName = Arrays.asList(value.split("[^a-zA-Z]"))
                    .stream().filter(word -> word.matches("\\w{2,}"))
                    .map(word -> capitalizationName(word))
                    .reduce("", (a, b) -> a + b);
            System.out.println( "shouldReturn" + methodName);
        }
    }
    
    private static String capitalizationName(String name) {
        char[] cs = name.toCharArray();
        if ('a' <= cs[0] && cs[0] <= 'z') {
            cs[0] -= 32;
        }
        else {
            return name;
        }
        return String.valueOf(cs);
    }
    
    static String firstLetterLowercase(String name) {
        char[] cs = name.toCharArray();
        if ('A' <= cs[0] && cs[0] <= 'Z') {
            cs[0] += 32;
        }
        else {
            return name;
        }
        return String.valueOf(cs);
    }
}
