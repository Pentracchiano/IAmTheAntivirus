/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

/**
 *
 * @author ccarratu
 */
public class Trojan extends Virus {
    private static final String DEFAULT_IMAGE_PATH = "src/resources/trojan_50.png";
    private static final int BASE_BITCOINS_VALUE = Virus.BASE_BITCOINS_VALUE * 15;
    
    private static final int BASE_TOTAL_HEALTH = 25;
    private static final int BASE_ATTACK = 5;
    private static final int BASE_SPEED = 2;
    
    public Trojan(int x, int y, int level) {
        super(x, y, DEFAULT_IMAGE_PATH, BASE_TOTAL_HEALTH, BASE_SPEED, BASE_ATTACK, level);
        this.setBitcoinsValue(BASE_BITCOINS_VALUE); // maybe base * level in the future
    }

    @Override
    public String toString() {
        return "Trojan: " + super.toString();
    }

    @Override
    public int getBaseBitcoinsValue() {
        return BASE_BITCOINS_VALUE;
    }

    
    
}
