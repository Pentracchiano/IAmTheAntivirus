/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package behaviors;

import java.awt.Rectangle;
import java.util.Random;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Francesco
 */
public class ObliqueDirectionGeneratorTest {

    ObliqueDirectionGenerator instance;

    public ObliqueDirectionGeneratorTest() {
    }

    @Before
    public void setUp() {
        instance = new ObliqueDirectionGenerator();
    }

    /**
     * Test of getDirection method, of class ObliqueDirectionGenerator.
     */
    @Test
    public void testGetDirection() {
        System.out.println("ObliqueDirectionGenerator - getDirection");
        Random r = new Random();
        int x = r.nextInt(100);
        int y = r.nextInt(100);
        Rectangle movableSpriteBounds = new Rectangle(120, 120);
        Rectangle externalBounds = new Rectangle(240, 240);
        int speed = 1;
        for (int i = 0; i < 1000; i++) {
            Direction result = instance.getDirection(movableSpriteBounds, externalBounds, speed);
            double newX = x + speed * result.getX();
            double newY = y + speed * result.getY();
            boolean cond = (newX + movableSpriteBounds.getWidth()) < (externalBounds.getX() + externalBounds.getWidth())
                    && newX > (externalBounds.getX())
                    && (newY + movableSpriteBounds.getHeight()) < (externalBounds.getY() + externalBounds.getHeight())
                    && newY > (externalBounds.getY());
            assertTrue(cond);
        }
    }

    @Test(expected = MovableGreaterThanFieldException.class)
    public void testGetDirectionExc() {
        System.out.println("ObliqueDirectionGenerator - getDirectionExc");
        Rectangle movableSpriteBounds = new Rectangle(120, 120);
        Rectangle externalBounds = new Rectangle(119, 119);
        int speed = 1;
        Direction result = instance.getDirection(movableSpriteBounds, externalBounds, speed);
    }

}
