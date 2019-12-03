/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.awt.Image;

/**
 *
 * @author ccarratu
 */
public abstract class Virus extends Enemy implements Movable, Damageable {
    protected int hp;
    protected int speed;

    public Virus(int x, int y, Image image, int attack, int hp, int speed) {
        super(x, y, image, attack);
        
        this.hp = hp;
        this.speed = speed;
    }
    
    public int getHp() {
        return hp;
    }
    
    public int getSpeed() {
        return speed;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public void move() {
        // at the moment, any virus simply advance towards the base following a line
        y -= speed;
        
        if(y < 0) {
            y = 0;
        }
    }
    
    @Override
    public void damage(int damage) {
        // maybe it's better to set hp = 0 if hp < 0
        hp -= damage;
    }
}
