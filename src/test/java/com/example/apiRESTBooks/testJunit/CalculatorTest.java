package com.example.apiRESTBooks.testJunit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculatorTest {
    Calculator calculator ;

    @BeforeAll
    public static void first(){
        System.out.println("First");
    }

    @BeforeEach
    public void instanceObjTest(){
         System.out.println("---Before Each---");
         calculator = new Calculator();
    }

    @Test 
    @DisplayName("PRUEBA EL ASSERT EQUALS")
    public void calculatorAssertEqualsTest(){
         System.out.println("Equals Test");
        assertEquals(2, calculator.sum(1, 1));
        assertEquals(3, calculator.res(4, 1));
        assertEquals(9, calculator.mult(3,3));
        assertEquals(2, calculator.div(2, 1));
    }

    @Test 
    @Disabled("ESTA PRUEBA NO SE EJECUTARA")
    public void calculatorAssertTFTest(){
         System.out.println("TF Test");
        assertTrue(2 == calculator.sum(1, 1));
        assertFalse(14 == calculator.mult(3, 4));
        assertFalse(2==calculator.res(24, 0));
        assertTrue(5==calculator.div(25, 5));;
    }

    @AfterAll
    public static void last(){
        System.out.println("Last");
    }

     @AfterEach
    public void afterEachTest(){
         System.out.println("---After Each---");
    }
    
}
