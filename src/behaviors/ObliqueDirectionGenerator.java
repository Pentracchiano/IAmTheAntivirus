/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviors;

import java.awt.Rectangle;

/**
 * Class that generates {@link Direction} objects that allow sprites to move in
 * an oblique way.
 *
 * @author Francesco
 */
public class ObliqueDirectionGenerator implements DirectionGenerator {

    private SideDirectionGenerator sdg;

    public ObliqueDirectionGenerator() {
        sdg = new SideDirectionGenerator();
    }

    /**
     * Returns a randomly chosen oblique direction. The vertical component of
     * this direction is always directed toward the base, while, for the
     * vertical component, left and right direction have the same probability to
     * be chosen. The random choice is made at object construction. The method
     * always returns a direction the sprite can move along, compatibly with the
     * position and dimension of the sprite and the dimension of the space in
     * which it moves. If this direction does not exist (the sprite can't follow
     * an oblique path), the method returns null. To accomplish this aim, if the
     * randomly chosen direction is not practicable, the method checks if a
     * movement in the opposite direction is possible, and, if so, it returns
     * the opposite direction.
     *
     * @param movableSpriteBounds Bounds of the sprite for which the movement
     * direction is being generated.
     * @param externalBounds Bounds of the spaces the
     * ({@link models.sprites.behaviors.Movable}) sprite moves in.
     * @param speed The movement speed of the (movable) sprite.
     * @return An admissible direction for the sprite, or null if this does not
     * exist.
     */
    @Override
    public Direction getDirection(Rectangle movableSpriteBounds, Rectangle externalBounds, int speed) {
        /*
        * An oblique movement can be seen as the combination of an orizontal movement and 
        * a vertical movement. Since vertical movements are always possible (in the worst case
        * the sprite hits the base), I create an horizontal direction through the SideDirectionGenerator, 
        * and then update its vertical component, to create an oblique direction.
        *
        * I've chosen to use SideDirectionGenerator by aggregation and not inheritance because
        * I don't think an oblique movement can be seen as a specialization of an horizontal
        * movement.
        * 
        * A check on the dimension of the sprite and the space it moves in is made in the
        * getDirection method of the SideDirectionGenerator object.
        */
        
        Direction direction = sdg.getDirection(movableSpriteBounds, externalBounds, speed);
        if (direction != null) {
            direction.setY(-1);
        }

        return direction;
    }
}
