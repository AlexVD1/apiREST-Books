package com.example.apiRESTBooks.testJunit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class AssertNotEqualsTheory {
    @Test
    public void myTest(){
        assertNotEquals(1, 2);
        //TEST WITH ERROR assertNotEquals(1, 1);
    }
    
}
