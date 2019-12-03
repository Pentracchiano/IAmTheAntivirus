/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import utilities.ImageUtilities;

/**
 *
 * @author ccarratu
 */
public class Worm extends Virus {
    private static final String defaultImagePath = "src/resources/worm_30.png";
    
    public Worm(int x, int y) {
        super(x, y, null, 5, 20, 1);
        
        image = ImageUtilities.loadImageFromPath(defaultImagePath);
    }

    @Override
    public String toString() {
        return "Worm{" + "defaultImagePath=" + defaultImagePath + '}';
    }
    
}

