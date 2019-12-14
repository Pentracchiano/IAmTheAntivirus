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
    private Base base;
    
    public BaseTest() {
    }
    
    @Before
    public void setUp() {
        base = new Base(0,0,20);
    }
    

    /**
     * Test of damage method, of class Base.
     */
    @Test
    public void testDamage() {
        System.out.println("damage");
        int dmg = 10;
        int currentHealth=base.getCurrentHealth();
        int excpected = currentHealth - dmg;
        base.damage(dmg);
        
        assertEquals("The health is not updated properly", excpected,base.getCurrentHealth());
    }

      /**
     * Tested if the health never becames negative
     */
    
    @Test
    public void testNegativeHealth() {
        System.out.println("Negative Health");
        int dmg = base.getCurrentHealth()+10;
        base.damage(dmg);
        
        assertEquals("The health is not updated properly", 0,base.getCurrentHealth());
    }
    /**
     * Test of isInfected method, of class Base.
     */
    @Test
    public void testIsInfected() {
        System.out.println("isInfected");
        int dmg = base.getCurrentHealth();
        base.damage(dmg);
        assertEquals("The base is not infected", true,base.isInfected());
    }
    

 
    
}
