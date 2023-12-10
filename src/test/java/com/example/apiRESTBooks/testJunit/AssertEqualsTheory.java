package com.example.apiRESTBooks.testJunit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class AssertEqualsTheory {
    @Test
    public void myTest(){
        assertEquals(1,1);
        //TEST WITH ERRIR assertEquals(1,2);
    }
    
}
