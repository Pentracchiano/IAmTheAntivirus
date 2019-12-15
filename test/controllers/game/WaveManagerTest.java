/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

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
public class WaveManagerTest {
    
    public WaveManagerTest() {
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
     * Test of getWave method, of class WaveManager.
     */
    @Test
    public void testGetWave() {
        System.out.println("getWave");
        int xLeftLimit = 0;
        int xRightLimit = 0;
        int yPoint = 0;
        WaveManager instance = new WaveManager(xLeftLimit, xRightLimit, yPoint);
        Wave expResult = null;
        Wave result = instance.getWave(1);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
