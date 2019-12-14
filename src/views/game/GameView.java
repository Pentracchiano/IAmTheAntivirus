/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.game;

import controllers.game.Wave;
import views.View;
import models.GameStatus;
import models.sprites.Keyboard.Key;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import models.sprites.Base;
import models.sprites.Keyboard;
import models.sprites.Virus;
import utilities.ImageUtilities;

public class GameView extends View {

    private Keyboard keyboard;
    private Base base;
    
    private Wave currentWave;
    
    private GameStatus gameStatus;
    
    private final int B_WIDTH = 1300;
    private final int B_HEIGHT = 747;
    
    private final Dimension dimension = new Dimension(B_WIDTH,B_HEIGHT);
    private BufferedImage backgroundImage;

    private static final String HEART_IMAGE_PATH = "src/resources/heart_50_red.png";
    private static final String HEALTH_BAR_IMAGE_PATH = "src/resources/health_bar_green_150_30.png";
    private static final String HEALTH_BORDERS_IMAGE_PATH = "src/resources/health_bar_borders_green_150_30.png";
    private static final String ENEMY_IMAGE_PATH = "src/resources/enemy_40.png";

    private Image heartImage;
    private Image healthBarImage;
    private Image healthBordersImage;
    private Image enemyImage;

    public GameView() {
        initView();
    }

    private void initView() {
        base = new Base(0, 0, 20);
        
        setBackground(Color.GRAY);
        try {
            backgroundImage = ImageIO.read(new File("src/resources/background/background.png"));
        } catch (IOException ex) {
            Logger.getLogger(GameView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
	setFocusable(true);
        setPreferredSize(dimension);
        
        keyboard = new Keyboard(160,300);
        
        this.gameStatus = GameStatus.getInstance();

        this.heartImage = ImageUtilities.loadImageFromPath(HEART_IMAGE_PATH);
        this.healthBarImage = ImageUtilities.loadImageFromPath(HEALTH_BAR_IMAGE_PATH);
        this.healthBordersImage = ImageUtilities.loadImageFromPath(HEALTH_BORDERS_IMAGE_PATH);
        this.enemyImage = ImageUtilities.loadImageFromPath(ENEMY_IMAGE_PATH);
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
        
        if(!gameStatus.isInWaveTransition()) {
            synchronized (currentWave) {
                drawViruses(g);
            }
        }

        drawGameStatus(g);
        
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawGameStatus(Graphics g) {
        int x;
        int y;

        if (gameStatus.isInWaveTransition()) {
            drawFormattedString(g, "Loading new wave...", base.getX() + 540, base.getY() + 40, new Color(97, 231, 79), new Font(Font.MONOSPACED, Font.BOLD, 30));
            return;
        }

        // draw wave indicator
        x = base.getX() + 560;
        y = base.getY() + 40;
        drawFormattedString(g, "Wave " + gameStatus.getCurrentWaveNumber(), x, y, new Color(97, 231, 79), new Font(Font.MONOSPACED, Font.BOLD, 30));
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
        
        if(currentWave == null) {
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
        drawFormattedString(g, remainingVirusesSize +  "/" + waveSize, x, y, new Color(97, 231, 79), new Font(Font.MONOSPACED, Font.BOLD, 30));
        
        // draw viruses
        if(aliveSpawnedViruses == null) {
            return;
        }
        
        aliveSpawnedViruses.forEach((virus) -> {
            g.drawImage(virus.getImage(), virus.getX(), virus.getY(), this);
        });

        // draw health bar
        aliveSpawnedViruses.forEach((virus) -> {
            if (virus.getCurrentHealth() < virus.getTotalHealth()) {
                double healthRatio = (double) virus.getCurrentHealth() / (double) virus.getTotalHealth();

                g.setColor(getColorHealthBar(healthRatio));

                int width = (int) (virus.getWidth() * healthRatio);

                g.fillRect(virus.getX(), virus.getY() - 7, width, 5);
            }
        });
    }

    private Color getColorHealthBar(double healthRatio) {
        if (healthRatio >= 0.66) {
            return new Color(97, 231, 79);
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
        g.drawImage(base.getImage(), base.getX(), base.getY(), this);

        g.drawImage(heartImage, base.getX() + 200, base.getY() + 5, this);

        g.drawImage(healthBordersImage, base.getX() + 200 + heartImage.getWidth(this) + 10, base.getY() + 15, this);

        int width = healthBarImage.getWidth(this) * base.getCurrentHealth() / base.getTotalHealth();

        g.drawImage(healthBarImage, base.getX() + 200 + heartImage.getWidth(this) + 10, base.getY() + 15, width, healthBarImage.getHeight(this), this);

    }

    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Base getBase() {
        return base;
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
