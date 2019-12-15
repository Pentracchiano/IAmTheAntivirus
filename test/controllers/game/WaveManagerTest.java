/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ccarratu
 */
public class WaveManagerTest {
    
    public WaveManagerTest() {
    }
    
    @Before
    public void setUp() {
    }

    /**
     * Test of getWave method, of class WaveManager.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalWaveManagerParameters() {
        System.out.println("testIllegalWaveManagerParameters");
        
        WaveManager instance = new WaveManager(0,0,0);
        int waveNumber = 0;
        Wave expResult = null;
        Wave result = instance.getWave(waveNumber);
        assertEquals(expResult, result);

    }
    
    /**
     * Test of getWave method, of class WaveManager.
     */
    @Test(expected=IllegalArgumentException.class)
    public void testIllegalWaveNumber() {
       System.out.println("testIllegalWaveNumber");
       
       WaveManager instance = new WaveManager(500,500,200);
       int waveNumber = 0;
       Wave expResult = null;
       Wave result = instance.getWave(waveNumber);
       assertEquals(expResult, result);
    }
    
    /**
     * Test of getWave method, of class WaveManager.
     */
    @Test
    public void testGetWave() {
       System.out.println("testGetWave");
       
       WaveManager instance = new WaveManager(500,500,200);
       int waveNumber = 10;
       Wave wave = instance.getWave(waveNumber);
       boolean expResult, result;
       
       expResult = true;
       result = wave.hasVirusToSpawn();
       assertEquals(expResult, result);
       
       expResult = false;
       result = wave.hasAliveViruses();
       assertEquals(expResult, result);
    }
    
}
