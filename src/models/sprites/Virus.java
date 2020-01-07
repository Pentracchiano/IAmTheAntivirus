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
import models.GameStatus;

/**
 * Virus is an Enemy that can attack, move and being damaged (it implements the Movable and Damageable interfaces)
 * A virus is one of the entity that the player has to kill in order to defend the base.
 * 
 * @author ccarratu
 */
public abstract class Virus extends Enemy implements Movable, Damageable {
    private DirectionGenerator directionGenerator;
    
    private final int BASE_TOTAL_HEALTH;
    private final int BASE_SPEED;
    
    private final int TOTAL_HEALTH;
    
    private int currentHealth; // it's not final because it changes subsequently to damage
    private int speed; // it's not final because we could introduce powerups that slow down viruses
    
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
     */
    public Virus(int x, int y, Image image, int baseTotalHealth, int baseSpeed, int baseAttack, int level, DirectionGenerator directionGenerator) {
        super(x, y, image, level, baseAttack);
        this.BASE_TOTAL_HEALTH = baseTotalHealth;
        this.BASE_SPEED = baseSpeed;
        this.TOTAL_HEALTH = BASE_TOTAL_HEALTH + (int) (this.BASE_TOTAL_HEALTH * (getLevel() - 1) * GameStatus.VIRUS_HEALTH_MULTIPLIER);
        
        initVirus(directionGenerator);
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
     */
    public Virus(int x, int y, String imagePath, int baseTotalHealth, int baseSpeed, int baseAttack, int level, DirectionGenerator directionGenerator) {
        super(x, y, imagePath, level, baseAttack);
        this.BASE_TOTAL_HEALTH = baseTotalHealth;
        this.BASE_SPEED = baseSpeed;
        this.TOTAL_HEALTH = BASE_TOTAL_HEALTH + (int) (this.BASE_TOTAL_HEALTH * (getLevel() - 1) * GameStatus.VIRUS_HEALTH_MULTIPLIER);

        initVirus(directionGenerator);
    }

    private void initVirus(DirectionGenerator directionGenerator) {
        this.directionGenerator = directionGenerator;
        this.currentHealth = TOTAL_HEALTH;
        
        // for evaluating each attribute, like the speed, we multiply the BASE_SPEED by the level and by a constant, that is different for each attribute.
        // this.speed = BASE_SPEED + (int) (this.BASE_SPEED * ( getLevel() - 1) * SPEED_MULTIPLIER);
        
        // we decide to not increase the speed
        this.speed = BASE_SPEED;
        
        this.setDifficulty((int) ceil(this.getAttack() * (1 / GameStatus.ENEMY_ATTACK_MULTIPLIER) + this.speed * (1 / GameStatus.VIRUS_SPEED_MULTIPLIER) + this.TOTAL_HEALTH * (1 / GameStatus.VIRUS_HEALTH_MULTIPLIER)));
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

    @Override
    public void move(){
        throw new UnsupportedOperationException("A virus always moves within the limits of the field");
    }
    
    // Parameters externalBounds represents the boundaries of the space in which
    // the sprite moves.
    @Override
    public void move(Rectangle externalBounds){
        Direction d = directionGenerator.getDirection(this.getBounds(), externalBounds, this.speed);
        
        if (d != null){
            setX(getX() + speed * d.getX());
            setY(getY() + speed * d.getY());
        }
    }

    @Override
    public void damage(int damage) {
        // maybe it's better to set currentHealth = 0 if currentHealth < 0
        this.currentHealth -= damage;
    }

    @Override
    public String toString() {
        return super.toString() + ", BASE_TOTAL_HEALTH=" + BASE_TOTAL_HEALTH + ", BASE_SPEED=" + BASE_SPEED + ", HEALTH_MULTIPLIER=" + ", TOTAL_HEALTH=" + TOTAL_HEALTH + ", currentHealth=" + currentHealth + ", speed=" + speed;
    }

}
