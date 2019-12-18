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

/*
* x and y coordinates of the movableSpriteBounds object are the coordinates of
* this point:
*                   O---------
*                   |         |
*                   |         |
*                   |         |  
*                    ---------
*/
public interface DirectionGenerator {
    // Classes implementing this method have the responsibility to always return
    // admissible directions with respect to speed, actual position and dimension
    // of the sprite
    public Direction getDirection(Rectangle movableSpriteBounds, Rectangle externalBounds, int speed);
}
