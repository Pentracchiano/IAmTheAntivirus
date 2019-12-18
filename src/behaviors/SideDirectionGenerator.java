/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviors;

import java.awt.Rectangle;
import java.util.Random;

/**
 * Class that generates {@link Direction} objects that allow sprites to move
 * horizontally.
 *
 * @author Francesco
 */
public class SideDirectionGenerator implements DirectionGenerator {
    // Return value of the getDirection method
    private Direction directionToReturn = null;
    // Horizontal direction
    int sideDirection;

    public SideDirectionGenerator() {
        // Randomly choose left o right direction
        Random r = new Random();
        double guess = r.nextDouble();
        sideDirection = guess < 0.5 ? 0 : 1;
    }

    /**
     * Returns a randomly chosen horizontal direction. 
     * The random choice is made at object construction.
     * Left and right direction
     * have the same probability to be chosen. The method always returns a
     * direction the sprite can move along, compatibly with the position and
     * dimension of the sprite and the dimension of the space in which it moves.
     * If this direction does not exist (the sprite can't move horizontally),
     * the method returns null. 
     * To accomplish this aim, if the randomly chosen direction is not practicable, 
     * the method checks if a movement in the
     * opposite direction is possible, and, if so, it returns the opposite
     * direction.
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
        if (movableSpriteBounds.getWidth() > externalBounds.getWidth()
                || movableSpriteBounds.getHeight() > externalBounds.getHeight()) {
            throw new MovableGreaterThanFieldException();
        }
        
        boolean result;
        // Try to move in the chosen direction
        result = move(sideDirection, movableSpriteBounds, externalBounds, speed);
        // If it's not possible, try to move in the opposite one
        if (!result) {
            sideDirection = 1 - sideDirection;
            move(sideDirection, movableSpriteBounds, externalBounds, speed);
        }

        return directionToReturn;
    }

    private boolean move(int direction, Rectangle movableSpriteBounds, Rectangle externalBounds, int speed) {
        if (direction == 0) {
            // move left, if possible
            if (movableSpriteBounds.getX() - speed < externalBounds.getX()) {
                directionToReturn = null;
                return false;
            } else {
                directionToReturn = new Direction(-1, 0);
                return true;
            }
        } else {
            //move right, if possible
            if (movableSpriteBounds.getX() + movableSpriteBounds.getWidth() + speed > externalBounds.getX() + externalBounds.getWidth()) {
                directionToReturn = null;
                return false;
            } else {
                directionToReturn = new Direction(1, 0);
                return true;
            }
        }
    }

}
