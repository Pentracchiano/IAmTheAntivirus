/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import behaviors.ObliqueDirectionGenerator;

/**
 * Trojan is a particolar type of Virus.
 *  It's quite fast.
 * 
 * Speed: Normal
 * Attack: Low
 * Health: Low
 * 
 * @author ccarratu
 */
public class Trojan extends Virus {
    private static final String DEFAULT_IMAGE_PATH = "src/resources/trojan_50.png";
    
    private static final int BASE_TOTAL_HEALTH = 25;
    private static final int BASE_ATTACK = 5;
    private static final int BASE_SPEED = 2;
    
    /**
     * Creates a new trojan with the specified position and level.
     * 
     * @param x The x position of the trojan.
     * @param y The y position of the trojan.
     * @param level The level of the trojan. The level determines the difficult, the value and the attributes of the instance.
     */
    public Trojan(int x, int y, int level) {
        super(x, y, DEFAULT_IMAGE_PATH, BASE_TOTAL_HEALTH, BASE_SPEED, BASE_ATTACK, level, new ObliqueDirectionGenerator());
    }

    @Override
    public String toString() {
        return "Trojan: " + super.toString();
    }
        
}
