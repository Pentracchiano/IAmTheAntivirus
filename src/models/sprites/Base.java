/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import models.sprites.behaviors.Damageable;

/**
 *
 * @author Gerardo
 */
public class Base extends Sprite implements Damageable{
    private int totalHealth;
    private int currentHealth;
    private static final String DEFAULT_IMAGE_PATH = "src/resources/base.png";

    public Base(int x,int y, int totalHealth) {
        super(x,y, DEFAULT_IMAGE_PATH);
        
        initBase(totalHealth);
    }
    
    private void initBase(int totalHealth) {
        this.totalHealth = totalHealth;
        this.currentHealth = totalHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }
    
    public int getTotalHealth() {
        return totalHealth;
    }

    public void setCurrentHealth(int health) {
        this.currentHealth = health;
    }
    
    public void setTotalHealth(int health) {
        this.totalHealth = health;
    }
    
    @Override
    public void damage(int dmg){
        this.currentHealth -= dmg;
        
        if(this.currentHealth < 0) {
            this.currentHealth = 0;
        }
    }
    
    public boolean isInfected(){
        return this.currentHealth == 0;
    }

    @Override
    public String toString() {
        return "Base: " + super.toString() + ", totalHealth: " + totalHealth + ", currentHealth: " + currentHealth;
    }
    
    
    
}
