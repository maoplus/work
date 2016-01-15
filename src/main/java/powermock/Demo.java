package powermock;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Set;

import org.junit.After;
import org.junit.Test;
import org.mockito.Mock;
import org.powermock.reflect.Whitebox;

public class Demo {
    private Computer coumputer;
    private String   name;
    public Demo(){
        
    }
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Computer getCoumputer() {
        return coumputer;
    }
    
    public void setCoumputer(Computer coumputer) {
        this.coumputer = coumputer;
    }
    public static void testFile(){
        File fff  = new File("9999999999999bbbbbb");
        System.out.println(fff.exists());
    }
}