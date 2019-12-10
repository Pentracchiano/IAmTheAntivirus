/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.game.GameController;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import views.game.GameView;
import menu.AbstractMenuViewController;
import menu.gameovermenu.GameOverViewController;
import menu.mainmenu.MainMenuViewController;

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
    //HO MODIFICATO currentMenu da final a non final
    private AbstractMenuViewController currentMenu;
    

    private static IAmTheAntivirus gameApplication;

    private IAmTheAntivirus() {
        frame = new JFrame();

        currentMenu = new MainMenuViewController();
        
        
        
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
     * in order to define the behaviors of the class clearly.
     */
    public void startGame() {
        EventQueue.invokeLater(() -> {
            frame.remove(currentMenu);
            gameView = new GameView();
            gameController = new GameController(gameView);
            frame.add(gameView);
            
            frame.pack();
            frame.setLocationRelativeTo(null);
            
            gameView.requestFocusInWindow();
        });
    }
    
    public void displayMainMenu(){
        EventQueue.invokeLater(() -> {
            frame.remove(currentMenu);
            currentMenu = new MainMenuViewController();
            frame.add(currentMenu);
    

            
            frame.pack();
            frame.setLocationRelativeTo(null);
            
            gameView.requestFocusInWindow();
        });
    }
    
    
    

    
    public void displayGameOverMenu(){
        EventQueue.invokeLater(() -> {
            frame.remove(gameView);
            currentMenu = new GameOverViewController();
            frame.add(currentMenu);
    

            
            frame.pack();
            frame.setLocationRelativeTo(null);
            
            gameView.requestFocusInWindow();
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
        frame.add(currentMenu);

        frame.pack();
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //frame.add(gameView);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            IAmTheAntivirus application = IAmTheAntivirus.getGameInstance();
            application.frame.setVisible(true);
        });
    }

}
