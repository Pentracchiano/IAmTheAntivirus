/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.util.Random;
import models.sprites.Trojan;
import models.sprites.Virus;
import models.sprites.Worm;

/**
 *
 * @author Francesco
 */
/**
 * Concrete creator for viruses.
 */
public class VirusFactory {

    //Represents the total number of different concrete viruses that we have in
    //our application.
    private static final int NUM_VIRUSES = 2;

    //Factory Method
    public Virus createVirus(int xLeftLimit, int xRightLimit, int yPoint, int level) {
        Random r = new Random();
        Virus virus = null;
        int virusType = r.nextInt(NUM_VIRUSES);
        if (virusType == 0) {
            virus = new Worm(0, yPoint, level);
        } else if (virusType == 1) {
            virus = new Trojan(0, yPoint, level);
        }
        int x = r.nextInt(xRightLimit - virus.getWidth()) + xLeftLimit;
        virus.setX(x);
        return virus;
    }
}
