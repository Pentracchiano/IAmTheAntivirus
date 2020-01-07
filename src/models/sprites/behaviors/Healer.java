/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites.behaviors;

/**
 * An interface representing game elements that can restore a {@link Curable}'s
 * health.
 * 
 * @author Francesco
 */
public interface Healer {
    /**
     * The method that applies the health-restoring process of this healer to
     * the received Curable.
     * 
     * @param toHeal the Curable to heal.
     */
    public int getHealth();
}
