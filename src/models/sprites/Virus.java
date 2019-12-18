/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import behaviors.Direction;
import behaviors.DirectionGenerator;
import models.sprites.behaviors.Movable;
import models.sprites.behaviors.Damageable;
import java.awt.Image;
import java.awt.Rectangle;
import static java.lang.Math.ceil;

/**
 * Virus is an Enemy that can attack, move and being damaged (it implements the Movable and Damageable interfaces)
 * A virus is one of the entity that the player has to kill in order to defend the base.
 * 
 * @author ccarratu
 */
public abstract class Virus extends Enemy implements Movable, Damageable {
    private DirectionGenerator directionGenerator;
    private Rectangle externalBounds;
    
    private final int BASE_TOTAL_HEALTH;
    private final int BASE_SPEED;
    
    // The multipliers are used to balance the differences between the attributes.
    // For example, a set fo standard value for the attributes is:
    // health = 25, attack = 5, speed = 1
    // all the attributes have the same importance, so, when the difficulty, the value and the growth of the attributes are evaluated, the values have to be balanced.
    // So for the difficulty, each attribute si multiplied for the inverse of the value of the corresponding multiplier.
    // For example the speed is multiplied by 5 and the health by 2
    // In this way the attributes are more balanced.
    private final double HEALTH_MULTIPLIER = 0.5;
    private final double SPEED_MULTIPLIER = 0.2;
    
    private final int TOTAL_HEALTH;
    
    private int currentHealth; // it's not final because it changes subsequently to damage
    private int speed; // it's not final because we could introduce powerups that slow down viruses
    private int bitcoinsValue; // the score amount the player gets when this virus is killed
    public static final int BASE_BITCOINS_VALUE = 1;
    
    /**
     * Creates a new virus with the specified position, attributes, level and image.
     * 
     * @param x The x position of the virus.
     * @param y The y position of the virus.
     * @param image The image of the virus.
     * @param baseTotalHealth The initial total health of the virus. The effective total health is proportional to this parameter and to the level.
     * @param baseSpeed The initial speed of the virus. The effective speed is proportional to this parameter and to the level.
     * @param baseAttack The initial attack of the virus. The effective attack is proportional to this parameter and to the level.
     * @param level The level of the virus. The level determines the difficult, the value and the attributes of the instance.
     * @param directionGenerator The DirectionGenerator determines the path followed by the virus.
     * @param externalBounds The Bounds of the Sprite space where the virus can move.
     */
    public Virus(int x, int y, Image image, int baseTotalHealth, int baseSpeed, int baseAttack, int level, DirectionGenerator directionGenerator, Rectangle externalBounds) {
        super(x, y, image, level, baseAttack);
        this.BASE_TOTAL_HEALTH = baseTotalHealth;
        this.BASE_SPEED = baseSpeed;
        this.TOTAL_HEALTH = BASE_TOTAL_HEALTH + (int) (this.BASE_TOTAL_HEALTH * (getLevel() - 1) * HEALTH_MULTIPLIER);
        
        initVirus(directionGenerator, externalBounds);
    }
    
    /**
     * Creates a new virus with the specified position, attribures, level and image.The image is obtained using the path of the image passed as parameter.
     * 
     * 
     * @param x The x position of the virus.
     * @param y The y position of the virus.
     * @param imagePath The path of the image of the virus.
     * @param baseTotalHealth The initial total health of the virus. The effective total health is proportional to this parameter and to the level.
     * @param baseSpeed The initial speed of the virus. The effective speed is proportional to this parameter and to the level.
     * @param baseAttack The initial attack of the virus. The effective attack is proportional to this parameter and to the level.
     * @param level The level of the virus. The level determines the difficult, the value and the attributes of the instance.
     * @param directionGenerator The DirectionGenerator determines the path followed by the virus.
     * @param externalBounds The Bounds of the Sprite space where the virus can move.
     */
    public Virus(int x, int y, String imagePath, int baseTotalHealth, int baseSpeed, int baseAttack, int level, DirectionGenerator directionGenerator, Rectangle externalBounds) {
        super(x, y, imagePath, level, baseAttack);
        this.BASE_TOTAL_HEALTH = baseTotalHealth;
        this.BASE_SPEED = baseSpeed;
        this.TOTAL_HEALTH = BASE_TOTAL_HEALTH + (int) (this.BASE_TOTAL_HEALTH * (getLevel() - 1) * HEALTH_MULTIPLIER);

        initVirus(directionGenerator, externalBounds);
    }

    private void initVirus(DirectionGenerator directionGenerator, Rectangle externalBounds) {
        this.directionGenerator = directionGenerator;
        this.externalBounds = externalBounds;
        this.currentHealth = TOTAL_HEALTH;
        
        // for evaluating each attribute, like the speed, we multiply the BASE_SPEED by the level and by a constant, that is different for each attribute.
        this.speed = BASE_SPEED + (int) (this.BASE_SPEED * ( getLevel() - 1) * SPEED_MULTIPLIER);
        this.setDifficulty((int) ceil(this.getAttack() * (1 / this.getAttackMultiplier()) + this.speed * (1 / this.SPEED_MULTIPLIER) + this.TOTAL_HEALTH * (1 / this.HEALTH_MULTIPLIER)));
    }
    
    public int getTOTAL_HEALTH() {
        return TOTAL_HEALTH;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    // maybe directionGenerator can be final, but in a general case, if I want,
    // I can change the movement style of the virus at run time multiple time after the creation of the istance.
    public void setDirectionGenerator(DirectionGenerator directionGenerator) {
        this.directionGenerator = directionGenerator;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }

    /**
     * This method MUST be overridden by subclasses.
     * 
     * @return the base (class-level) score given to the player that kills that virus.
     */
    public abstract int getBaseBitcoinsValue();

    // maybe consider not exposing it as a setter, but calculating it through "level"
    public void setBitcoinsValue(int bitcoinsValue) {
        this.bitcoinsValue = bitcoinsValue;
    }

    public int getBitcoinsValue() {
        return bitcoinsValue;
    }

    @Override
    public void move() {
        Direction d = directionGenerator.getDirection(this.getBounds(), externalBounds);
        
        setX(getX() + speed * d.getX());
        setY(getY() + speed * d.getY());
        
        // the following checks can be avoided because
        // the getDirection function should provide always an ammisible direction
        // because the externalBounds are passed as parameters
        if(getX() < 0) {
            setY(0);
        }

        if (getY() < 0) {
            setY(0);
        }
    }

    @Override
    public void damage(int damage) {
        // maybe it's better to set currentHealth = 0 if currentHealth < 0
        this.currentHealth -= damage;
    }

    @Override
    public String toString() {
        return super.toString() + ", BASE_TOTAL_HEALTH=" + BASE_TOTAL_HEALTH + ", BASE_SPEED=" + BASE_SPEED + ", HEALTH_MULTIPLIER=" + HEALTH_MULTIPLIER + ", SPEED_MULTIPLIER=" + SPEED_MULTIPLIER + ", TOTAL_HEALTH=" + TOTAL_HEALTH + ", currentHealth=" + currentHealth + ", speed=" + speed + ", bitcoinsValue=" + bitcoinsValue;
    }

}
