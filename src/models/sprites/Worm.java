/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import behaviors.UpwardDirectionGenerator;

/**
 * Worm is a particolar type of Virus.
 * It's has got a quite good health.
 * 
 * Speed: Low
 * Attack: Low
 * Health: Normal
 * 
 * @author ccarratu
 */
public class Worm extends Virus {
    private static final String DEFAULT_IMAGE_PATH = "src/resources/worm_50.png";
    private static final int BASE_BITCOINS_VALUE = Virus.BASE_BITCOINS_VALUE * 5;
    
    private static final int BASE_TOTAL_HEALTH = 50;
    private static final int BASE_ATTACK = 5;
    private static final int BASE_SPEED = 1;
    
    /**
     * Creates a new worm with the specified position and level.
     * 
     * @param x The x position of the worm.
     * @param y The y position of the worm.
     * @param level The level of the worm. The level determines the difficult, the value and the attributes of the instance.
     */
    public Worm(int x, int y, int level) {
        super(x, y, DEFAULT_IMAGE_PATH, BASE_TOTAL_HEALTH, BASE_SPEED, BASE_ATTACK, level, new UpwardDirectionGenerator());
        this.setBitcoinsValue(BASE_BITCOINS_VALUE); // maybe base * level in the future
    }
    
    @Override
    public String toString() {
        return "Worm: " + super.toString();
    }

    @Override
    public int getBaseBitcoinsValue() {
        return BASE_BITCOINS_VALUE;
    }
    
    
    
}

