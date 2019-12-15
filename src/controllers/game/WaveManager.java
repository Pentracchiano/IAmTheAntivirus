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
import models.sprites.Trojan;
import models.sprites.Virus;
import models.sprites.Worm;

/**
 *
 * @author Francesco
 */
public class WaveManager {
    private final int xLeftLimit;
    private final int xRightLimit;
    private final int yPoint;
    private final VirusFactory virusFactory;
    
    private final static int INITIAL_WAVE_DIFFICULTY = 300;
    private final static double WAVE_DIFFICULTY_MULTIPLIER = 0.5;
    private final static int VIRUS_LEVEL_INTERVAL = 3;
    private final static double[] SPAWN_PROBABILITIES = {0.2, 0.3, 0.5};
    private final static double DELAY_COEFF = 1;
    private final static double PATH_COEFF = 1;
    private final static int LOWEST_CONST_DELAY = 5; //5*20 = 100 ms
    private final static int MAX_CONST_DELAY = 75; //half a second
    private final static double CONST_DELAY_DECREASE_QUANTITY = 3;
    
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
     */
    public Wave getWave(int waveNumber) {
        int constantDelay; //minum spawn interval between two different viruses
        int randomDelay; //random additive spawn interval between two different viruses
        
        int maxWaveDifficulty = (int) ceil(INITIAL_WAVE_DIFFICULTY * WAVE_DIFFICULTY_MULTIPLIER * waveNumber); //linear wrt wave number
        int delay = 0;
        List<VirusToSpawn> virusesToSpawn = new ArrayList<>();
        int waveDifficulty = 0;
        Random r = new Random();
        while (waveDifficulty < maxWaveDifficulty) {
            // Virus level construction
            double guess = r.nextDouble();
            int level;
            if (guess >= 0 && guess <= SPAWN_PROBABILITIES[0]){
                level = waveNumber;
            }
            else if (guess > SPAWN_PROBABILITIES[0] && guess <= (SPAWN_PROBABILITIES[0] + SPAWN_PROBABILITIES[1])){ //guess > SPAWN_PROBABILITIES[0] && guess <= SPAWN_PROBABILITIES[1]
                level = waveNumber - 1;
            }
            else{
                level = waveNumber - 2;
            }
            if (level <= 0){
                level = 1;
            }
            Virus virus;
            virus = virusFactory.createVirus(this.xLeftLimit, this.xRightLimit, this.yPoint, level);
            
            //Virus delay construction
            constantDelay = this.MAX_CONST_DELAY - (int)(waveNumber*WaveManager.CONST_DELAY_DECREASE_QUANTITY);
            if (constantDelay < WaveManager.LOWEST_CONST_DELAY){
                constantDelay = WaveManager.LOWEST_CONST_DELAY;
            }
            System.out.println("Constant delay: " + constantDelay);
            //double delayCoeff = 
            waveDifficulty += (int) ceil(virus.getDifficulty() * DELAY_COEFF * PATH_COEFF);
            delay += (r.nextInt(10) + constantDelay);
            virusesToSpawn.add(new VirusToSpawn(virus, delay));
        }

        return new Wave(virusesToSpawn);
    }
}
