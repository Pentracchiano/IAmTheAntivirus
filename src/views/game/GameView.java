/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.game;

import controllers.game.Shell;
import controllers.game.Wave;
import views.View;
import models.GameStatus;
import models.sprites.Keyboard.Key;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import models.sprites.Base;
import models.sprites.Firewall;
import models.sprites.Keyboard;
import models.sprites.Sprite;
import models.sprites.Virus;
import models.sprites.behaviors.Command;
import utilities.ImageUtilities;

public class GameView extends View {

    private Keyboard keyboard;
    private Base base;
    private Firewall firewall;

    private Wave currentWave;
    private Shell shell;
    private GameStatus gameStatus;

    private final int B_WIDTH = 1300;
    private final int B_HEIGHT = 747;

    private final Dimension dimension = new Dimension(B_WIDTH, B_HEIGHT);
    private BufferedImage backgroundImage;

    private static final Color COLOR_TERMINAL_GREEN = new Color(97, 231, 79);
    private static final Font DEFAULT_FONT = new Font(Font.MONOSPACED, Font.BOLD, 30);

    private static final Color LEVEL_COLOR = COLOR_TERMINAL_GREEN;
    private static final Font LEVEL_FONT = new Font(Font.MONOSPACED, Font.BOLD, 15);

    private static final String HEART_IMAGE_PATH = "src/resources/heart_50_red.png";
    private static final String HEALTH_BAR_IMAGE_PATH = "src/resources/health_bar_green_150_30.png";
    private static final String HEALTH_BORDERS_IMAGE_PATH = "src/resources/health_bar_borders_green_150_30.png";
    private static final String ENEMY_IMAGE_PATH = "src/resources/enemy_40.png";
    private static final String BITCOIN_IMAGE_PATH = "src/resources/bitcoin.png";
    private static final String FLAME_IMAGE_PATH = "src/resources/flame.png";

    private Image heartImage;
    private Image healthBarImage;
    private Image healthBordersImage;
    private Image enemyImage;
    private Image bitcoinImage;
    private Image flame;
    
    private Set<Command> commands;

    public GameView() {
        this.setLayout(new GridLayout());
        initView();
    }

    private void initView() {
        commands = GameStatus.getInstance().getCommands();


        base = new Base(0, 0, GameStatus.getInstance().getDefaultMaxHealth());

        setBackground(Color.GRAY);
        try {
            backgroundImage = ImageIO.read(new File("src/resources/background/background.png"));
        } catch (IOException ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }

        setFocusable(true);
        setPreferredSize(dimension);

        keyboard = new Keyboard(37, 275);
        firewall = new Firewall(37,20);
        
        GameStatus.getInstance().addCommand(firewall);

        this.gameStatus = GameStatus.getInstance();
        this.shell= new Shell(commands);
        this.heartImage = ImageUtilities.loadImageFromPath(HEART_IMAGE_PATH);
        this.healthBarImage = ImageUtilities.loadImageFromPath(HEALTH_BAR_IMAGE_PATH);
        this.healthBordersImage = ImageUtilities.loadImageFromPath(HEALTH_BORDERS_IMAGE_PATH);
        this.enemyImage = ImageUtilities.loadImageFromPath(ENEMY_IMAGE_PATH);
        this.bitcoinImage = ImageUtilities.loadImageFromPath(BITCOIN_IMAGE_PATH).getScaledInstance(30, -1, Image.SCALE_DEFAULT);
        this.flame = ImageUtilities.loadImageFromPath(FLAME_IMAGE_PATH);
    }

    @Override
    public void update() {
        this.repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.drawImage(backgroundImage, 0, 0, this);

        drawKeyboard(g);
        

        // the draws must be called in this order, otherwise the base will cover other drawings
        synchronized (base) {
            drawBase(g);
        }
        
        if (currentWave != null) {
            if (!gameStatus.isInWaveTransition()) {
                synchronized (currentWave) {
                    drawViruses(g);
                }
            }
        }
        drawCommands(g);
        
        drawWaveStatus(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawWaveStatus(Graphics g) {
        int x;
        int y;

        if (gameStatus.isInWaveTransition()) {
            drawFormattedString(g, "Loading new wave...", base.getX() + 550, base.getY() + 40, COLOR_TERMINAL_GREEN, DEFAULT_FONT);
            return;
        }

        // draw wave indicator
        x = base.getX() + 560;
        y = base.getY() + 40;

        drawFormattedString(g, "Wave " + gameStatus.getCurrentWaveNumber(), x, y, COLOR_TERMINAL_GREEN, DEFAULT_FONT);
    }

    private void drawFormattedString(Graphics g, String string, int x, int y, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(string, x, y);
    }

    private void drawViruses(Graphics g) {
        // draw enemies indicator
        int x;
        int y;
        Collection<Virus> aliveSpawnedViruses = null;
        int aliveSpawnedVirusesSize;
        int remainingVirusesSize;
        int waveSize;

        if (currentWave == null) {
            aliveSpawnedVirusesSize = 0;
            waveSize = 0;
            remainingVirusesSize = 0;
        } else {
            aliveSpawnedViruses = currentWave.getAliveSpawnedViruses();
            aliveSpawnedVirusesSize = aliveSpawnedViruses.size();
            waveSize = currentWave.getWaveSize();
            remainingVirusesSize = currentWave.getVirusesToSpawnSize() + aliveSpawnedVirusesSize;
        }

        x = base.getX() + 710;
        y = base.getY() + 15;
        g.drawImage(enemyImage, x, y, this);

        x += enemyImage.getWidth(this) + 10;
        y = base.getY() + 40;
        drawFormattedString(g, remainingVirusesSize + "/" + waveSize, x, y, COLOR_TERMINAL_GREEN, DEFAULT_FONT);

        // draw viruses
        if (aliveSpawnedViruses == null) {
            return;
        }

        aliveSpawnedViruses.forEach((virus) -> {
            g.drawImage(virus.getImage(), virus.getX(), virus.getY(), this);
        });

        // draw health bar
        aliveSpawnedViruses.forEach((virus) -> {
            if (virus.getCurrentHealth() < virus.getTOTAL_HEALTH()) {
                double healthRatio = (double) virus.getCurrentHealth() / (double) virus.getTOTAL_HEALTH();

                g.setColor(getColorHealthBar(healthRatio));

                int width = (int) (virus.getWidth() * healthRatio);

                g.fillRect(virus.getX(), virus.getY() - 7, width, 5);
            }
        });

        // draw level
        aliveSpawnedViruses.forEach((virus) -> {
            drawFormattedString(g, "LvL " + virus.getLevel(), virus.getX(), virus.getY() + virus.getHeight() + 15, LEVEL_COLOR, LEVEL_FONT);
        });
    }

    private Color getColorHealthBar(double healthRatio) {
        if (healthRatio >= 0.66) {
            return COLOR_TERMINAL_GREEN;
        } else if (healthRatio >= 0.33) {
            return Color.ORANGE;
        } else {
            return Color.RED;
        }

    }

    private void drawKeyboard(Graphics g) {

        g.drawImage(keyboard.getImage(), keyboard.getX(), keyboard.getY(), this);

        Collection<Key> coll = keyboard.getKeys();

        for (Key key : coll) {
            g.drawImage(key.getImage(), key.getX(), key.getY(), this);
        };

    }

    private void drawBase(Graphics g) {
        final int BASE_SPAN_X = base.getX() + 200;
        final int BASE_SPAN_Y = base.getY() + 15;

        final int HEART_SPAN_X = heartImage.getWidth(this) + 10;

        final int HEALTH_SPAN_X = healthBordersImage.getWidth(this) + 10;

        final int BITCOIN_SPAN_X = bitcoinImage.getWidth(this) + 10;

        g.drawImage(base.getImage(), base.getX(), base.getY(), this);

        g.drawImage(heartImage, BASE_SPAN_X, base.getY() + 5, this);

        g.drawImage(healthBordersImage, BASE_SPAN_X + HEART_SPAN_X, BASE_SPAN_Y, this);

        int width = healthBarImage.getWidth(this) * base.getCurrentHealth() / base.getTotalHealth();
        
        g.drawImage(healthBarImage, BASE_SPAN_X + HEART_SPAN_X, BASE_SPAN_Y, width, healthBarImage.getHeight(this), this);
        
        g.drawImage(bitcoinImage, BASE_SPAN_X + HEART_SPAN_X + HEALTH_SPAN_X, BASE_SPAN_Y, this);
        drawFormattedString(g, this.shell.getText(), base.getX() + 200, base.getY() + 130, COLOR_TERMINAL_GREEN, DEFAULT_FONT);
        
        String multiplier = "x" + gameStatus.getMultiplier();
        drawFormattedString(g, multiplier, BASE_SPAN_X + HEART_SPAN_X + HEALTH_SPAN_X + BITCOIN_SPAN_X - 10, BASE_SPAN_Y + 25, COLOR_TERMINAL_GREEN, DEFAULT_FONT.deriveFont((float) 18));
        String bitcoins = String.valueOf(gameStatus.getBitcoins());
        drawFormattedString(g, bitcoins, BASE_SPAN_X + HEART_SPAN_X + HEALTH_SPAN_X + BITCOIN_SPAN_X + 15, BASE_SPAN_Y + 25, COLOR_TERMINAL_GREEN, DEFAULT_FONT);
        if(!firewall.isInCoolDown())
            g.drawImage(flame,850, 85, this);
    }
    
    public void drawCommands(Graphics g){
        
        for(Command c: gameStatus.getCommands()){
            Sprite command = (Sprite) c;
          g.drawImage(command.getImage(), command.getX(), command.getY(), this);
        }
    }
    


    public Shell getShell() {
        return shell;
    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Base getBase() {
        return base;
        
    }
    
    public Firewall getFirewall(){
        return firewall;
    }

    @Override
    public int getWidth() {
        return backgroundImage.getWidth();
    }

    @Override
    public int getHeight() {
        return backgroundImage.getHeight();
    }

    public Dimension getPanelDimension() {
        return dimension;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setCurrentWave(Wave currentWave) {
        this.currentWave = currentWave;
    }

}
