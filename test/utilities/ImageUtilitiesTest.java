/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Image;
import javax.swing.ImageIcon;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davide Cafaro
 */
public class ImageUtilitiesTest {
    
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of loadImageFromPath method, of class ImageUtilities.
     */
    @Test
    public void testLoadImageFromPath() {
        System.out.println("loadImageFromPath");
        String path = "";
        Image expected = new ImageIcon(path).getImage();
        Image result = ImageUtilities.loadImageFromPath(path);
        assertEquals(expected,result);
    }
    
}
