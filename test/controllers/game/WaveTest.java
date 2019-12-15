/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.util.ArrayList;
import java.util.List;
import models.sprites.Worm;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francesco
 */
public class WaveTest {
    
    public WaveTest() {
    }
    
    /**
     * Test of spawnVirus method, of class Wave.
     */
    @Test
    public void testSpawnVirus() {
        System.out.println("spawnVirus");
        
        List<VirusToSpawn> virusesToSpawn = new ArrayList<>();
        virusesToSpawn.add(new VirusToSpawn(new Worm(0, 0, 1), 1));
        virusesToSpawn.add(new VirusToSpawn(new Worm(0, 0, 1), 2));
        
        Wave wave = new Wave(virusesToSpawn);
        
        wave.spawnVirus(0);
        assertTrue(wave.getVirusesToSpawnSize() == 2 && wave.getAliveSpawnedViruses().isEmpty());
        wave.spawnVirus(1);
        assertTrue(wave.getVirusesToSpawnSize() == 1 && wave.getAliveSpawnedViruses().size() == 1);
        wave.spawnVirus(1);
        assertTrue(wave.getVirusesToSpawnSize() == 1 && wave.getAliveSpawnedViruses().size() == 1);
        wave.spawnVirus(2);
        assertTrue(wave.getVirusesToSpawnSize() == 0 && wave.getAliveSpawnedViruses().size() == 2);
    }
    
}
