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
import java.util.List;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import models.shop.Stat;
import models.sprites.Firewall;
import models.sprites.Keyboard.Key;
import utilities.ThreadUtilities;
import views.game.GameView;

/**
 *
 * @author Gerardo
 */
public class GameController extends Controller implements Runnable {

    private final Keyboard keyboard;
    private final Base base;
    private final Firewall firewall;
    private Shell shell;
    private final Thread gameLoop;
    private final Thread graphicsUpdater;

    private final static int GAME_DELAY_MS = 20;
    private final static int GRAPHICS_DELAY_MS = 20;
    private final static int WAVE_DELAY_MS = 3000;
    

    private final GameStatus gameStatus;

    private final WaveManager waveManager;
    private Wave wave;

    private List<Stat> stats;

    public GameController(GameView view) throws KeyNotFoundException {
        super(view);

        this.keyboard = view.getKeyboard();
        this.base = view.getBase();
        this.firewall = view.getFirewall();
        this.gameStatus = GameStatus.getInstance();
        int leftLimit = keyboard.getKey('1').getX();
        int rightLimit = keyboard.getKey('P').getX() + keyboard.getKey('P').getWidth() - leftLimit;
        this.waveManager = new WaveManager(leftLimit, rightLimit, view.getHeight());
        this.shell = view.getShell();
        this.gameLoop = new Thread(this);
        this.graphicsUpdater = new Thread(new ViewUpdater(view, GRAPHICS_DELAY_MS));

        this.stats = gameStatus.getStats();
        this.updateStats();
        

        this.initListeners();
    }

    @Override
    public void run() {
        int timeCount;
        long beforeTime, timeDiff, sleep;

        graphicsUpdater.start();
        IAmTheAntivirus.getGameInstance().setMusicOn(true);

        while (gameStatus.isInGame() && !Thread.currentThread().isInterrupted()) {
            timeCount = 0; // counts the number of cycles

            // set wave
            gameStatus.setCurrentWaveNumber(gameStatus.getCurrentWaveNumber() + 1);
            wave = waveManager.getWave(gameStatus.getCurrentWaveNumber());
            GameView gameView = (GameView) view;
            gameView.setCurrentWave(wave);

            gameStatus.setInWave(true);

            while (gameStatus.isInGame() && gameStatus.isInWave() && !Thread.currentThread().isInterrupted()) {
                // System.out.println("In wave");

                beforeTime = System.currentTimeMillis();

                if (!wave.hasVirusToSpawn() && !wave.hasAliveViruses()) {
                    gameStatus.setInWave(false);
                }

                // spawn and move
                synchronized (wave) {
                    updateWave(timeCount);
                    checkFirewallCollision();
                    checkBaseCollision();
                    if (Thread.currentThread().isInterrupted()) {
                        return;
                    }
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

            IAmTheAntivirus.getGameInstance().openShopMenu();

            /*
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(GameController.class.getName()).log(Level.SEVERE, null, ex);
            }
             */
            // usare un altro metodo, wait e notify, oppure uccidere e riavviare il thread
            while (gameStatus.isInShop()) {
                ThreadUtilities.sleep(1000);
            }
            this.updateStats();

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

                gameStatus.addBitcoinsAndScore(v.getBitcoinsValue() * gameStatus.getMultiplier());
            } else {
                v.move(keyboard.getBounds());
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
            this.gameEnded();
        }
    }
    
    private void checkFirewallCollision(){
        
        if(!firewall.isActive())
            return;
        synchronized (this.wave) {
            Collection<Virus> aliveSpawnedViruses = wave.getAliveSpawnedViruses();
            boolean missedViruses = true;
            for (Virus virus : aliveSpawnedViruses) {
                if (checkCollision(firewall.getBounds(), virus.getBounds())) {
                    
                    
                    virus.damage(virus.getCurrentHealth());
                    gameStatus.incrementConsecutiveHits();
                    missedViruses = false;
                }
            }
            if (missedViruses) {
                //gameStatus.resetConsecutiveHits();
            }
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
                
                if ((char) keyCode == '\\' && gameStatus.isInWave()) {
                    shell.setFocusable(true);
                    return;
                }
                if (shell.isFocusable()) {
                    if (keyCode == 10) {
                        shell.setFocusable(false);
                        shell.launchCommand();
                        
                        
                        return;
                    }
                    shell.digitcommands((char) keyCode);
                    return;
                }
                if(gameStatus.isInWave()){
                    try {
                        keyboard.press((char) keyCode);
                        checKeyCollision(keyboard.getKey((char) keyCode));
                    } catch (KeyNotFoundException knfe) { }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();
                
                try {
                    keyboard.release((char) keyCode);
                } catch (KeyNotFoundException knfe) {}
                
            }
        });
    }


    private void updateStats() {
        for (Stat s : stats) {
            if (s.getId() == "health") {
                this.base.setTotalHealth(s.getValue());
            }
            if (s.getId() == "attack") {
                for (Key k : this.keyboard.getKeys()) {
                    k.setAttack(s.getValue());
                }
            }
        }
    }

    private void gameEnded() {
        gameStatus.setInGame(false);
        graphicsUpdater.interrupt();
        gameLoop.interrupt();
        IAmTheAntivirus appInstance = IAmTheAntivirus.getGameInstance();
        appInstance.setMusicOn(false);

        IAmTheAntivirus.getGameInstance().displayGameOverMenu();
    }

}
