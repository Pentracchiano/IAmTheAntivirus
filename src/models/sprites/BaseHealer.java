/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import behaviors.Direction;
import behaviors.DirectionGenerator;
import java.awt.Rectangle;
import models.GameStatus;
import models.sprites.behaviors.Curable;
import models.sprites.behaviors.Damageable;
import models.sprites.behaviors.Healer;
import models.sprites.behaviors.Movable;

/**
 *
 * @author Francesco
 */
public class BaseHealer extends Sprite implements Healer, Movable{

    private final static String DEFAULT_IMAGE_PATH = "src/resources/healer_heart48.png";
    private final GameStatus GAME_STATUS;
    private final int HEALING_MULTIPLIER = 10;
    private final int ATTACK_MULTIPLIER = 5;
    private final DirectionGenerator directionGenerator;
    private int speed;
    private int maxHealth;

    public BaseHealer(int x, int y, DirectionGenerator directionGenerator, int speed) {
        super(x, y, DEFAULT_IMAGE_PATH);
        GAME_STATUS = GameStatus.getInstance();
        this.directionGenerator = directionGenerator;
        this.speed = speed;
    }
    
    public int getAttack(){
        return ATTACK_MULTIPLIER * GAME_STATUS.getCurrentWaveNumber();
    }
    
    public int getMaxHealth(){
        return this.maxHealth;
    }
    
    public void setMaxHealth(int maxHealth){
        this.maxHealth = maxHealth;
    }

    @Override
    public void heal(Curable toHeal) {
        int healthIncrement = HEALING_MULTIPLIER * GAME_STATUS.getCurrentWaveNumber();
        int partialHealth = toHeal.getCurrentHealth() + healthIncrement;
        int newHealth = partialHealth < this.maxHealth ? partialHealth : this.maxHealth;
        
        System.out.println("healer - new health: " + newHealth);
        
        toHeal.setCurrentHealth(newHealth);
    }

    @Override
    public void move() {
        throw new UnsupportedOperationException("The healer must move within the limits of the field");
    }

    @Override
    public void move(Rectangle externalBounds) {
        Direction direction = directionGenerator.getDirection(this.getBounds(), externalBounds, HEALING_MULTIPLIER);

        if (direction != null) {
            setX(getX() + speed * direction.getX());
            setY(getY() + speed * direction.getY());
        }
    }

}
