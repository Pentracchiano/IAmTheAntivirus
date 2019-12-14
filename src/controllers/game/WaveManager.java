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
            //new double[WaveManager.VIRUS_LEVEL_INTERVAL];
    
    private final static double DELAY_COEFF = 1;
    private final static double PATH_COEFF = 1;

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
        int maxWaveDifficulty = (int) ceil(INITIAL_WAVE_DIFFICULTY * WAVE_DIFFICULTY_MULTIPLIER * waveNumber);
        int delay = 0;
        List<VirusToSpawn> virusesToSpawn = new ArrayList<>();
        int waveDifficulty = 0;
        Random r = new Random();
        while (waveDifficulty < maxWaveDifficulty) {
            double guess = r.nextDouble();
            //System.out.println("guess: " + guess); // DEBUG
            int level;
            if (guess >= 0 && guess <= SPAWN_PROBABILITIES[0]){
                level = waveNumber;
            }
            else if (guess > SPAWN_PROBABILITIES[0] && guess <= SPAWN_PROBABILITIES[1]){
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
            waveDifficulty += (int) ceil(virus.getDifficulty() * DELAY_COEFF * PATH_COEFF);
            //System.out.println(virus.toString()); // DEBUG
            delay += r.nextInt(10);
            virusesToSpawn.add(new VirusToSpawn(virus, delay));
        }

        return new Wave(virusesToSpawn);
    }
}
