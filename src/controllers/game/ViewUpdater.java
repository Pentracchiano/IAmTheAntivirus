/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.game;

import utilities.ThreadUtilities;
import views.View;

/**
 * ViewUpdater is the class responsible for triggering in a loop the update of a view.
 * 
 * @author LordCatello
 */
public class ViewUpdater implements Runnable {
    private final View VIEW;
    private final int DELAY_MS;

    /**
     * Creates a ViewUpdater with the specified view to trigger every delay milliseconds.
     * 
     * @param view The view to trigger
     * @param delay_ms The time, in milliseconds, that elaps between two consecutive triggers.
     */
    public ViewUpdater(View view, int delay_ms) {
        this.VIEW = view;
        this.DELAY_MS = delay_ms;
    }

    /**
     * This method trigger the view every delay milliseconds.
     * 
     */
    @Override
    public void run() {
        long beforeTimeMs, timeDifferenceMs, sleepTimeMs;

        while(!Thread.currentThread().isInterrupted()) { 
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
            
            ThreadUtilities.sleep(sleepTimeMs);
        }
    }
    
}
