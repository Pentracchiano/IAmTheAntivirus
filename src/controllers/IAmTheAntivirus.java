/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.game.GameController;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import views.game.GameView;
import menu.AbstractMenuViewController;
import menu.gameovermenu.GameOverViewController;
import menu.highscoresmenu.HighScoresMenuViewController;
import menu.highscoresmenu.SetHighScoresMenuViewController;
import menu.mainmenu.MainMenuViewController;
import menu.shopmenu.ShopMenuViewController;
import models.GameStatus;
import models.sprites.exceptions.KeyNotFoundException;
import sounds.BackgroundMusic;
import utilities.FontUtilities;

/**
 * Singleton class which has the global game status and screens to display at a
 * given moment.
 *
 * @author ccarratu
 */
public class IAmTheAntivirus {

    private final JFrame frame;
    private GameView gameView;
    private GameController gameController;
    private final List<String> highscores = new ArrayList<>(HIGHSCORES_NUMBER);
    private ShopMenuViewController shopMenu;
    private SetHighScoresMenuViewController setHighScoresMenu;
    private HighScoresMenuViewController highscoresMenu;
    //HO MODIFICATO currentMenu da final a non final
    private AbstractMenuViewController currentMenu;
    private static IAmTheAntivirus gameApplication;
    private static final String FONT_MENU_PATH = "src/resources/fonts/Minecraft.ttf";
    public static final int HIGHSCORES_NUMBER = 5;
    public static final String DEFAULT_NAME = "---";
    public static final String DEFAULT_SCORE = "000";
    public static final String HIGHSCORES_FILE_PATH = "src/resources/highscores/highscores.txt";
    private final Dimension panelDimension = new GameView().getPanelDimension();
    private boolean musicOn = false;
    private boolean musicDisabled = false;
    private final BackgroundMusic backgroundMusic = new BackgroundMusic("src/resources/music/backgroundMusic.wav");

    private IAmTheAntivirus() {
        FontUtilities.registerFont(FONT_MENU_PATH);
        frame = new JFrame();
        loadHighScores();
        initFrame();
    }

    /**
     * The only way to obtain an instance of the IAmTheAntivirus (so, the game
     * app class) is to call this method: it always returns the same instance as
     * for the Singleton design pattern.
     *
     * @return the game singleton class.
     */
    public static IAmTheAntivirus getGameInstance() {
        if (IAmTheAntivirus.gameApplication == null) {
            IAmTheAntivirus.gameApplication = new IAmTheAntivirus();
        }

        return IAmTheAntivirus.gameApplication;
    }

    /**
     * Sets the game screen and launches the game.
     *
     * This <strong>must</strong> be called when the game is in a "menu" state.
     * The fact that we used an "abstract menu" class makes this feasible also
     * from the Game over screen, and not only from the main menu.
     *
     * We decided to do this here and not put getters and setters of the frame
     * in order to define the behaviours of the class clearly.
     */
    public void startGame() {
        EventQueue.invokeLater(() -> {
            frame.remove(currentMenu);
            GameStatus.disposeInstance();
            gameView = new GameView();
            try {
                gameController = new GameController(gameView);
            } catch (KeyNotFoundException ex) {
                Logger.getLogger(IAmTheAntivirus.class.getName()).log(Level.SEVERE, null, ex);
            }
            frame.add(gameView);
            frame.pack();
            frame.setLocationRelativeTo(null);
            gameView.requestFocusInWindow();
            shopMenu = new ShopMenuViewController(panelDimension);
        });
    }

    public void displayMainMenu() {
        EventQueue.invokeLater(() -> {
            if (currentMenu != null) {
                frame.remove(currentMenu);
            }
            currentMenu = new MainMenuViewController(panelDimension);
            frame.add(currentMenu);
            frame.pack();
            frame.setLocationRelativeTo(null);
            currentMenu.requestFocusInWindow();
            highscoresMenu = new HighScoresMenuViewController(panelDimension);

        });
    }
    
    public void displayHighScoresMenu(){
        EventQueue.invokeLater(() -> {
            if (currentMenu != null) {
                frame.remove(currentMenu);
            }
            currentMenu = highscoresMenu;
            frame.add(currentMenu);
            frame.pack();
            frame.setLocationRelativeTo(null);
            currentMenu.requestFocusInWindow();

        });
    }

    public void displayGameOverMenu() {
        GameStatus.disposeInstance();
        EventQueue.invokeLater(() -> {
            frame.remove(gameView);
            currentMenu = new GameOverViewController(panelDimension);
            frame.add(currentMenu);
            frame.pack();
            frame.setLocationRelativeTo(null);
            currentMenu.requestFocusInWindow();
        });
    }

    public void openSetHighScoresMenu() {
        
        EventQueue.invokeLater(() -> {
            
            setHighScoresMenu = new SetHighScoresMenuViewController(panelDimension);
            gameView.add(setHighScoresMenu);
            setHighScoresMenu.setVisible(true);
            
        });
    }
    
    public void closeSetHighScoresMenu() {
        
        EventQueue.invokeLater(() -> {
            
            gameView.remove(setHighScoresMenu);
            setHighScoresMenu.setVisible(false);
            displayGameOverMenu();
            
        });
    }
    
    public void openShopMenu() {
        GameStatus.getInstance().setInShop(true);
        EventQueue.invokeLater(() -> {
            
            gameView.add(shopMenu);
            shopMenu.setVisible(true);

        });
    }

    public void closeShopMenu() {
        GameStatus.getInstance().setInShop(false);
        EventQueue.invokeLater(() -> {
            shopMenu.setVisible(false);
            gameView.remove(shopMenu);
        });
    }

    /**
     * This method closes the application: do any clean up here if needed.
     *
     */
    public void closeGame() {
        EventQueue.invokeLater(() -> {
            // simply closes the app as if clicked the exit button
            frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
        });
    }

    private void initFrame() {
        frame.setResizable(false);
        frame.setTitle("IAmTheAntivirus");
        // this method has to be called after add() and before setLocationRelativeTo()
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        displayMainMenu();

    }

    public boolean getMusicOn() {
        return musicOn;
    }

    public boolean isMusicDisabled() {
        return musicDisabled;
    }

    private void loadHighScores() {
        try {
            InputStream stream = new DataInputStream(new BufferedInputStream(new FileInputStream(HIGHSCORES_FILE_PATH)));
            Scanner in = new Scanner(stream);
            int i = 0;    
            while(i < HIGHSCORES_NUMBER){
                if(in.hasNext()){
                    this.setHighscores(in.next(), in.next());
                }
                else{
                    this.setHighscores(DEFAULT_NAME, DEFAULT_SCORE);
                }
                i++;
            }
            in.close();
        } catch (FileNotFoundException ex) {
            
        };
        
    }
    
    public synchronized List<String> getHighscores() {
        return highscores;
    }

    public synchronized void setHighscores(String name, String score) {
        this.highscores.add(name + ";" + score);
    }
    
    /**
     * Disables the music in the game.
     * Subsequent calls to {@link #setMusicOn(boolean)} will fail, and the music will be turned off
     * if it is currently playing.
     * @param musicDisabled 
     */
    public void setMusicDisabled(boolean musicDisabled) {
        this.musicDisabled = musicDisabled;
        
        // stop the music if it was playing.
        if(musicDisabled && musicOn) {
            setMusicOn(false);
        }
    }

    /**
     * This method has no effect if the music is disabled.
     * Refer to {@link #setMusicDisabled(boolean)} and {@link #isMusicDisabled()} for changing the music disabled state.
     * @param musicOn 
     */
    public void setMusicOn(boolean musicOn) {
        if (!musicDisabled) {
            this.musicOn = musicOn;

            backgroundMusic.setRunning(musicOn);
        }
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            IAmTheAntivirus application = IAmTheAntivirus.getGameInstance();
            application.frame.setVisible(true);
        });
    }

}
