/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import java.util.Collection;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davide Cafaro
 */
public class KeyboardTest {
    
    public KeyboardTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getSetKeyKeys method, of class Keyboard.
     */
    @Test
    public void testGetSetKeyKeys() {
        System.out.println("getSetKeyKeys");
        Keyboard instance = null;
        Set<Character> expResult = null;
        Set<Character> result = instance.getSetKeyKeys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKeys method, of class Keyboard.
     */
    @Test
    public void testGetKeys() {
        System.out.println("getKeys");
        Keyboard instance = null;
        Collection<Keyboard.Key> expResult = null;
        Collection<Keyboard.Key> result = instance.getKeys();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of press method, of class Keyboard.
     */
    @Test
    public void testPress() throws Exception {
        System.out.println("press");
        char id = ' ';
        Keyboard instance = null;
        instance.press(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getKey method, of class Keyboard.
     */
    @Test
    public void testGetKey() throws Exception {
        System.out.println("getKey");
        char keyCode = ' ';
        Keyboard instance = null;
        Keyboard.Key expResult = null;
        Keyboard.Key result = instance.getKey(keyCode);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of release method, of class Keyboard.
     */
    @Test
    public void testRelease() throws Exception {
        System.out.println("release");
        char id = ' ';
        Keyboard instance = null;
        instance.release(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
