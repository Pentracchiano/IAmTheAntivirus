/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import static java.lang.Math.ceil;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import models.sprites.Virus;

/**
 *
 * @author Francesco
 */
public class WaveManager {
    private final int xLeftLimit;
    private final int xRightLimit;
    private final int yPoint;
    private final VirusFactory virusFactory;
    
    //To generate the number of enemies in the wave
    private final static int BASE_NUMBER_VIRUSES = 4;
    private final static int NUMBER_VIRUSES_INTERVAL = 2;
    private final static int CONST_NUMBER_VIRUSES = 1;
    
    //To choose the type of enemies in the wave
    private final static double[] SPAWN_PROBABILITIES = {0.1, 0.2, 0.7};
    
    //To generate the spawn delay in the wave
    private final static int LOW_CONST_DELAY = 5;
    private final static int MAX_CONST_DELAY = 25;
    private final static int RANDOM_INTERVAL_DELAY = 10; //shouldn't be constant
    
    private final static int MAX_DELAY = RANDOM_INTERVAL_DELAY + MAX_CONST_DELAY;
    private final static int MIN_DELAY = LOW_CONST_DELAY;
    private final static double CONST_DELAY_DECREASE_QUANTITY = 5;
    
    public WaveManager(int xLeftLimit, int xRightLimit, int yPoint) {
        this.xLeftLimit = xLeftLimit;
        this.xRightLimit = xRightLimit;
        this.yPoint = yPoint;
        virusFactory = new VirusFactory();
    }

    /**
     * This method returns a collection of viruses ready to spawn (instances of
     * VirusToSpawn class), whose spawn point x coordinate is between xLeftLimit
     * and xRightLimit, while its y coordinate is fixed, and it's equal to the
     * lower border of the application screen (not game's console screen).
     * 
     * In each wave the difficulty increases. In particular this things should happens:
     * - The number of viruses increase.
     * - The level of viruses increase.
     * - The spawn delay between two consecutive viruses decrease
     */
    public Wave getWave(int waveNumber) {
        // int maxWaveDifficulty = (int) ceil(BASE_WAVE_DIFFICULTY * waveNumber); // linear wrt wave number
        int waveDifficulty = 0;
        int lastDelay = 0;
        
        // evaluate maxWaveDifficulty as a number that depends of the number of viruses
        
        Random r = new Random();
        
        //Num virus in una wave è pari a BASE_NUMBER_VIRUSES + (CONST_NUMBER_VIRUSES
        //+ un numero a caso in NUMBER_VIRUSES_INTERVAL)*waveNumber
        int randomNumberViruses = r.nextInt(NUMBER_VIRUSES_INTERVAL);
        int maxWaveDifficulty = getMaxWaveDifficulty(randomNumberViruses, waveNumber);
        
        List<VirusToSpawn> virusesToSpawn = new ArrayList<>();
        
        // Viruses are add to the Wave untile the maxWaveDifficulty i reached
        //System.out.println("wave_difficuly: " + maxWaveDifficulty);
        while (waveDifficulty < maxWaveDifficulty) {
            double choice = r.nextDouble();
            int level = getVirusLevel(choice, waveNumber);
            
            Virus virus = virusFactory.createVirus(this.xLeftLimit, this.xRightLimit, this.yPoint, level);
            
            // delay choice
            int randomDelay = r.nextInt(RANDOM_INTERVAL_DELAY);
            //delay's dependency from the wave is contained in this function
            int constDelay = getConstDelay(waveNumber);  

            //Relative delay between two subsequent viruses. It's needed to accumulate
            //on lastDelay and to compute the delay coefficient
            int relativeDelay = randomDelay + constDelay;
            lastDelay += relativeDelay;
            int delay = lastDelay;
            
            // Add a new virusToSpawn
            virusesToSpawn.add(new VirusToSpawn(virus, delay));
            
            // delayCoeff is 1 if relativeDelay == MAX. It's 2 if relativeDelay == MIN.
            // The remaining point belong to the straight line passing through these two points.
            double delayCoeff = (double) (WaveManager.MIN_DELAY - 2 * WaveManager.MAX_DELAY + relativeDelay) /  (double) (WaveManager.MIN_DELAY - WaveManager.MAX_DELAY);
            
            waveDifficulty += (int) ceil(virus.getDifficulty() * delayCoeff);
        }

        return new Wave(virusesToSpawn);
    }
    
    private int getMaxWaveDifficulty(int randomNumberViruses, int waveNumber) {
        Virus sampleVirus = virusFactory.createVirus(this.xLeftLimit, this.xRightLimit, this.yPoint, waveNumber);
        int maxWaveDifficulty = sampleVirus.getDifficulty() * (BASE_NUMBER_VIRUSES + (randomNumberViruses + CONST_NUMBER_VIRUSES) * waveNumber);
        return maxWaveDifficulty;
    }
    
    private int getVirusLevel(double choice, int waveNumber) {
            int level;
        
            if (choice >= 0 && choice <= SPAWN_PROBABILITIES[0]){
                level = waveNumber;
            }
            else if (choice > SPAWN_PROBABILITIES[0] && choice <= (SPAWN_PROBABILITIES[0] + SPAWN_PROBABILITIES[1])) {
                level = waveNumber - 1;
            }
            else{
                level = waveNumber - 2;
            }
            
            if (level <= 0){
                level = 1;
            }
            
            return level;
    }
    
 
    
    private int getConstDelay(int waveNumber) {
        int constDelay = MAX_CONST_DELAY - (int)(waveNumber * CONST_DELAY_DECREASE_QUANTITY);
        if (constDelay < LOW_CONST_DELAY){
            constDelay = LOW_CONST_DELAY;
        }
        
        return constDelay;
    }
}
