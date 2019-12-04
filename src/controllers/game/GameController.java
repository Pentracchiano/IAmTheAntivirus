package controllers.game;

import java.awt.Rectangle;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import models.*;
import views.game.GameView;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Gerardo
 */
public class GameController extends Controller implements Runnable {

    private final Keyboard keyboard;
    private final Base base;
    // it's final because we only add or remove viruses, but we don't change the referenced object
    // it containts the alive(it's visible on the user interface and its health is greater then 0) virus in the current wave
    private final Collection<Virus> viruses;
    
    private final Thread gameLoop;
    
    private final int DELAY_MS = 10; // in milliseconds

    /*inGame is a boolean that we use to indicate if we have to stop the game evolution
      thread or not, so it indicates if the match is going on or it is over.
     */
    private boolean inGame;

    public GameController(GameView view) {
        super(view);
        
        this.keyboard = view.getKeyboard();
        this.base = view.getBase();
        this.viruses = view.getViruses();
        
        this.gameLoop = new Thread(this);
        
        this.inGame = false;
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        int timeCount = 0; // counts the number of cycles
        
        Wave wave = buildWave();
        
        int totalViruses = wave.getSize();

        while (inGame) {
            beforeTime = System.currentTimeMillis(); 
            
            if(wave.getSize() == 0 && viruses.isEmpty()) {
                inGame = false;
            }

            // spawn and move
            updateViruses(wave, timeCount);
            checkBaseCollision();
            
            view.update();
            
            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = DELAY_MS - timeDiff;

            /*we use this if to handle the case in which the execution time of
            updateEnemies(), checkBaseCollision() and update() methods is larger then the DELAY.
             */
            if (sleep < 0) {
                // 2 is a random chose.
                sleep = 2;
            }
            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                throw new NotImplementedException();
            }
            
            timeCount++;
        }

    }
    
    // deve essere eliminato, faremo una classe wave manager
    private Wave buildWave() {
        Random r = new Random();
                
        int virusType;
        int delay;
        Virus virus = null;
        
        Wave wave = new Wave();
        
        int x = r.nextInt(view.getWidth() - 50);
                
        for(int i = 0; i < 20; i++) {
            
            virusType = r.nextInt(2);
            if (virusType == 0) {
                virus = new Worm(x, view.getHeight());
            } else if(virusType == 1) {
                virus = new Trojan(x, view.getHeight());
            }
            
            delay = r.nextInt(10) + i*10;
            
            wave.addElement(new VirusToSpawn(virus, delay));
        }
                
        return wave;
    }

    private void updateViruses(Wave wave, int timeCount) {
        if(wave.getSize() > 0 ) {
            VirusToSpawn vts = wave.getCurrentElement();
            
            if(vts.getDelay() <= timeCount) {
                viruses.add(wave.removeCurrentElement().getVirus());
            }
        }
        
        viruses.forEach((v) -> {
            v.move();
        });
    }

    public void checkBaseCollision() {
        Rectangle baseBounds = base.getBounds();
        Virus virus = null;

        for (Iterator<Virus> it = viruses.iterator(); it.hasNext(); virus = it.next()) {

            Rectangle virusBounds = virus.getBounds();

            if (baseBounds.intersects(virusBounds)) {
                
                /*
                We must define how to represent the condition of the virus that must 
                disappear from the screen. 
                An example is this:
                enemy.setVisible(false);
                */
                base.damage(virus.getAttack());
                
                it.remove();
                
            }
        }
        
        if(base.isInfected()) {
            inGame = false;
        }
        
    }

    private void initListeners() {
        /**
         * A ComponentAdapter is passed to the view, in order to register
         * callbacks for the componentShown and componentHidden events that are
         * called when the view is presented or hidden. We use them to start and
         * stop the thread that controls the game evolution.
         */
        view.addComponentListener(new ComponentAdapter() {
            public void componentShown(ComponentEvent e) {
                GameController.this.inGame = true;
                gameLoop.start();
            }

            public void componentHidden(ComponentEvent e) {
                GameController.this.inGame = false;
            }
        });

        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                // delegate to keyboard or the controller manage the logic (I obtain the key from the keyboard)
                // throw new NotImplementedException();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                // delegate to keyboard or the controller manage the logic (I obtain the key from the keyboard)
                // throw new NotImplementedException();
            }
        });
    }

}
