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
public class Trojan extends Virus {
    private static final String defaultImagePath = "src/resources/trojan_30.png";
    
    public Trojan(int x, int y) {
        super(x, y, null, 5, 10, 2);
        
        image = ImageUtilities.loadImageFromPath(defaultImagePath);
    }

    @Override
    public String toString() {
        return "Trojan{" + "defaultImagePath=" + defaultImagePath + '}';
    }
    
}
