/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

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
public class BaseTest {
    
    public BaseTest() {
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
     * Test of getCurrentHealth method, of class Base.
     */
    @Test
    public void testGetCurrentHealth() {
        System.out.println("getCurrentHealth");
        Base instance = null;
        int expResult = 0;
        int result = instance.getCurrentHealth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalHealth method, of class Base.
     */
    @Test
    public void testGetTotalHealth() {
        System.out.println("getTotalHealth");
        Base instance = null;
        int expResult = 0;
        int result = instance.getTotalHealth();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentHealth method, of class Base.
     */
    @Test
    public void testSetCurrentHealth() {
        System.out.println("setCurrentHealth");
        int health = 0;
        Base instance = null;
        instance.setCurrentHealth(health);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalHealth method, of class Base.
     */
    @Test
    public void testSetTotalHealth() {
        System.out.println("setTotalHealth");
        int health = 0;
        Base instance = null;
        instance.setTotalHealth(health);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of damage method, of class Base.
     */
    @Test
    public void testDamage() {
        System.out.println("damage");
        int dmg = 0;
        Base instance = null;
        instance.damage(dmg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInfected method, of class Base.
     */
    @Test
    public void testIsInfected() {
        System.out.println("isInfected");
        Base instance = null;
        boolean expResult = false;
        boolean result = instance.isInfected();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Base.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Base instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
