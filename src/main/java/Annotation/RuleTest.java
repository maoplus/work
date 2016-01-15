package Annotation;

import org.junit.Rule;
import org.junit.Test;

public class RuleTest {
    @Rule
    public CatchExceptionRule rule = new CatchExceptionRule();
   
    @Test
    @ExceptionCatch(message = "zero", value = java.lang.ArithmeticException.class)
    public void mytest() {
       int a = 9;
       int b=1;
       int c = a/0;
    }
    @Test
    public void mytest2() {
        int a = 9;
        int b=1;
        int c = a/0;
    }
}
