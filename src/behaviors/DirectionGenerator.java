/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviors;

import java.awt.Rectangle;

/**
 *
 * @author ccarratu
 */

// It is used as a strategy pattern
public interface DirectionGenerator {
    public Direction getDirection(Rectangle MovableSpriteBounds, Rectangle ExternalBounds);
}
