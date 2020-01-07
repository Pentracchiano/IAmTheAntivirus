/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites.behaviors;

/**
 * An interface for game elements, tipically Sprites, whose health, or any other
 * life-related parameter, can be restored, after being damaged.
 * 
 * @author Francesco
 */
public interface Curable {
    /**
     * The method that actually heals the Curable. The implementation of the
     * method depends of the specific Curable implementing it, while the meaning
     * of the received parameter depends on the specific {@link Healer} 
     * currently used.
     * 
     * @param health The meaning of this parameter depends on the specific 
     * healer used.
     */
    public void heal(int health);
}
