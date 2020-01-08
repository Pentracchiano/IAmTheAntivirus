/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites.behaviors;

/**
 * An interface representing game elements that can restore a {@link Curable}'s
 * life-related property.
 * 
 * @author Francesco
 */
public interface Healer {
    /**
     * This method computes an integer value related to the health of a 
     * {@link Curable} and returns it. This value can represent, for example,
     * an increment for the value of the health, or a new value for it.
     * The specific meaning depends on the specific class implementing this 
     * interface.
     * 
     * @return An integer related to the health of a {@link Curable}.
     */
    public int getHealth();
}
