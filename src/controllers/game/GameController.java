package controllers.game;

import models.sprites.exceptions.KeyNotFoundException;
import models.sprites.Keyboard;
import models.sprites.Virus;
import models.sprites.Base;
import models.GameStatus;
import controllers.IAmTheAntivirus;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import models.sprites.Keyboard.Key;
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

    private final WaveManager waveManager;

    public GameController(GameView view) {
        super(view);

        this.keyboard = view.getKeyboard();
        this.base = view.getBase();
        this.viruses = view.getViruses();
        this.gameStatus = GameStatus.getInstance();
        this.waveManager = new WaveManager();

        this.gameLoop = new Thread(this);

        this.initListeners();
    }

    @Override
    public void run() {
        long beforeTime, timeDiff, sleep;
        int timeCount = 0; // counts the number of cycles

        //Wave wave = buildWave();
        Wave wave = waveManager.getWave(keyboard.getX(), keyboard.getWidth(), view.getHeight());

        gameStatus.setTotalWaveEnemies(wave.getSize());
        gameStatus.setRemainingWaveEnemies(wave.getSize());
        gameStatus.setCurrentWave(1);

        while (gameStatus.isInGame()) {
            beforeTime = System.currentTimeMillis();

            if (wave.getSize() == 0 && viruses.isEmpty()) {
                gameStatus.setInGame(false);
                this.gameEnded();
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
                throw new UnsupportedOperationException();
            }

            timeCount++;

            if (wave.getSize() == 0 && viruses.isEmpty()) {
                /*
                All the enemies have spawned and they've been killed/they've
                reached the base, so the game can continue with the successive
                wave.
                 */
                wave = waveManager.getWave(keyboard.getX(), keyboard.getWidth(), view.getHeight());
                timeCount = 0;
                gameStatus.setTotalWaveEnemies(wave.getSize());
                gameStatus.setRemainingWaveEnemies(wave.getSize());
                gameStatus.setCurrentWave(gameStatus.getCurrentWave() + 1);
                gameStatus.setInWaveTransition(true);
                for(int i=0; i<3000/DELAY_MS; i++){
                    view.update();
                    try {
                        Thread.sleep(DELAY_MS);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                gameStatus.setInWaveTransition(false);
            }
        }
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
            Virus v = it.next();

            if (!v.isAlive()) {
                it.remove();
                gameStatus.addBitcoinsAndScore(v.getBitcoinsValue());
                System.out.println(v.getBitcoinsValue());
                
                gameStatus.setRemainingWaveEnemies(gameStatus.getRemainingWaveEnemies() - 1);
            } else {
                v.move();
            }
        }
    }

    // I cannot pass directly the sprite to this funciont because some times I have to synchronize in order to access the sprites
    private boolean checkCollision(Rectangle hitbox1, Rectangle hitbox2) {
        return hitbox1.intersects(hitbox2);
    }

    private void checkBaseCollision() {
        Rectangle baseBounds = base.getBounds();
        Iterator<Virus> it = viruses.iterator();

        while (it.hasNext()) {
            Virus virus = it.next();

            Rectangle virusBounds = virus.getBounds();

            if (checkCollision(baseBounds, virusBounds)) {
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
            this.gameEnded();
        }
    }

    private void checkKeyCollision(Key key) {
        synchronized (viruses) {
            for (Virus v : viruses) {
                if (checkCollision(key.getBounds(), v.getBounds())) {
                    v.damage(key.getAttack());
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
                try {
                    keyboard.press((char) keyCode);
                    checkKeyCollision(keyboard.getKey((char) keyCode));
                } catch (KeyNotFoundException knfe) {

                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                try {
                    keyboard.release((char) keyCode);
                } catch (KeyNotFoundException knfe) {

                }
            }
        });
    }
    
    private void gameEnded(){
        IAmTheAntivirus.getGameInstance().displayGameOverMenu();
    }

}
