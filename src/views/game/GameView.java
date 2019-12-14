/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views.game;

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
import java.util.ArrayList;
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
    private ArrayList<Virus> viruses;
    private final int B_WIDTH = 1300;
    private final int B_HEIGHT = 747;
    private final Dimension dimension = new Dimension(B_WIDTH,B_HEIGHT);
    private BufferedImage backgroundImage;

    private GameStatus gameStatus;
    
    private static final Color COLOR_TERMINAL_GREEN = new Color(97, 231, 79);

    private static final String HEART_IMAGE_PATH = "src/resources/heart_50_red.png";
    private static final String HEALTH_BAR_IMAGE_PATH = "src/resources/health_bar_green_150_30.png";
    private static final String HEALTH_BORDERS_IMAGE_PATH = "src/resources/health_bar_borders_green_150_30.png";
    private static final String ENEMY_IMAGE_PATH = "src/resources/enemy_40.png";
    private static final String BITCOIN_IMAGE_PATH = "src/resources/bitcoin.png";
    
    private Image heartImage;
    private Image healthBarImage;
    private Image healthBordersImage;
    private Image enemyImage;
    private Image bitcoinImage;

    public GameView() {

        initView();
    }

    private void initView() {

        viruses = new ArrayList<>();
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
        this.bitcoinImage = ImageUtilities.loadImageFromPath(BITCOIN_IMAGE_PATH).getScaledInstance(30, -1, Image.SCALE_DEFAULT);
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

        synchronized (viruses) {
            drawViruses(g);
        }

        synchronized (base) {
            drawBase(g);
        }

        drawWaveStatus(g);
        Toolkit.getDefaultToolkit().sync();
    }

    private void drawWaveStatus(Graphics g) {
        int x;
        int y;

        if (gameStatus.isInWaveTransition()) {
            drawFormattedString(g, "Loading new wave...", base.getX() + 540, base.getY() + 40, new Color(97, 231, 79), new Font(Font.MONOSPACED, Font.BOLD, 30));
            return;
        }

        // draw wave indicator
        x = base.getX() + 560;
        y = base.getY() + 40;
        drawFormattedString(g, "Wave " + gameStatus.getCurrentWave(), x, y, new Color(97, 231, 79), new Font(Font.MONOSPACED, Font.BOLD, 30));

        // draw enemies indicator
        x += 150;
        g.drawImage(enemyImage, x, base.getY() + 15, this);

        x += enemyImage.getWidth(this) + 10;
        drawFormattedString(g, gameStatus.getRemainingWaveEnemies() + "/" + gameStatus.getTotalWaveEnemies(), x, y, new Color(97, 231, 79), new Font(Font.MONOSPACED, Font.BOLD, 30));
    }

    private void drawFormattedString(Graphics g, String string, int x, int y, Color color, Font font) {
        g.setColor(color);
        g.setFont(font);
        g.drawString(string, x, y);
    }

    private void drawViruses(Graphics g) {
        // draw viruses
        viruses.forEach((vir) -> {
            g.drawImage(vir.getImage(), vir.getX(), vir.getY(), this);
        });

        // draw health bar
        viruses.forEach((vir) -> {
            if (vir.getCurrentHealth() < vir.getTotalHealth()) {
                double healthRatio = (double) vir.getCurrentHealth() / (double) vir.getTotalHealth();

                g.setColor(getColorHealthBar(healthRatio));

                int width = (int) (vir.getWidth() * healthRatio);

                g.fillRect(vir.getX(), vir.getY() - 7, width, 5);
            }
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
        
        String bitcoins = String.valueOf(gameStatus.getBitcoins());
        System.out.println(gameStatus.getBitcoins());
        drawFormattedString(g, bitcoins, BASE_SPAN_X + HEART_SPAN_X + HEALTH_SPAN_X + BITCOIN_SPAN_X, BASE_SPAN_Y + 25, COLOR_TERMINAL_GREEN, new Font(Font.SANS_SERIF, Font.BOLD, 30));
    }
    
    public Keyboard getKeyboard() {
        return keyboard;
    }

    public Base getBase() {
        return base;
    }

    public ArrayList<Virus> getViruses() {
        return viruses;
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

}
