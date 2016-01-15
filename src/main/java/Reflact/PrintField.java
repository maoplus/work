package Reflact;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.junit.Test;

import anotationdemo.Apple;

public class PrintField {
    
    public static void main(String[] args) {
        
    }
    
    @Test
    public void printField() throws IllegalArgumentException,
            IllegalAccessException {
        Apple apple = new Apple();
        setField(apple);
    }
    
    public void setField(Object object) throws IllegalArgumentException,
            IllegalAccessException {
        Field[] fields = object.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().isAssignableFrom(String.class)) {
                if (Modifier.toString(field.getModifiers()).contains("final")) {
                    continue;
                }
                System.out.println(field.getName() + "  "
                        + Modifier.toString(field.getModifiers()));
                
            }
        }
    }
    
    public static void showAttr(Object o, String title) {
        if (o == null) {
            System.out.println("空值");
            return;
        }
        Field[] fields = o.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName().substring(0, 1)
                    .toUpperCase()
                    + fields[i].getName().substring(1);
            String fieldName2 = fields[i].getName().substring(0, 1)
                    .toUpperCase()
                    + fields[i].getName().substring(1);
            System.out.println();
            
        }
    }
}