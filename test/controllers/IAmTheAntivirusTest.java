/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

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
public class IAmTheAntivirusTest {
    
    public IAmTheAntivirusTest() {
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
     * Test of getGameInstance method, of class IAmTheAntivirus.
     */
    @Test
    public void testGetGameInstance() {
        System.out.println("getGameInstance");
        IAmTheAntivirus expResult = null;
        IAmTheAntivirus result = IAmTheAntivirus.getGameInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startGame method, of class IAmTheAntivirus.
     */
    @Test
    public void testStartGame() {
        System.out.println("startGame");
        IAmTheAntivirus instance = null;
        instance.startGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayMainMenu method, of class IAmTheAntivirus.
     */
    @Test
    public void testDisplayMainMenu() {
        System.out.println("displayMainMenu");
        IAmTheAntivirus instance = null;
        instance.displayMainMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayGameOverMenu method, of class IAmTheAntivirus.
     */
    @Test
    public void testDisplayGameOverMenu() {
        System.out.println("displayGameOverMenu");
        IAmTheAntivirus instance = null;
        instance.displayGameOverMenu();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of closeGame method, of class IAmTheAntivirus.
     */
    @Test
    public void testCloseGame() {
        System.out.println("closeGame");
        IAmTheAntivirus instance = null;
        instance.closeGame();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class IAmTheAntivirus.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        IAmTheAntivirus.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
