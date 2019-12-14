/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.sprites;

import models.sprites.Keyboard.Key;
import models.sprites.exceptions.KeyNotFoundException;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Davide Cafaro
 */
public class KeyboardTest {
    private Keyboard keyboard;

    @Before
    public void setUp() {
        keyboard = new Keyboard(160,300);
    }

    /**
     * Test if the press method throws the KeyNotFoundException when an illegal
     * key is pressed
     */
    @Test(expected= KeyNotFoundException.class)
    public void testPressedNotSupportedKey() throws Exception {
        System.out.println("Not supported key pressed");
        char id = '@';
        keyboard.press(id);
    }

     /**
     * Test if the press method change the states of the appropriate keys.
     */
    @Test
    public void testPress() throws Exception {
        System.out.println("key pressed");
        char CHARACTERS[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        
        for(char id : CHARACTERS){
        Key expected=keyboard.getKey(id);
        keyboard.press(id);
        assertEquals("The states maste be equals", Keyboard.State.PRESSED,expected.getState());
        }
        
    }
    
    
       /**
     * Test if GetKey() throws the KeyNotFoundException when an illegale key is requested.
     */
    @Test(expected= KeyNotFoundException.class)
    public void testGetIllegalKey() throws Exception {
        System.out.println("get illegal key");
        char keyCode = '@';
        keyboard.getKey(keyCode);
    }
    
    /**
     * Test of getKey method, of class Keyboard.
     */
    @Test
    public void testGetKey() throws Exception {
        System.out.println("getKey");
        char CHARACTERS[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        
        for(char id : CHARACTERS){
        assertEquals("The keys must be equals", id,keyboard.getKey(id).getId());
        }
    }

    
    /**
     * Test of release method, of class Keyboard.
     */
    @Test
    public void testRelease() throws Exception {
        System.out.println("release");
        char CHARACTERS[] = {'1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'Q', 'W', 'E', 'R', 'T', 'Y', 'U', 'I', 'O', 'P', 'A', 'S', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'Z', 'X', 'C', 'V', 'B', 'N', 'M'};
        for(char id : CHARACTERS){
        Key expected=keyboard.getKey(id);
        keyboard.release(id);
        assertEquals("The states maste be equals", Keyboard.State.RELEASED,expected.getState());
        }
    }
    
}
