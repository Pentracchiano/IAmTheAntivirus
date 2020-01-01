/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import behaviors.ObliqueDirectionGenerator;
import controllers.IAmTheAntivirus;
import java.awt.Rectangle;
import static java.lang.Math.floor;
import java.util.Random;
import models.GameStatus;
import models.sprites.BaseHealer;

/**
 *
 * @author Francesco
 */
public class BaseHealerManager {
    //externalBoundaries sono le dimensioni della tastiera
    
    //forse posso passargli da qui anche l'attacco e l'heal
    
    public BaseHealer getBaseHealer(Rectangle externalBoundaries){
        Random r = new Random();
        int currentWaveNumber = GameStatus.getInstance().getCurrentWaveNumber();
        double guess = r.nextDouble();
        double healerProbability = 1 - (1/currentWaveNumber);
        if (guess >= healerProbability){
            return null;
        }
        //Alla 14esima wave diventa speed = 1. Più è bassa la velocità, più è difficile evitarlo.
        int healerSpeed = (int) (currentWaveNumber < 14 ? floor(14/currentWaveNumber) : 1); 
        BaseHealer retVal = new BaseHealer(0, 0, new ObliqueDirectionGenerator(), healerSpeed);
        //externalBoundaries.getX() + externalBoundaries.getWidth() - externalBoundaries.getX() - retVal.getWidth()
        int xLimit = (int) (externalBoundaries.getWidth() - retVal.getWidth());
        int x = r.nextInt(xLimit);
        retVal.setX((int) floor(x + externalBoundaries.getX()));
        retVal.setY((int) floor(externalBoundaries.getY() + externalBoundaries.getHeight()));
        return retVal;
    }
    
}
