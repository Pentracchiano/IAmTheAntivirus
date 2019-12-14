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
    
    public Trojan(int x, int y) {
        super(x, y, DEFAULT_IMAGE_PATH, 5, 25, 2);
    }

    @Override
    public String toString() {
        return "Trojan: " + super.toString();
    }

    
    
}
