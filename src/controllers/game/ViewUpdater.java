/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import java.util.logging.Level;
import java.util.logging.Logger;
import views.View;

/**
 *
 * @author LordCatello
 */
public class ViewUpdater implements Runnable {
    private final View VIEW;
    private final int DELAY_MS;

    public ViewUpdater(View view, int delay_ms) {
        this.VIEW = view;
        this.DELAY_MS = delay_ms;
    }

    @Override
    public void run() {
        long beforeTimeMs, timeDifferenceMs, sleepTimeMs;
        while(true) {
            beforeTimeMs = System.currentTimeMillis();
            
            VIEW.update();
            
            // sleep
            timeDifferenceMs = System.currentTimeMillis() - beforeTimeMs;
            sleepTimeMs = DELAY_MS - timeDifferenceMs;
            
            // we use this if to handle the case in which the execution time of
            // updateEnemies(), checkBaseCollision() and update() methods is larger then the DELAY.
            if(sleepTimeMs < 0) {
                sleepTimeMs = 2;
            }
            
            try {
                Thread.sleep(sleepTimeMs);
            } catch (InterruptedException ex) {
                Logger.getLogger(ViewUpdater.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
