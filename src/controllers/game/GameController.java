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
import java.util.concurrent.locks.ReentrantLock;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import models.shop.Stat;
import models.sprites.BaseHealer;
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
    /*
    * The interval, in time counts, that passes between two subsequent attempts
    * to create a BaseHealer.
    */
    //time counts, not milliseconds. 350*20 = 7000 ms = 7s
    private final static int HEALER_DELAY_TC = 350;
    private final BaseHealerManager baseHealerManager;
    private BaseHealer baseHealer;
    /*
    * Lock for synchronizing concurrent access to the BaseHealer between this
    * controller and the GameView.
    * Since the healer is nullified when it reaches the base or it's killed, an
    * auxiliary lock is needed to syncrhonize on it, to avoid 
    * NullPointerException on synchronized(healer) calls, because the healer 
    * could be nullified inside the block, so on exiting the block, and then
    * releasing the lock, if the healer has been nullified, we could get an
    * exception.
    */
    private final ReentrantLock baseHealerLock;
    private int maxWaveHealth;

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
        this.baseHealerManager = new BaseHealerManager();
        this.shell = view.getShell();
        this.gameLoop = new Thread(this);
        this.graphicsUpdater = new Thread(new ViewUpdater(view, GRAPHICS_DELAY_MS));
        this.baseHealerLock = view.getBaseHealerLock();

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
            refreshKeyboard();
            timeCount = 0; // counts the number of cycles
            
            /* 
            * Get the max health of the base for the current wave, needed for
            * the creation of the BaseHealer.
            */
            this.maxWaveHealth = base.getTotalHealth();
            //System.out.println("health: " + this.maxWaveHealth); // DEBUG

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
                    baseHealerLock.lock();
                    try {
                        updateWave(timeCount);
                        updateBaseHealer(timeCount);
                        checkFirewallCollision();
                        checkBaseCollision();
                    } finally {
                        baseHealerLock.unlock();
                    }
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

            /*
            * remove eventually spawned base healers that have neither been
            * kill nor have reached the base (otherwise they will persist 
            * through waves).
            */
            baseHealer = null;
            nullifyHealerInView();

            gameStatus.addBitcoinsAndScore(1);
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

    private void updateBaseHealer(int timeCount) {
        //System.out.println("Updating base healer"); //DEBUG
        if (baseHealer == null) {
            //try to get one, if it's time to (attempts to get a BaseHeaeler
            //every HEALER_DELAY_TC time counts.
            if (timeCount > 0 && (timeCount % HEALER_DELAY_TC) == 0) {
                baseHealer = baseHealerManager.getBaseHealer(keyboard.getBounds());
                if (baseHealer != null) {
                    // maxWaveHealth it's surely set because the call to this function
                    // is executed only after the wave is started (the inner while
                    // in the game loop)
                    baseHealer.setMaxHealth(this.maxWaveHealth);
                    System.out.println("BaseHealer creato"); // DEBUG
                }
                nullifyHealerInView();
            }
        } else {
            //an healer is already in game, so move it
            baseHealer.move(keyboard.getBounds());
        }
    }
    
    // Nullifies the healer field in the GameView instance, in order to make
    // it disappear from the screen.
    private void nullifyHealerInView() {
        GameView gameView = (GameView) this.view;
        gameView.setBaseHealer(baseHealer);
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

        if (baseHealer != null) {
            if (checkCollision(baseBounds, baseHealer.getBounds())) {
                baseHealer.heal(base);
                baseHealer = null;
                nullifyHealerInView();
            }
        }

        if (base.isInfected()) {
//            if (Integer.parseInt(gameStatus.getHighscores().get(4).split(";",0)[1]) > gameStatus.getScore()){
            this.gameEnded();
//            }else{
//                this.gameEndedWithHighscores();
//            }
        }
    }

    private void checkFirewallCollision() {

        if (!firewall.isActive()) {
            return;
        }
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

        if (this.baseHealer != null) {
            //it's surely alive, because this method is called after updateHealer method
            if (checkCollision(firewall.getBounds(), baseHealer.getBounds())) {
                //it's been killed, so the base must be damaged
                base.damage(baseHealer.getAttack());
                baseHealer = null;
               nullifyHealerInView();
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
        if (baseHealer != null) {
            baseHealerLock.lock();
            try {
                if (checkCollision(key.getBounds(), baseHealer.getBounds())) {
                    base.damage(baseHealer.getAttack());
                    baseHealer = null;
                    nullifyHealerInView();
                }
            } finally {
                baseHealerLock.unlock();
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
                if (gameStatus.isInWave()) {
                    try {
                        keyboard.press((char) keyCode);
                        checKeyCollision(keyboard.getKey((char) keyCode));
                    } catch (KeyNotFoundException knfe) {
                    }
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

    private void updateStats() {
        for (Stat s : stats) {
            if (s.getId() == "health") {
                int difference = this.base.getTotalHealth() - this.base.getCurrentHealth();
                this.base.setTotalHealth(s.getValue());
                this.base.setCurrentHealth(this.base.getTotalHealth() - difference);
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

        if (Integer.parseInt(gameStatus.getHighscores().get(4).split(";", 0)[1]) < gameStatus.getScore()) {

            appInstance.openSetHighScoresMenu();

        } else {
            IAmTheAntivirus.getGameInstance().displayGameOverMenu();
        }
    }

    private void refreshKeyboard() {
        for (Key k : keyboard.getKeys()) {
            keyboard.release(k.getId());
        }
    }
//    private void gameEndedWithHighscores() {
//        gameStatus.setInGame(false);
//        graphicsUpdater.interrupt();
//        gameLoop.interrupt();
//        IAmTheAntivirus appInstance = IAmTheAntivirus.getGameInstance();
//        appInstance.setMusicOn(false);
//        
//        IAmTheAntivirus.getGameInstance().displaySetHighScoresMenu();    }
}
