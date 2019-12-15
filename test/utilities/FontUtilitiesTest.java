/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

import java.awt.Font;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davide Cafaro
 */
public class FontUtilitiesTest {
    
    public FontUtilitiesTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of registerFont method, of class FontUtilities.
     */
    @Test
    public void testRegisterFont() {
        System.out.println("registerFont");
        String fontPath = "";
        Font expResult = null;
        Font result = FontUtilities.registerFont(fontPath);
        assertEquals(expResult, result);
    }
    
}
