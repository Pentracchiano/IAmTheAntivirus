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
    public Direction getDirection(Rectangle movableSpriteBounds, Rectangle externalBounds, int speed) {
        /*
        * Don't have to do any check, because a movable sprite can always move upwards. In the worst
        * case, it will hit the base.
        */
        return new Direction(0, -1);
    }
   
}
