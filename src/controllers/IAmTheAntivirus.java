/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controllers.game.GameController;
import java.awt.EventQueue;
import javax.swing.JFrame;
import views.game.GameView;

/**
 *
 * @author ccarratu
 */
public class IAmTheAntivirus {
    private final JFrame frame;
    private final GameView gameView;
    private final GameController gameController;
    
    public IAmTheAntivirus(){
        frame = new JFrame();
        gameView = new GameView();
        gameController = new GameController(gameView);
        
        initFrame();
    }
    
    private void initFrame(){
        frame.setResizable(false);
        frame.setTitle("IAmTheAntivirus");
        
        frame.add(gameView);
        // this method has to be called after add() and before setLocationRelativeTo()
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //frame.add(gameView);
    }
    
    public static void main(String[] args){
        EventQueue.invokeLater(() -> {
            IAmTheAntivirus application = new IAmTheAntivirus();
            application.frame.setVisible(true);
        });
    }

}
