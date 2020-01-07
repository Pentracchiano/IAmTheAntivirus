/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import behaviors.Direction;
import behaviors.DirectionGenerator;
import java.awt.Rectangle;
import models.GameStatus;
import models.sprites.behaviors.Healer;
import models.sprites.behaviors.Movable;

/**
 * An {@link Healer} that can restore the health of the Base.
 *
 * @author Francesco
 */

/*
* It doesn't implement Damageable, even if it can be killed, because, in the
* current implementation, the first hit kills it, so, in a way, it isn't
* damaged, it's directly killed. For this reason, I found unuseful to give it
* an health attribute, that was brought to zero on the first call to damage().
 */
public class BaseHealer extends Sprite implements Healer, Movable {

    private final static String DEFAULT_IMAGE_PATH = "src/resources/healer_heart48.png";
    private final GameStatus GAME_STATUS;
    private final double HEALING_MULTIPLIER = 0.15;
    private final int ATTACK_MULTIPLIER = 5;
    private final DirectionGenerator directionGenerator;
    private final int speed;
    // Maximum health of the base. Needed to avoid to restore it to a value that
    // is greater that what it was at the beginning of the wave.
    private int maxHealth;

    /**
     * @param x X-axis coordinate of the spawning point of the healer.
     * @param y Y-axis coordinate of the spawning point of the healer.
     * @param directionGenerator The {@link DirectionGenerator} that determines
     * how the healer moves across the field.
     * @param speed The speed of the healer.
     */
    public BaseHealer(int x, int y, DirectionGenerator directionGenerator, int speed, int maxHealth) {
        super(x, y, DEFAULT_IMAGE_PATH);
        GAME_STATUS = GameStatus.getInstance();
        this.directionGenerator = directionGenerator;
        this.speed = speed;
        this.maxHealth = maxHealth;
    }

    public int getAttack() {
        return ATTACK_MULTIPLIER * GAME_STATUS.getCurrentWaveNumber();
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
    }

    /**
     * The method that actually heals the base. The healing process consists in
     * adding to the health of the base a quantity that is obtained by
     * multiplying a suited multiplier for the number of the current wave, in
     * order to compute an health increment that makes the healing "useful",
     * according to the difficulty (e.g. enemies' attack power) of the current
     * wave. The health increment applied never causes the health of the base to
     * reach a value greater than its maximum possible value, set at the
     * beginning of the wave.
     *
     * @param toHeal The {@link Base} instance the healer must work on.
     */
    @Override
    public int getHealth() {
        return (int) (this.maxHealth*this.HEALING_MULTIPLIER);
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("The healer must move within the limits of the field");
    }

    /**
     * Moves the healer in the limits of the field, according to the given
     * {@link DirectionGenerator}.
     *
     * @param externalBounds A {@link Rectangle} instance representing the
     * boundaries of the field in which the healer moves.
     */
    @Override
    public void move(Rectangle externalBounds) {
        Direction direction = directionGenerator.getDirection(this.getBounds(), externalBounds, this.speed);

        if (direction != null) {
            setX(getX() + speed * direction.getX());
            setY(getY() + speed * direction.getY());
        }
    }

}
