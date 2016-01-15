package format;

import java.util.Arrays;

import org.junit.Test;
import org.springframework.util.StringUtils;

import com.google.common.base.Strings;

public class GetErrorMessage {
    
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
    
    private void getError(String value) {
        value = value.replace("'t", "not");
        if (StringUtils.hasText(value)) {
            String methodName = Arrays.asList(value.split("[^a-zA-Z]"))
                    .stream().filter(word -> word.matches("\\w{2,}"))
                    .map(word -> capitalizationName(word))
                    .reduce("", (a, b) -> a + b);
            System.out.println( "shouldReturn" + methodName);
        }
    }
    
    @Test
    public void generateRepeat() {
        char[] chars = new char[255];
        Arrays.fill(chars, '0');
        String text = new String(chars);
        System.out.println(text);
    }
    @Test
    public void generateRepeat2() {
        System.out.println(Strings.repeat("1", 16));
    }
    @Test
    public void prtError(){
        getError("Comment can not be null.");
    }
}
