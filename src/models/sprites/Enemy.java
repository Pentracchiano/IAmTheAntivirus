/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;


import java.awt.Image;
import static java.lang.Math.ceil;

/**
 * Enemy is a Sprite that can attack the base.
 * The player should kill the enemies in order to defend the base.
 * 
 * @author ccarratu
 */
public abstract class Enemy extends Sprite {
    private final int BASE_ATTACK;
    
    private final int ATTACK;
    private final int LEVEL;
    
    private int difficulty;
    
    private final double ATTACK_MULTIPLIER = 0.3; 
   
    /**
     * Creates a new enemy with the specified position, attributes, level and image.
     * 
     * @param x The x position of the enemy.
     * @param y The y position of the enemy.
     * @param image The image of the enemy.
     * @param level The level of the enemy. The level determines the difficult, the value and the attributes of the instance.
     * @param baseAttack The initial attack of the enemy. The effective attack is proportional to this parameter and to the level.
     */
    public Enemy(int x, int y, Image image, int level, int baseAttack) {
        super(x, y, image);
        
        this.BASE_ATTACK = baseAttack;
        this.LEVEL = level;
        this.ATTACK = BASE_ATTACK + (int) (this.BASE_ATTACK * (this.LEVEL-1) * this.ATTACK_MULTIPLIER);
        initEnemy();
    }
    
    /**
     * 
     * @param x The x position of the enemy.
     * @param y The y position of the enemy.
     * @param imagePath The path of the image of the enemy.
     * @param level The level of the enemy. The level determines the difficult, the value and the attributes of the instance.
     * @param baseAttack The initial attack of the enemy. The effective attack is proportional to this parameter and to the level.
     */
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
