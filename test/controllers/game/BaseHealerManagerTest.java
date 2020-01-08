/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.awt.Rectangle;
import models.GameStatus;
import models.sprites.Base;
import models.sprites.BaseHealer;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francesco
 */
public class BaseHealerManagerTest {

    private BaseHealerManager instance;

    public BaseHealerManagerTest() {
    }

    @Before
    public void setUp() {
        Base base = new Base(0, 0, 50);
        this.instance = new BaseHealerManager(base);
    }

    /**
     * Test of getBaseHealer method, of class BaseHealerManager.
     * Checks if the spawning point of the created BaseHealer respects the 
     * provided boundaries. Cannot check for the respect of the game rules in 
     * setting the speed, because speed attribute in BaseHealer is private and
     * has no getter.
     */
    @Test
    public void testGetBaseHealer() {

        GameStatus gameStatus = GameStatus.getInstance();
        gameStatus.setCurrentWaveNumber(2);

        Rectangle externalBoundaries = new Rectangle(0, 0, 120, 120);

        BaseHealer retVal = instance.getBaseHealer(externalBoundaries);

        assertTrue(retVal.getY() == (externalBoundaries.getY() + externalBoundaries.getHeight()));
        assertTrue(retVal.getX() >= externalBoundaries.getX()
                && retVal.getX() <= (externalBoundaries.getX() + externalBoundaries.getWidth()));
    }

}
