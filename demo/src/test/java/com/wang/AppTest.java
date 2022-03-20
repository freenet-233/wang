package com.wang;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
//    @Test
//    public void shouldAnswerWithTrue()
//    {
//        assertTrue( true );
//    }
    public static void main(String[] args) {
        Person person = new Person();
        Map<String, Object> map = new HashMap();
        map.put("person", null);
//        map.get("person").toString();
        System.out.println(map.get("person"));

    }
}
