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
public class UpwardDirectionGenerator implements DirectionGenerator {

    @Override
    public Direction getDirection(Rectangle MovableSpriteBounds, Rectangle ExternalBounds) {
        return new Direction(0, -1);
    }
    
}
