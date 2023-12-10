package com.example.apiRESTBooks.testJunit;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.jupiter.api.Test;

public class AssertArrayEqualsTheory {

    @Test
    public void testArrayEquals(){
        String [] arr1Strings = {"a","b"};
        String [] arr2Strings = {"a","b"};
        //String [] arr3Strings = {"a","b","c"};
        assertArrayEquals(arr1Strings, arr2Strings);
        //assertArrayEquals(arr2Strings, arr3Strings);
        //assertArrayEquals(arr1Strings, arr3Strings);
    }

    
}
