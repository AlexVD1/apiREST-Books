package com.example.apiRESTBooks.testJunit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AssertTrueFalseTheory {
    @Test
    public void test1()
    {
        assertTrue(true);
        assertFalse(false);
    }

     @Test
    public void test2()
    {
        int a=2*1;
        boolean exp=2==a;
        assertTrue(exp);
        assertFalse(!exp);
    }
    
}
