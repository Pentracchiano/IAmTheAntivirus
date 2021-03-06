/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.gameovermenu;


import controllers.IAmTheAntivirus;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JDialog;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import menu.AbstractMenuViewController;
import menu.RetroButton;
import utilities.FocusTraversalKeysUtilities;
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
        
        
       
        initRetroButtons();
        FocusTraversalKeysUtilities.changeFocusTraversalKeys(this);
        
    }
    
    private void initRetroButtons(){
        
        final FocusListener buttonFocusHandler = new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                RetroButton target = (RetroButton) e.getSource();
                target.toggleColors();
            }

            @Override
            public void focusLost(FocusEvent e) {
                RetroButton target = (RetroButton) e.getSource();
                target.toggleColors();}
        };
        
        retryButton.addFocusListener(buttonFocusHandler);
        backToMainMenuButton.addFocusListener(buttonFocusHandler);
        
  
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jDialog2 = new javax.swing.JDialog();
        subTitle = new javax.swing.JLabel();
        retryButton = new menu.RetroButton();
        title = new javax.swing.JLabel();
        backToMainMenuButton = new menu.RetroButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setBackground(new java.awt.Color(0, 0, 255));
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        subTitle.setBackground(new java.awt.Color(0, 0, 255));
        subTitle.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        subTitle.setForeground(new java.awt.Color(255, 255, 255));
        subTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subTitle.setText("An error occurred, a virus may have infected your system");

        retryButton.setBackground(new java.awt.Color(0, 0, 255));
        retryButton.setForeground(new java.awt.Color(255, 255, 255));
        retryButton.setText("retry");
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

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        retryButton.requestFocusInWindow();
    }//GEN-LAST:event_formFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private menu.RetroButton backToMainMenuButton;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private menu.RetroButton retryButton;
    private javax.swing.JLabel subTitle;
    private javax.swing.JLabel title;
    // End of variables declaration//GEN-END:variables
}
