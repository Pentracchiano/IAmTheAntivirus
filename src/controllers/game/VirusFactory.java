/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.awt.Rectangle;
import java.util.Random;
import models.sprites.Trojan;
import models.sprites.Virus;
import models.sprites.Worm;

/**
 *
 * @author Francesco
 */

/**
 * VirusFactory is a concrete creator of viruses.
 * .
 */
public class VirusFactory {

    // Represents the total number of different concrete viruses that we have in
    // our application.
    private static final int NUM_VIRUSES = 2;

    /**
     * Creates a new Virus with the specified level.
     * 
     * @param xLeftLimit The minimum x that a virus can have.
     * @param xRightLimit The maximum x that a virus can have.
     * @param yPoint The y where the virus has to spawn.
     * @param level The level of the virus.
     * @return Returns one of the virus defined in the game.
     */
    public Virus createVirus(int xLeftLimit, int xRightLimit, int yPoint, int level) {
        Random r = new Random();
        
        Virus virus = null;
        
        // test
        Rectangle externalBounds = new Rectangle(0, 0, 0, 0);
        
        int virusType = r.nextInt(NUM_VIRUSES);
        if (virusType == 0) {
            virus = new Worm(0, yPoint, level, externalBounds);
        } else if (virusType == 1) {
            virus = new Trojan(0, yPoint, level, externalBounds);
        }
        
        // we should declare widht and height as static attributes
        int interval = xRightLimit - virus.getWidth();
        if(interval <= 0) {
            throw new IllegalArgumentException("xRightLimit must be grater than the width of the virus");
        }
        
        int x = r.nextInt(interval) + xLeftLimit;
        virus.setX(x);
        
        return virus;
    }
}
