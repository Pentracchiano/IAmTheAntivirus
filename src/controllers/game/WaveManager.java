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
    // X-axis coordinate of the left on-screen limit for viruses' spawning point
    private final int xLeftLimit;
    // X-axis coordinate of the right on-screen limit for viruses' spawning point
    private final int xRightLimit;
    /*
    * Bottom border of the game-screen, that represents the y coordinate 
    * of the viruses' spawning point. 
    */
    private final int yPoint;
    private final VirusFactory virusFactory;
    
    /* 
    * Constants needed to generate the number of viruses in the wave.
    * This number is built by adding to the minimum number of viruses to be 
    * spawnend in a wave an increment. This increment is computed multiplying
    * the sum of a constant component and a randomic component by the wave
    * number.
    * What said until now is summarized in the following formula:
    * numberOfVirusesInCurrentWave = BASE_NUMBER_VIRUSES + (CONST_NUMBER_VIRUSES
    * + a number randomly chosen in NUMBER_VIRUSES_INTERVAL)*waveNumber
    */
    // Minimum number of viruses to be spawnend in a wave.
    private final static int BASE_NUMBER_VIRUSES = 4;
    /*
    * Numeric interval in which the randomic part of the number of viruses 
    * increment is chosen.
    */
    private final static int NUMBER_VIRUSES_INTERVAL = 2;
    // Constant part of the number of viruses increment
    private final static int CONST_NUMBER_VIRUSES = 1;
    
    /*
    * This is a vector of probability values used to choose the level of the
    * virus that is going to be added to the wave. The first component is the
    * probabilty of taking a virus of the maximum possible level. The second 
    * component is the probability of taking a virus of a level one unit 
    * smaller than the maximum possible level, and the third one is the 
    * probability of taking a virus of a level two units smaller than the 
    * maximum possible level.
    */
    private final static double[] SPAWN_PROBABILITIES = {0.1, 0.2, 0.7};
    
    /*
    * Constants needed to generate the spawn delay of the virus that is going to
    * be added to the wave. 
    * This delay is composed by a constant part, which decreases by a given
    * quantity as the wave number increases, and a randomic part, that is chosen 
    * in a given interval.
    * The constant part can't be smaller than a given value.
    */
    // Minimum value of the constant part of the delay.
    private final static int MIN_CONST_DELAY = 5;
    // Maximum value of the constant part of the delay.
    private final static int MAX_CONST_DELAY = 25;
    // Numeric interval in which the randomic part of the delay is chosen.
    private final static int RANDOM_INTERVAL_DELAY = 10; //shouldn't be constant
    // Overall maximum value of the delay
    private final static int MAX_DELAY = RANDOM_INTERVAL_DELAY + MAX_CONST_DELAY;
    /*
    * Overall minimum value of the delay.
    * It's composed by the minimum value of the constant delay plus the minimum
    * value of the random delay, but the latter one is zero.
    */
    private final static int MIN_DELAY = MIN_CONST_DELAY;
    // Value of the constant decrement applied to the delay through the waves.
    private final static double CONST_DELAY_DECREASE_QUANTITY = 5;
    
    /**
     * @param xLeftLimit X-axis coordinate of the left on-screen limit for 
     * viruses' spawning point
     * @param xRightLimit X-axis coordinate of the right on-screen limit for 
     * viruses' spawning point
     * @param yPoint Bottom border of the game-screen, that represents 
     * the y coordinate of the viruses' spawning point. 
     */
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
     * In each wave the difficulty increases. In particular this things happen:
     * - The number of viruses increases.
     * - The level of viruses increases.
     * - The spawn delay between two consecutive viruses decreases.
     * @param waveNumber The number of the current wave.
     * @return A collection of viruses ready to spawn, instances of
     * {@link VirusToSpawn} class.
     */
    public Wave getWave(int waveNumber) {
        if(waveNumber <= 0) {
            throw new IllegalArgumentException();
        }
        
        int waveDifficulty = 0;
        int lastDelay = 0;     
        Random r = new Random();
        
        // Take the randomic part of the number of viruses increment.
        int randomNumberViruses = r.nextInt(NUMBER_VIRUSES_INTERVAL);
        // Compute the maximum difficulty for this wave.
        int maxWaveDifficulty = getMaxWaveDifficulty(randomNumberViruses, waveNumber);
        
        List<VirusToSpawn> virusesToSpawn = new ArrayList<>();
        
        // Viruses are added to the wave until the maxWaveDifficulty is reached
        while (waveDifficulty < maxWaveDifficulty) {
            // Randomly choose virus' level.
            double choice = r.nextDouble();
            int level = getVirusLevel(choice, waveNumber);
            
            Virus virus = virusFactory.createVirus(this.xLeftLimit, this.xRightLimit, this.yPoint, level);
            
            // Choose the randomic part of the delay
            int randomDelay = r.nextInt(RANDOM_INTERVAL_DELAY);
            // Delay's dependency from the wave is contained in this function
            int constDelay = getConstDelay(waveNumber);  

            /*
            * Relative delay between two subsequent viruses. It's needed to 
            * accumulate on lastDelay and to compute the delay coefficient.
            */
            int relativeDelay = randomDelay + constDelay;
            lastDelay += relativeDelay;
            int delay = lastDelay;
            
            // Add a new virusToSpawn
            virusesToSpawn.add(new VirusToSpawn(virus, delay));
            
            /* 
            * Delay coefficient needed to compute the difficulty of the wave. 
            * It's 1 if relativeDelay == MAX. It's 2 if relativeDelay == MIN.
            * The remaining values are computed as points belonging to the 
            * straight line passing through these two points.
            */
            double delayCoeff = (double) (WaveManager.MIN_DELAY - 2 * WaveManager.MAX_DELAY + relativeDelay) /  (double) (WaveManager.MIN_DELAY - WaveManager.MAX_DELAY);
            
            waveDifficulty += (int) ceil(virus.getDifficulty() * delayCoeff);
            
        }

        //
        System.out.println("wave difficulty = " + waveDifficulty);
        
        return new Wave(virusesToSpawn);
    }
    
    /*
    * Computes the maximum difficulty for a wave with a given waveNuber.
    */
    private int getMaxWaveDifficulty(int randomNumberViruses, int waveNumber) {
        // Take a random virus ("sample virus"), of the maximum possible level.
        Virus sampleVirus = virusFactory.createVirus(this.xLeftLimit, this.xRightLimit, this.yPoint, waveNumber);
        /* 
        * The maximum difficulty for the wave is computed by multiplying the 
        * difficulty of the "sample virus" for the number of viruses in the wave,
        * computed as specified above.
        */
        int maxWaveDifficulty = sampleVirus.getDifficulty() * (BASE_NUMBER_VIRUSES + (randomNumberViruses + CONST_NUMBER_VIRUSES) * waveNumber);
        return maxWaveDifficulty;
    }
    
    /*
    * Computes the virus level according to the vector of probabilities, as
    * specified above.
    */
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

    /*
    * Returns the constant part of the delay, given the wave number, according
    * to the rules specified above.
    */
    private int getConstDelay(int waveNumber) {
        int constDelay = MAX_CONST_DELAY - (int)(waveNumber * CONST_DELAY_DECREASE_QUANTITY);
        if (constDelay < MIN_CONST_DELAY){
            constDelay = MIN_CONST_DELAY;
        }
        
        return constDelay;
    }
}
