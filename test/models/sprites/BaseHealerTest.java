/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import behaviors.SideDirectionGenerator;
import java.awt.Rectangle;
import models.GameStatus;
import models.sprites.behaviors.Curable;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francesco
 */
public class BaseHealerTest {
    
    private BaseHealer instance;
    
    public BaseHealerTest() {
    }
    
    @Before
    public void setUp() {
       this.instance = new BaseHealer(0, 0, new SideDirectionGenerator(), 2);
    }
    
    /**
     * Test of heal method, of class BaseHealer.
     * Checks for correct computation of the new health value.
     */
    @Test
    public void testHeal() {
        
       int maxHealth = 200;
       int multiplier = 10;
       int waveNumber = 12;
       
       GameStatus gameStatus = GameStatus.getInstance();
       gameStatus.setCurrentWaveNumber(12);
    
       instance.setMaxHealth(maxHealth);
       
       Curable curable = new Curable(){
           private int health = 20;
           
           @Override
           public int getCurrentHealth() {
               return this.health;
           }

           @Override
           public void setCurrentHealth(int health) {
               this.health = health;
           }
       };
       
       int oldHealth = curable.getCurrentHealth();
       
       instance.heal(curable);
       
       assertTrue(curable.getCurrentHealth() == (waveNumber * multiplier + oldHealth));
    }
    
    /**
     * Test of heal method, of class BaseHealer.
     * Checks for the respect of the maximum health of the Curable element.
     */
    @Test
    public void testHeal1() {
        
       int maxHealth = 120;
       int multiplier = 10;
       int waveNumber = 12;
       
       GameStatus gameStatus = GameStatus.getInstance();
       gameStatus.setCurrentWaveNumber(12);
    
       instance.setMaxHealth(maxHealth);
       
       Curable curable = new Curable(){
           private int health = 20;
           
           @Override
           public int getCurrentHealth() {
               return this.health;
           }

           @Override
           public void setCurrentHealth(int health) {
               this.health = health;
           }
       };
       
       int oldHealth = curable.getCurrentHealth();
       
       instance.heal(curable);
       
       assertTrue(curable.getCurrentHealth() == maxHealth);
    }

    /**
     * Test of move method, of class BaseHealer.
     * This tests the case of a null direction returned by the getDirection()
     * method. There's no need to check that the returned direction, if not
     * null, produces a movement that keeps the healer within the boundaries,
     * because the direction returned by the method, if not null, is always
     * legal, so it's up to the tests of that class to verify this situation.
     */
    @Test
    public void testMove_Rectangle() {
        Rectangle boundaries = new Rectangle(0, 0, 48, 48);
        
        this.instance.move(boundaries);
        
        assertTrue(instance.getX() == 0 && instance.getY() == 0);
    }
    
}
