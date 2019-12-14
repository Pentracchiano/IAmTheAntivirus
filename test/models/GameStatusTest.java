/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

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
public class GameStatusTest {
    
    public GameStatusTest() {
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
     * Test of getInstance method, of class GameStatus.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        GameStatus expResult = null;
        GameStatus result = GameStatus.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetInstance method, of class GameStatus.
     */
    @Test
    public void testResetInstance() {
        System.out.println("resetInstance");
        GameStatus.resetInstance();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMultiplier method, of class GameStatus.
     */
    @Test
    public void testGetMultiplier() {
        System.out.println("getMultiplier");
        GameStatus instance = null;
        int expResult = 0;
        int result = instance.getMultiplier();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetConsecutiveHits method, of class GameStatus.
     */
    @Test
    public void testResetConsecutiveHits() {
        System.out.println("resetConsecutiveHits");
        GameStatus instance = null;
        instance.resetConsecutiveHits();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of incrementConsecutiveHits method, of class GameStatus.
     */
    @Test
    public void testIncrementConsecutiveHits() {
        System.out.println("incrementConsecutiveHits");
        GameStatus instance = null;
        instance.incrementConsecutiveHits();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addBitcoinsAndScore method, of class GameStatus.
     */
    @Test
    public void testAddBitcoinsAndScore() {
        System.out.println("addBitcoinsAndScore");
        int bitcoins = 0;
        GameStatus instance = null;
        instance.addBitcoinsAndScore(bitcoins);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of withdrawBitcoins method, of class GameStatus.
     */
    @Test
    public void testWithdrawBitcoins() {
        System.out.println("withdrawBitcoins");
        int bitcoins = 0;
        GameStatus instance = null;
        instance.withdrawBitcoins(bitcoins);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScore method, of class GameStatus.
     */
    @Test
    public void testGetScore() {
        System.out.println("getScore");
        GameStatus instance = null;
        int expResult = 0;
        int result = instance.getScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBitcoins method, of class GameStatus.
     */
    @Test
    public void testGetBitcoins() {
        System.out.println("getBitcoins");
        GameStatus instance = null;
        int expResult = 0;
        int result = instance.getBitcoins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInGame method, of class GameStatus.
     */
    @Test
    public void testIsInGame() {
        System.out.println("isInGame");
        GameStatus instance = null;
        boolean expResult = false;
        boolean result = instance.isInGame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInGame method, of class GameStatus.
     */
    @Test
    public void testSetInGame() {
        System.out.println("setInGame");
        boolean inGame = false;
        GameStatus instance = null;
        instance.setInGame(inGame);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInWave method, of class GameStatus.
     */
    @Test
    public void testIsInWave() {
        System.out.println("isInWave");
        GameStatus instance = null;
        boolean expResult = false;
        boolean result = instance.isInWave();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInWave method, of class GameStatus.
     */
    @Test
    public void testSetInWave() {
        System.out.println("setInWave");
        boolean inWave = false;
        GameStatus instance = null;
        instance.setInWave(inWave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentWave method, of class GameStatus.
     */
    @Test
    public void testGetCurrentWave() {
        System.out.println("getCurrentWave");
        GameStatus instance = null;
        int expResult = 0;
        int result = instance.getCurrentWave();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentWave method, of class GameStatus.
     */
    @Test
    public void testSetCurrentWave() {
        System.out.println("setCurrentWave");
        int currentWave = 0;
        GameStatus instance = null;
        instance.setCurrentWave(currentWave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotalWaveEnemies method, of class GameStatus.
     */
    @Test
    public void testGetTotalWaveEnemies() {
        System.out.println("getTotalWaveEnemies");
        GameStatus instance = null;
        int expResult = 0;
        int result = instance.getTotalWaveEnemies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotalWaveEnemies method, of class GameStatus.
     */
    @Test
    public void testSetTotalWaveEnemies() {
        System.out.println("setTotalWaveEnemies");
        int totalWaveEnemies = 0;
        GameStatus instance = null;
        instance.setTotalWaveEnemies(totalWaveEnemies);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRemainingWaveEnemies method, of class GameStatus.
     */
    @Test
    public void testGetRemainingWaveEnemies() {
        System.out.println("getRemainingWaveEnemies");
        GameStatus instance = null;
        int expResult = 0;
        int result = instance.getRemainingWaveEnemies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRemainingWaveEnemies method, of class GameStatus.
     */
    @Test
    public void testSetRemainingWaveEnemies() {
        System.out.println("setRemainingWaveEnemies");
        int remainingWaveEnemies = 0;
        GameStatus instance = null;
        instance.setRemainingWaveEnemies(remainingWaveEnemies);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isInWaveTransition method, of class GameStatus.
     */
    @Test
    public void testIsInWaveTransition() {
        System.out.println("isInWaveTransition");
        GameStatus instance = null;
        boolean expResult = false;
        boolean result = instance.isInWaveTransition();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInWaveTransition method, of class GameStatus.
     */
    @Test
    public void testSetInWaveTransition() {
        System.out.println("setInWaveTransition");
        boolean waveTransition = false;
        GameStatus instance = null;
        instance.setInWaveTransition(waveTransition);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
