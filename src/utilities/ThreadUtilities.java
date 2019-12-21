/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utilities;

/**
 *
 * @author LordCatello
 */
public class ThreadUtilities {
    
    public static void sleep(long time_ms) {
        try {
            Thread.sleep(time_ms);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
    
}
