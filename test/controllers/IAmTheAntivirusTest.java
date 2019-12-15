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
    private IAmTheAntivirus instance;
    
    @Before
    public void setUp() {
        instance = IAmTheAntivirus.getGameInstance();
    }

    /**
     * Test of getGameInstance method, of class IAmTheAntivirus.
     */
    @Test
    public void testGetGameInstance() {
        System.out.println("getGameInstance");
        assertEquals(instance, IAmTheAntivirus.getGameInstance());
    }

}
