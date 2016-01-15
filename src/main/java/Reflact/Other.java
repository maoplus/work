package Reflact;

import java.lang.reflect.Field;

public class Other {
    private String str;
    
    public void setStr(String value) {
        str = value;
    }
    
    public String getStr() {
        return str;
    }
    
    protected String getHello(String value) {
        return value;
    }
}

class Test {
    public static void main(String[] args)
    // Just for the ease of a throwaway test. Don't
    // do this normally!
            throws Exception {
        Other t = new Other();
        
        t.setStr("hi");
        Field field = Other.class.getDeclaredField("str");
        field.setAccessible(true);
        // Object value = field.get(t);
        field.set(t, "hello");
        System.out.println("value =" + t.getStr());
    }
}