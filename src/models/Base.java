/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import utilities.ImageUtilities;
/**
 *
 * @author Gerardo
 */
public class Base extends Sprite implements Damageable{
    private int health;
    private static final String IMAGE_PATH="src/images/base.png";

    public Base(int x,int y, int health) {
        super(x,y, ImageUtilities.loadImageFromPath(IMAGE_PATH));
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
        this.health-=dmg;
        if(this.health<0)
            this.health=0;
    }
    
    public boolean isInfected(){
        return this.health==0;
    }
    
}
