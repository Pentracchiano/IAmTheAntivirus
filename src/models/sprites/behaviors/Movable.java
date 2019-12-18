/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites.behaviors;

import java.awt.Rectangle;

/**
 *
 * @author ccarratu
 */
public interface Movable {
    public void move();
    // Parameters externalBounds represents the boundaries of the space in which
    // the movable object moves.
    public void move(Rectangle externalBounds);
}
