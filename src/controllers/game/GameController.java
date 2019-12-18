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
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import models.sprites.Keyboard.Key;
import sounds.BackgroundMusic;
import utilities.ThreadUtilities;
import views.game.GameView;

/**
 *
 * @author Gerardo
 */
public class GameController extends Controller implements Runnable {
    private final Keyboard keyboard;
    private final Base base;

    private final Thread gameLoop;
    private final Thread graphicsUpdater;
    private final Thread backgroundMusicThread;
    private final BackgroundMusic backgroundMusic;

    private final static int GAME_DELAY_MS = 20;
    private final static int GRAPHICS_DELAY_MS = 20;
    private final static int WAVE_DELAY_MS = 3000;

    private final GameStatus gameStatus;

    private final WaveManager waveManager;
    private Wave wave;

    public GameController(GameView view) throws KeyNotFoundException {
        super(view);
        
        this.keyboard = view.getKeyboard();
        this.base = view.getBase();
        this.gameStatus = GameStatus.getInstance();
        int leftLimit = keyboard.getKey('1').getX();
        int rightLimit = keyboard.getKey('P').getX() + keyboard.getKey('P').getWidth() - leftLimit;
        this.waveManager = new WaveManager(leftLimit, rightLimit, view.getHeight());

       
        this.gameLoop = new Thread(this);
        this.backgroundMusic = new BackgroundMusic("src/resources/music/backgroundMusic.wav");
        this.backgroundMusicThread = new Thread(backgroundMusic);
        this.graphicsUpdater = new Thread(new ViewUpdater(view, GRAPHICS_DELAY_MS));

        this.initListeners();
    }

    @Override
    public void run() {
        int timeCount;
        long beforeTime, timeDiff, sleep;
        
        graphicsUpdater.start();

        while (gameStatus.isInGame()) {
            timeCount = 0; // counts the number of cycles
            
            // set wave
            gameStatus.setCurrentWaveNumber(gameStatus.getCurrentWaveNumber() + 1);
            wave = waveManager.getWave(gameStatus.getCurrentWaveNumber());
            GameView gameView = (GameView) view;
            gameView.setCurrentWave(wave);
            
            gameStatus.setInWave(true);
            
            while (gameStatus.isInGame() && gameStatus.isInWave() ) {
                // System.out.println("In wave");
                
                beforeTime = System.currentTimeMillis();
                
                if (!wave.hasVirusToSpawn() && !wave.hasAliveViruses()) {
                    gameStatus.setInWave(false);
                }
                
                // spawn and move
                synchronized (wave) {
                    updateWave(timeCount);
                    checkBaseCollision();
                }
                
                timeDiff = System.currentTimeMillis() - beforeTime;
                sleep = GAME_DELAY_MS - timeDiff;
                
                // we use this if to handle the case in which the execution time of
                // updateEnemies(), checkBaseCollision() and update() methods is larger then the DELAY.
                if (sleep < 0) {
                    // 2 is a random chose.
                    sleep = 2;
                }
            
                ThreadUtilities.sleep(sleep);

                timeCount++;
            }
            // wave transition
            gameStatus.setInWaveTransition(true);
            
            ThreadUtilities.sleep(WAVE_DELAY_MS);
            
            gameStatus.setInWaveTransition(false);
        }
    }

    private void updateWave(int timeCount) {
        // spawn
        wave.spawnVirus(timeCount);
        
        // remove dead viruses and move viruses
        Collection<Virus> aliveSpawnedViruses = wave.getAliveSpawnedViruses();

        Iterator<Virus> it = aliveSpawnedViruses.iterator();
        while (it.hasNext()) {
            Virus v = it.next();

            if (!v.isAlive()) {
                it.remove();
                
                gameStatus.addBitcoinsAndScore(v.getBitcoinsValue()*gameStatus.getMultiplier());
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
        Collection<Virus> aliveSpawnedViruses = wave.getAliveSpawnedViruses();
        
        Iterator<Virus> it = aliveSpawnedViruses.iterator();

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
                gameStatus.resetConsecutiveHits();

                it.remove();
            }
        }

        if (base.isInfected()) {
            gameStatus.setInGame(false);
            backgroundMusic.setRunning(false);
            this.gameEnded();
        }
    }

    private void checKeyCollision(Key key) {
        synchronized (this.wave) {
            Collection<Virus> aliveSpawnedViruses = wave.getAliveSpawnedViruses();
            boolean missedViruses = true;
            for (Virus virus : aliveSpawnedViruses) {
                if (checkCollision(key.getBounds(), virus.getBounds())) {
                    virus.damage(key.getAttack());
                    gameStatus.incrementConsecutiveHits();
                    missedViruses = false;
                }
            }
            if (missedViruses) {
                gameStatus.resetConsecutiveHits();
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
                if(IAmTheAntivirus.getGameInstance().getMusicOn()){
                    backgroundMusicThread.start();
                    backgroundMusic.setRunning(true);
                }
                
            }

            // this event is called when the view is removed by the frame
            @Override
            public void ancestorRemoved(AncestorEvent e) {
                gameStatus.setInGame(false);
                backgroundMusic.setRunning(false);
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
                    checKeyCollision(keyboard.getKey((char) keyCode));
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

    private void gameEnded() {
        IAmTheAntivirus.getGameInstance().displayGameOverMenu();
    }

}
