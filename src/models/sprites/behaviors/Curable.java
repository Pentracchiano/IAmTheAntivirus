/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites.behaviors;

/**
 * An interface for game elements, tipically Sprites, that have an health, which
 * can be restored after events that compromise it.
 * 
 * @author Francesco
 */
public interface Curable {
    public int getCurrentHealth();
    public void setCurrentHealth(int health);
}
