/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.gameovermenu;

import com.sun.glass.events.KeyEvent;
import controllers.IAmTheAntivirus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.util.HashSet;
import java.util.Set;
import javax.swing.KeyStroke;
import menu.AbstractMenuViewController;
import utilities.FontUtilities;

/**
 *
 * @author admin
 */
public class GameOverViewController extends AbstractMenuViewController {

    private Color backgroundColor = Color.BLUE;
    private Color foregroundColor = Color.WHITE;
    private Font minecraftFont = FontUtilities.registerFont("src/resources/fonts/Minecraft.ttf");
    
    public GameOverViewController(Dimension dimension) {
        super(dimension);
        initComponents();
       
        setBackground(backgroundColor);
        retryButton.setBackground(backgroundColor);
        retryButton.setForeground(foregroundColor);

        
        retryButton.setBackground(backgroundColor);
        retryButton.setForeground(foregroundColor);
        
        subTitle.setForeground(foregroundColor);
        subTitle.setBackground(backgroundColor);
        subTitle.setFont(new Font("Minecraft", Font.BOLD, 34));
        
        title.setForeground(foregroundColor);
        title.setBackground(backgroundColor);
        title.setFont(new Font("Minecraft", Font.BOLD, 60));
        changeFocusTraversalKeys();
       
    }
    
    private void changeFocusTraversalKeys() {
        Set<KeyStroke> forwardKeys = new HashSet<>();
        forwardKeys.add(KeyStroke.getKeyStroke("DOWN"));
        forwardKeys.add(KeyStroke.getKeyStroke("TAB"));
        
        Set<KeyStroke> backwardKeys = new HashSet<>();
        backwardKeys.add(KeyStroke.getKeyStroke("UP"));
        
        this.setFocusTraversalKeys(KeyboardFocusManager.FORWARD_TRAVERSAL_KEYS, forwardKeys);
        this.setFocusTraversalKeys(KeyboardFocusManager.BACKWARD_TRAVERSAL_KEYS, backwardKeys);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        subTitle = new javax.swing.JLabel();
        retryButton = new menu.RetroButton();
        title = new javax.swing.JLabel();
        backToMainMenuButton = new menu.RetroButton();

        setBackground(new java.awt.Color(0, 0, 255));

        subTitle.setBackground(new java.awt.Color(0, 0, 255));
        subTitle.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        subTitle.setForeground(new java.awt.Color(255, 255, 255));
        subTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subTitle.setText("An error occurred, a virus may have infected your system");

        retryButton.setBackground(new java.awt.Color(0, 0, 255));
        retryButton.setForeground(new java.awt.Color(255, 255, 255));
        retryButton.setText("retry");
        retryButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                retryButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                retryButtonFocusLost(evt);
            }
        });
        retryButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                retryButtonKeyPressed(evt);
            }
        });

        title.setBackground(new java.awt.Color(0, 0, 255));
        title.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        title.setForeground(new java.awt.Color(255, 255, 255));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("GAME OVER");

        backToMainMenuButton.setBackground(new java.awt.Color(0, 0, 255));
        backToMainMenuButton.setForeground(new java.awt.Color(255, 255, 255));
        backToMainMenuButton.setText("go back to main menu");
        backToMainMenuButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                backToMainMenuButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                backToMainMenuButtonFocusLost(evt);
            }
        });
        backToMainMenuButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                backToMainMenuButtonKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(subTitle, javax.swing.GroupLayout.DEFAULT_SIZE, 1300, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backToMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(retryButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(title)
                .addGap(29, 29, 29)
                .addComponent(subTitle)
                .addGap(132, 132, 132)
                .addComponent(retryButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(backToMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void retryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_retryButtonActionPerformed
       

    }//GEN-LAST:event_retryButtonActionPerformed

    private void backToMainMenuButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_backToMainMenuButtonFocusGained
        backToMainMenuButton.toggleColors();
    }//GEN-LAST:event_backToMainMenuButtonFocusGained

    private void backToMainMenuButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_backToMainMenuButtonKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            IAmTheAntivirus gameApplication = IAmTheAntivirus.getGameInstance();
            gameApplication.displayMainMenu();
        }
    }//GEN-LAST:event_backToMainMenuButtonKeyPressed

    private void retryButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_retryButtonKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            IAmTheAntivirus gameApplication = IAmTheAntivirus.getGameInstance();
            gameApplication.startGame();
        }
    }//GEN-LAST:event_retryButtonKeyPressed

    private void retryButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_retryButtonFocusGained
        retryButton.toggleColors();
    }//GEN-LAST:event_retryButtonFocusGained

    private void retryButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_retryButtonFocusLost
        retryButton.toggleColors();
    }//GEN-LAST:event_retryButtonFocusLost

    private void backToMainMenuButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_backToMainMenuButtonFocusLost
        backToMainMenuButton.toggleColors();
    }//GEN-LAST:event_backToMainMenuButtonFocusLost


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private menu.RetroButton backToMainMenuButton;
    private menu.RetroButton retryButton;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}