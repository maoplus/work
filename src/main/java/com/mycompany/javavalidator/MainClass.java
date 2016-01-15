package com.mycompany.javavalidator;

import static org.junit.Assert.assertEquals;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

public class MainClass {
   public static void main(String[] args) {
	   setUp();
	   System.out.println(validator);
//	   MainClass m = new MainClass();
//	   m.testLicensePlateNotUpperCase();
}

    private static Validator validator;

   
    public static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

 
    public void testLicensePlateNotUpperCase() {

        Car car = new Car("Morris", "dd-ab-123", 4);

        Set<ConstraintViolation<Car>> constraintViolations =
            validator.validate(car);
        assertEquals(1, constraintViolations.size());
        assertEquals(
            "Case mode must be UPPER.", 
            constraintViolations.iterator().next().getMessage());
    }

  
    public void carIsValid() {

        Car car = new Car("Morris", "DD-AB-123", 4);

        Set<ConstraintViolation<Car>> constraintViolations =
            validator.validate(car);

        assertEquals(0, constraintViolations.size());
    }

}
