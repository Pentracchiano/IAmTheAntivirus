/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;


import java.awt.Image;
import static java.lang.Math.ceil;

/**
 * 
 * @author ccarratu
 */
public abstract class Enemy extends Sprite {
    private final int BASE_ATTACK;
    
    private final int ATTACK;
    private int difficulty;
    private final int LEVEL;
    
    private final double ATTACK_MULTIPLIER = 0.3; 
   
    public Enemy(int x, int y, Image image, int level, int baseAttack) {
        super(x, y, image);
        
        this.BASE_ATTACK = baseAttack;
        this.LEVEL = level;
        this.ATTACK = BASE_ATTACK + (int) (this.BASE_ATTACK * (this.LEVEL-1) * this.ATTACK_MULTIPLIER);
        initEnemy();
    }
    
    public Enemy(int x, int y, String imagePath, int level, int baseAttack) {
        super(x, y, imagePath);
        
        this.BASE_ATTACK = baseAttack;
        this.LEVEL = level;
        this.ATTACK = BASE_ATTACK + (int) (this.BASE_ATTACK * (this.LEVEL-1) * this.ATTACK_MULTIPLIER);
        initEnemy();
    }
    
    private void initEnemy() {
        this.difficulty =  (int) ceil(this.ATTACK * (1 / this.ATTACK_MULTIPLIER));
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getAttack() {
        return ATTACK;
    }

    public int getBaseAttack() {
        return BASE_ATTACK;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public int getLevel() {
        return LEVEL;
    }

    public double getAttackMultiplier() {
        return ATTACK_MULTIPLIER;
    }

    @Override
    public String toString() {
        return super.toString() + ", attack=" + ATTACK + ", baseAttack=" + BASE_ATTACK + ", difficulty=" + difficulty + ", level=" + LEVEL + ", levelMultiplier=" + ATTACK_MULTIPLIER;
    }
}
