/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davide Cafaro
 */
public class GameStatusTest {
    
    GameStatus gameStatus;
    
    @Before
    public void setUp() {
        gameStatus = GameStatus.getInstance();
    }
    
    @After
    public void tearDown() {
        GameStatus.disposeInstance();
    }

    /**
     * Tests if the get instance gives a default-initialized instance when called for the first time.
     */
    @Test
    public void testGetInstance() {
        GameStatus currentInstance = gameStatus;
        
        int actualBitcoins = currentInstance.getBitcoins();
        int actualWave = currentInstance.getCurrentWave();
        int actualMultiplier = currentInstance.getMultiplier();
        int actualRemainingWaveEnemies = currentInstance.getRemainingWaveEnemies();
        int actualScore = currentInstance.getScore();
        int actualTotalWaveEnemies = currentInstance.getTotalWaveEnemies();
        boolean actualIsInGame = currentInstance.isInGame();
        boolean actualIsInWave = currentInstance.isInWave();
        boolean actualIsInWaveTransition = currentInstance.isInWaveTransition();
        
        int defaultBitcoins = 0;
        int defaultWave = 0;
        int defaultMultiplier = 1;
        int defaultRemainingEnemies = 0;
        int defaultScore = 0;
        int defaultTotalWaveEnemies = 0;
        boolean defaultIsInGame = false;
        boolean defaultIsInWave = false;
        boolean defaultIsInWaveTransition = false;
        
        assertEquals(actualBitcoins, defaultBitcoins);
        assertEquals(actualWave, defaultWave);
        assertEquals(actualMultiplier, defaultMultiplier);
        assertEquals(actualRemainingWaveEnemies, defaultRemainingEnemies);
        assertEquals(actualScore, defaultScore);
        assertEquals(actualTotalWaveEnemies, defaultTotalWaveEnemies);
        assertEquals(actualIsInGame, defaultIsInGame);
        assertEquals(actualIsInWave, defaultIsInWave);
        assertEquals(actualIsInWaveTransition, defaultIsInWaveTransition);
    }
    
    /**
     * Test of disposeInstance method, of class GameStatus.
     * Checks if the new instance is a new one.
     */
    @Test
    public void testDisposeInstance() {
        System.out.println("disposeInstance");
        
        GameStatus previousInstance = gameStatus;
        
        // random modifications to the old instance
        gameStatus.addBitcoinsAndScore(25);
        gameStatus.setCurrentWave(3);
        gameStatus.setInGame(true);
        for(int i = 0; i < 100; i++) {
            gameStatus.incrementConsecutiveHits();
        }
        gameStatus.setInWave(true);
        
        // reset of the instance
        
        GameStatus.disposeInstance();
        GameStatus currentInstance = GameStatus.getInstance();
        
        // checking usage of the instance
        
        assertNotSame("After dispose, GameStatus is still using the old instance.",
                previousInstance, currentInstance);
        
        assertSame("After dispose, the current instance is different from the getInstance.",
                currentInstance, GameStatus.getInstance());
        
        }

    /**
     * Test of getMultiplier method, of class GameStatus.
     * Default case is not checked: it is checked in testGetInstance().
     * Checking if the multiplier exceeds max multiplier.
     */
    @Test
    public void testGetMultiplierMax() {
        System.out.println("getMultiplier Max");
        
        for(int i = 0; i < gameStatus.MAX_MULTIPLIER * gameStatus.HITS_PER_MULTIPLIER * 2; i++) {
            gameStatus.incrementConsecutiveHits();
        }
        
        assertEquals(gameStatus.getMultiplier(), gameStatus.MAX_MULTIPLIER);
    }
    
    /**
     * Test of getMultiplier method, of class GameStatus.
     * Default case is not checked: it is checked in testGetInstance().
     * Checking if the multiplier is correctly updated when incrementing the consecutive hits.
     */
    @Test
    public void testGetMultiplierPerHits() {
        System.out.println("testGetMultiplierPerHits");
        
        for(int i = 0; i < gameStatus.HITS_PER_MULTIPLIER - 1; i++) {
            gameStatus.incrementConsecutiveHits();
            assertNotEquals(gameStatus.getMultiplier(), 2);
        }
        
        gameStatus.incrementConsecutiveHits();
        assertEquals(gameStatus.getMultiplier(), 2);
    }

    /**
     * Test of resetConsecutiveHits method, of class GameStatus.
     */
    @Test
    public void testResetConsecutiveHits() {
        System.out.println("resetConsecutiveHits");
        
        for(int i = 0; i < 100; i++) {
            gameStatus.incrementConsecutiveHits();
        }
        gameStatus.resetConsecutiveHits();
        
        assertEquals(gameStatus.getMultiplier(), 1);
    }

    /**
     * Test of addBitcoinsAndScore method, of class GameStatus.
     */
    @Test
    public void testAddBitcoinsAndScore() {
        System.out.println("addBitcoinsAndScore");

        int amount = 25;
        
        gameStatus.addBitcoinsAndScore(amount);
        assertEquals(gameStatus.getScore(), gameStatus.getBitcoins());
        assertEquals(gameStatus.getBitcoins(), amount);
    }

    /**
     * Test of withdrawBitcoins method, of class GameStatus.
     * Tests an enourmous withdrawal, impossible for now.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithdrawBitcoinsNegative() {
        System.out.println("withdrawBitcoins Negative");
        gameStatus.withdrawBitcoins(10000);
    }
    
    /**
     * Test of withdrawBitcoins method, of class GameStatus.
     * Checks if the correct amount is substracted to the bitcoins amount.
     */
    @Test
    public void testWithdrawBitcoins() {
        System.out.println("withdrawBitcoins");
        int addAmount = 100;
        int withdrawalAmount = 75;
        
        gameStatus.addBitcoinsAndScore(addAmount);
        gameStatus.withdrawBitcoins(withdrawalAmount);
        
        assertEquals(gameStatus.getBitcoins(), addAmount - withdrawalAmount);
    }

    
}
