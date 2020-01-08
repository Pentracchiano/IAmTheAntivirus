/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Gerardo
 */
public class ShellTest {
    
    private Shell shell;
    
    @Before
    public void setUp() {
        shell = new Shell();
    }
    
    @After
    public void tearDown() {
        shell = null;
    }

    /**
     * Test of launchCommand method, of class Shell.
     */
    @Test
    public void testLaunchCommand() {
        shell.setText("FIREWALL");
        shell.launchCommand();
        assertEquals("The shell is not empty", shell.getText(), "c:\\");
    }


    /**
     * Test of digitcommands method, of class Shell.
     */
    @Test
    public void testDigitcommands() {
       shell.digitcommands('F');
       assertEquals("The shell does not read the input properly", shell.getText(), "c:\\F");
       shell.digitcommands((char) 8);
       assertEquals("The shell does not delete characters", shell.getText(), "c:\\");
       
    }

    /**
     * Test of setText method, of class Shell.
     */
    @Test
    public void testSetText() {
       String s = new String("Try");
       shell.setText(s);
       assertEquals("The shell is not empty",shell.getText(), "c:\\Try");
    }


}
