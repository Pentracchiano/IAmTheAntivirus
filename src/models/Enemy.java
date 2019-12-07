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
public abstract class Enemy extends Sprite {
    private int attack;
    
    public Enemy(int x, int y, Image image, int attack) {
        super(x, y, image);
        
        initEnemy(attack);
    }
    
    public Enemy(int x, int y, String imagePath, int attack) {
        super(x, y, imagePath);
        
        initEnemy(attack);
    }
    
    private void initEnemy(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public String toString() {
        return super.toString() + ", attack=" + attack;
    }

    
    
}
