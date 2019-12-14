/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import models.sprites.behaviors.Movable;
import models.sprites.behaviors.Damageable;
import java.awt.Image;

/**
 *
 * @author ccarratu
 */
public abstract class Virus extends Enemy implements Movable, Damageable {

    private int totalHealth;
    private int currentHealth;
    private int speed;
    private int bitcoinsValue; // the score amount the player gets when this virus is killed
    public static final int BASE_BITCOINS_VALUE = 1;
    
    public Virus(int x, int y, Image image, int attack, int totalHealth, int speed) {
        super(x, y, image, attack);

        initVirus(totalHealth, speed);
    }

    public Virus(int x, int y, String imagePath, int attack, int totalHealth, int speed) {
        super(x, y, imagePath, attack);

        initVirus(totalHealth, speed);
    }

    private void initVirus(int totalHealth, int speed) {
        this.totalHealth = totalHealth;
        this.currentHealth = totalHealth;
        this.speed = speed;
    }
    
    public int getTotalHealth() {
        return totalHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getSpeed() {
        return speed;
    }
    
    public void setTotalHealth(int totalHealth) {
        this.totalHealth = totalHealth;        
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
        return super.toString() + ", totalHealth=" + totalHealth + ", currentHealth=" + currentHealth + ", speed=" + speed;
    }

}
