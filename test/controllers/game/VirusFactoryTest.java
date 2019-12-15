/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import models.sprites.Virus;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francesco
 */
public class VirusFactoryTest {
    
    VirusFactory instance;
    
    public VirusFactoryTest() {
    }
    
    @Before
    public void setUp() {
        instance = new VirusFactory();
    }
    
    /**
     * Test of createVirus method, of class VirusFactory.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateVirusExc() {
        System.out.println("createVirusExc");
        int xLeftLimit = 0;
        int xRightLimit = 0;
        int yPoint = 0;
        int level = 0;
        Virus result = instance.createVirus(xLeftLimit, xRightLimit, yPoint, level);
    }
    
    @Test
    public void testCreateVirus() {
        System.out.println("createVirus");
        int xLeftLimit = 0;
        int xRightLimit = 100;
        int yPoint = 0;
        int level = 0;
        Virus result = instance.createVirus(xLeftLimit, xRightLimit, yPoint, level);
        assertEquals(result.getY(), yPoint);
        assertEquals(result.getLevel(), level);
        assertTrue(result.getX() > xLeftLimit && result.getX() < xRightLimit);
    }
    
}
