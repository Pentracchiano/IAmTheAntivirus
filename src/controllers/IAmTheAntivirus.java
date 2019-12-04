/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;
import controllers.game.GameController;
import views.game.GameView;

/**
 *
 * @author ccarratu
 */
import java.awt.EventQueue;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class IAmTheAntivirus extends JFrame {
    private GameController gameController;

    public IAmTheAntivirus(){
        initGame();
    }
    
    private void initGame(){
        
        setResizable(false);
        setTitle("IAmTheAntivirus");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GameView gameView = null;
        try {
            gameView = new GameView();
        } catch (IOException ex) {
            Logger.getLogger(IAmTheAntivirus.class.getName()).log(Level.SEVERE, null, ex);
        }
        gameController = new GameController(gameView);
        
        this.add(gameView);
        
        pack();
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            IAmTheAntivirus ex = new IAmTheAntivirus();
            ex.setVisible(true);
        });
    }

}
