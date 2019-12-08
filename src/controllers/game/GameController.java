package controllers.game;

import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import models.*;
import models.Keyboard.Key;
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

    private final int DELAY_MS = 20; // in milliseconds

    /*inGame is a boolean that we use to indicate if we have to stop the game evolution
      thread or not, so it indicates if the match is going on or it is over.
     */
    private final GameStatus gameStatus;

    public GameController(GameView view) {
        super(view);

        this.keyboard = view.getKeyboard();
        this.base = view.getBase();
        this.viruses = view.getViruses();
        this.gameStatus = GameStatus.getIstance();

        this.gameLoop = new Thread(this);
        
        this.initListeners();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        int timeCount = 0; // counts the number of cycles

        Wave wave = buildWave();

        gameStatus.setTotalWaveEnemies(wave.getSize());
        gameStatus.setRemainingWaveEnemies(wave.getSize());

        while (gameStatus.isInGame()) {
            beforeTime = System.currentTimeMillis();

            if (wave.getSize() == 0 && viruses.isEmpty()) {
                gameStatus.setInGame(false);
            }

            // spawn and move
            synchronized (viruses) {
                updateViruses(wave, timeCount);
                checkBaseCollision();
            }

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


        for(int i = 0; i < 20; i++) {
            
            virusType = r.nextInt(2);
            if (virusType == 0) {
                virus = new Worm(0, view.getHeight());
            } else if (virusType == 1) {
                virus = new Trojan(0, view.getHeight());
            }
            
            int x = r.nextInt(keyboard.getWidth() - virus.getWidth()) + keyboard.getX();
            virus.setX(x);

            delay = r.nextInt(10) + i * 20;

            wave.addElement(new VirusToSpawn(virus, delay));
        }

        return wave;
    }

    private void updateViruses(Wave wave, int timeCount) {
        if (wave.getSize() > 0) {
            VirusToSpawn vts = wave.getCurrentElement();

            if (vts.getTimeToSpawn() <= timeCount) {
                viruses.add(wave.removeCurrentElement().getVirus());
            }
        }
        
        Iterator<Virus> it = viruses.iterator();
        
        while (it.hasNext()) {
           Virus v=it.next();
           
           if(!v.isAlive()){
               it.remove();
               gameStatus.setRemainingWaveEnemies(gameStatus.getRemainingWaveEnemies() - 1);
           } else {
               v.move();
           }
           
            
        }

     
    }

    public void checkBaseCollision() {
        Rectangle baseBounds = base.getBounds();
        
        Iterator<Virus> it = viruses.iterator();

        
        while(it.hasNext()) {
            Virus virus = it.next();
            

            Rectangle virusBounds = virus.getBounds();

            if (baseBounds.intersects(virusBounds)) {

                /*
                We must define how to represent the condition of the virus that must 
                disappear from the screen. 
                An example is this:
                enemy.setVisible(false);
                 */
                synchronized (base) {
                    base.damage(virus.getAttack());
                }
                it.remove();
                gameStatus.setRemainingWaveEnemies(gameStatus.getRemainingWaveEnemies() - 1);

            }
        }

        if (base.isInfected()) {
            gameStatus.setInGame(false);
        }

    }


    private void checkHittenViruses(KeyEvent e) {
        char keyCode = (char) e.getKeyCode();
        Key pressedKey = keyboard.getKey(keyCode);

        synchronized (viruses) {
            for(Virus v : viruses){
            if (pressedKey.getBounds().intersects(v.getBounds())){
                
                v.damage(pressedKey.getAttack());    
            }
           }
        }
    }
    
    

    private void initListeners() { 
        view.addAncestorListener(new AncestorListener() {
            // unfortunately does not exist an AncestorListenerAdapter
            // so I have to implement all the methods
            // even if I need only ancestorAdded and acestorRemoved
            
            // this method is called when the view is added to the frame
            @Override
            public void ancestorAdded(AncestorEvent e) {
                gameStatus.setInGame(true);
                gameLoop.start();
            }
            
            // this event is called when the view is removed by the frame
            @Override
            public void ancestorRemoved(AncestorEvent e) {
                gameStatus.setInGame(false);
            }
            
            @Override
            public void ancestorMoved(AncestorEvent e) {
                
            }

        });
        

        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                keyboard.press((char) keyCode);
                checkHittenViruses(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                keyboard.release((char) keyCode);
            }
        });
    }

}
