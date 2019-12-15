/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import models.sprites.behaviors.Movable;
import models.sprites.behaviors.Damageable;
import java.awt.Image;
import static java.lang.Math.ceil;

/**
 *
 * @author ccarratu
 */
public abstract class Virus extends Enemy implements Movable, Damageable {
    private final int BASE_TOTAL_HEALTH;
    private final int BASE_SPEED;
    
    private final double HEALTH_MULTIPLIER = 0.5;
    private final double SPEED_MULTIPLIER = 0.2;
    
    private final int TOTAL_HEALTH;
    private int currentHealth; //it's not final because it changes subsequently to damage
    private int speed; //it's not final because we could introduce powerups that slow down viruses
    private int bitcoinsValue; // the score amount the player gets when this virus is killed
    public static final int BASE_BITCOINS_VALUE = 1;
    
    public Virus(int x, int y, Image image, int baseTotalHealth, int baseSpeed, int baseAttack, int level) {
        super(x, y, image, level, baseAttack);
        this.BASE_TOTAL_HEALTH = baseTotalHealth;
        this.BASE_SPEED = baseSpeed;
        this.TOTAL_HEALTH = BASE_TOTAL_HEALTH + (int) (this.BASE_TOTAL_HEALTH * (getLevel() - 1) * HEALTH_MULTIPLIER);
        
        initVirus();
    }

    public Virus(int x, int y, String imagePath, int baseTotalHealth, int baseSpeed, int baseAttack, int level) {
        super(x, y, imagePath, level, baseAttack);
        this.BASE_TOTAL_HEALTH = baseTotalHealth;
        this.BASE_SPEED = baseSpeed;
        this.TOTAL_HEALTH = BASE_TOTAL_HEALTH + (int) (this.BASE_TOTAL_HEALTH * (getLevel() - 1) * HEALTH_MULTIPLIER);

        initVirus();
    }

    private void initVirus() {
        this.currentHealth = TOTAL_HEALTH;
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
        // at the moment, any virus simply advance towards the base following a line
        setY(getY() - speed);

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
