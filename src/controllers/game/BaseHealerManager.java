/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import behaviors.ObliqueDirectionGenerator;
import java.awt.Rectangle;
import static java.lang.Math.floor;
import java.util.Random;
import models.GameStatus;
import models.sprites.Base;
import models.sprites.BaseHealer;

/**
 * The class that actually manages the creation of the {@link BaseHealer}
 * instances through the different waves.
 *
 * @author Francesco
 */
public class BaseHealerManager {
    
    private final Base base;
    private int maxSpeed = 20;
    private int minSpeed = 5;
    
    public BaseHealerManager(Base base){
        this.base = base;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getMinSpeed() {
        return minSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setMinSpeed(int minSpeed) {
        this.minSpeed = minSpeed;
    }
    

    /**
     * This methods manages the stochastic creation of a {@link BaseHealer}. The
     * healer is created with probability 1 - (1/currentWaveNumber), otherwise
     * the method returns null. If created, the healer is built to move in an
     * oblique way, and has a speed that starts from the maxSpeed 
     * (that defaults to 20) and decreases linearly with the number of the 
     * current wave, until it reaches minSpeed (that defaults to 5). Once it
     * reaches minSpeed, it doesn't change anymore. This is done in order to make 
     * it easier to kill the healer (and then get the base damaged) as the waves
     * pass through. The healer spawns from the bottom of received boundaries;
     * the x-axis coordinate of the spawn point is chosen randomly, but it's
     * ensured to be into the limits of the boundaries.
     *
     * @param externalBoundaries A {@link Rectangle} representing the boundaries
     * of the field in which the haler moves.
     * @return A {@link BaseHealer} with probability 1 - (1/currentWaveNumber),
     * otherwise null.
     */
    public BaseHealer getBaseHealer(Rectangle externalBoundaries) {
        Random r = new Random();
        int currentWaveNumber = GameStatus.getInstance().getCurrentWaveNumber();
        double guess = r.nextDouble();
        double healerProbability = 1 - (1 / currentWaveNumber);
        if (guess >= healerProbability) {
            return null;
        }
        /*
         * At 4th wave speed = 5. The quicker the healer is, the harder is to
         * avoid to kill it.
         */
        int speed = (int) floor(maxSpeed / currentWaveNumber);
        int healerSpeed = (int) (speed < minSpeed ? minSpeed : speed);
        // Create the healer with a trivial spawning point.
        BaseHealer retVal = new BaseHealer(0, 0, new ObliqueDirectionGenerator(), healerSpeed, base.getTotalHealth());
        /*
        * The x-axis coordinate of the spawning point is a random interger 
        * between the x coordinate of the beginning of the given boundaries, and the x
        * coordinate of its end minus the width of the healer.
         */
        //externalBoundaries.getX() + externalBoundaries.getWidth() - externalBoundaries.getX() - retVal.getWidth()
        int xLimit = (int) (externalBoundaries.getWidth() - retVal.getWidth());
        int x = r.nextInt(xLimit);
        retVal.setX((int) floor(x + externalBoundaries.getX()));
        /*
        * The y-axis coordinate of the spawning point is equal to the bottom of the given
        * boundaries.
         */
        retVal.setY((int) floor(externalBoundaries.getY() + externalBoundaries.getHeight()));
        
        return retVal;
    }

}
