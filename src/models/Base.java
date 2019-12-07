/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Gerardo
 */
public class Base extends Sprite implements Damageable{
    private int health;
    private static final String DEFAULT_IMAGE_PATH = "src/resources/base.png";

    public Base(int x,int y, int health) {
        super(x,y, DEFAULT_IMAGE_PATH);
        
        initBase(health);
    }
    
    private void initBase(int health) {
        this.health = health;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
    
    @Override
    public void damage(int dmg){
        this.health -= dmg;
        
        if(this.health < 0) {
            this.health = 0;
        }
    }
    
    public boolean isInfected(){
        return this.health == 0;
    }

    @Override
    public String toString() {
        return "Base: " + super.toString() + ", health";
    }
    
    
    
}
