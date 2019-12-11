/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.util.Random;
import models.Trojan;
import models.Virus;
import models.Worm;

/**
 *
 * @author Francesco
 */
public class WaveManager {
    //private GameStatus gameStatus;
    //private int currentWave;

    public WaveManager() {
        //gameStatus = GameStatus.getInstance();
    }

    /**
     * This method returns a collection of viruses ready to spawn (instances of
     * VirusToSpawn class), whose spawn point x coordinate is between xLeftLimit
     * and xRightLimit, while its y coordinate is fixed, and it's equal to the
     * lower border of the application screen (not game's console screen).
     */
    public Wave getWave(int xLeftLimit, int xRightLimit, int yPoint) {
        /* Should use State design pattern, to return different waves,
               depending on the value of the currentWave in GameStatus
         */

        //keyboard.getX() = xLeftLimit
        //keyboard.getWidth() = xRightLimit
        //view.getHeight = yPoint
        Random r = new Random();

        int virusType;
        int delay;
        Virus virus = null;

        Wave wave = new Wave();

        for (int i = 0; i < 5; i++) {

            virusType = r.nextInt(2);
            if (virusType == 0) {
                virus = new Worm(0, yPoint);
            } else if (virusType == 1) {
                virus = new Trojan(0, yPoint);
            }

            int x = r.nextInt(xRightLimit - virus.getWidth()) + xLeftLimit;
            virus.setX(x);

            delay = r.nextInt(10) + i * 20;

            wave.addElement(new VirusToSpawn(virus, delay));
        }

        return wave;
    }
}
