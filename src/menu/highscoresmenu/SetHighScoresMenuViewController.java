/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.highscoresmenu;

import controllers.IAmTheAntivirus;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import menu.AbstractMenuViewController;
import menu.RetroButton;
import models.GameStatus;
import utilities.FocusTraversalKeysUtilities;

/**
 *
 * @author manud
 */
public class SetHighScoresMenuViewController extends AbstractMenuViewController {

    /**
     * Creates new form SetHighScoresMenuViewController
     */
    
    private static final String HIGHSCORES_FILE_PATH = "src/resources/highscores/highscores.txt";
    private String name;
    private int score;
    
    public SetHighScoresMenuViewController(Dimension dimension) {
        super(dimension);
        this.setFocusCycleRoot(true);
        
        initComponents();
        
        FocusTraversalKeysUtilities.changeFocusTraversalKeys(this);
        this.score = GameStatus.getInstance().getScore();
        EventQueue.invokeLater(() -> {
            nameField.setCaretColor(java.awt.Color.GREEN);
            scoreLabel.setText(Integer.toString(this.score));
        });
        
        initRetroButtons();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        nameField = new javax.swing.JTextField();
        confirmButton = new menu.RetroButton();
        scoreLabel = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(1300, 747));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Minecraft", java.awt.Font.BOLD, 60));
        jLabel1.setForeground(java.awt.Color.orange);
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Insert your name");

        nameField.setBackground(new java.awt.Color(0, 0, 0));
        nameField.setFont(new java.awt.Font("Minecraft", java.awt.Font.BOLD, 60)
        );
        nameField.setForeground(new java.awt.Color(255, 255, 255));
        nameField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameField.setBorder(null);
        nameField.setName(""); // NOI18N
        nameField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameFieldMouseClicked(evt);
            }
        });
        nameField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameFieldKeyPressed(evt);
            }
        });

        confirmButton.setText("OK");
        confirmButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmButtonActionPerformed(evt);
            }
        });
        confirmButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                confirmButtonKeyPressed(evt);
            }
        });

        scoreLabel.setBackground(new java.awt.Color(0, 0, 0));
        scoreLabel.setFont(new java.awt.Font("Minecraft", java.awt.Font.BOLD, 45));
        scoreLabel.setForeground(new java.awt.Color(255, 255, 255));
        scoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        scoreLabel.setText("SCORE");

        jLabel2.setFont(new java.awt.Font("Minecraft", java.awt.Font.BOLD, 60));
        jLabel2.setForeground(java.awt.Color.orange);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Score");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(320, 320, 320)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 566, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(scoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 660, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 360, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(320, 320, 320))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(scoreLabel)
                .addGap(30, 30, 30)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(nameField, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(74, 74, 74)
                .addComponent(confirmButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(124, Short.MAX_VALUE))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {nameField, scoreLabel});

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2});

    }// </editor-fold>//GEN-END:initComponents

    private void confirmButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmButtonActionPerformed
        EventQueue.invokeLater(() -> {
            if(nameField.getText().isEmpty() || nameField.getText().startsWith(" ")){
                this.name = "-----";
            }
            else{
                this.name = nameField.getText();
            }
            
            saveHighScoresOnFile();
            IAmTheAntivirus.getGameInstance().closeSetHighScoresMenu();
        });
    }//GEN-LAST:event_confirmButtonActionPerformed

    private void nameFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameFieldMouseClicked
        nameField.requestFocusInWindow();
    }//GEN-LAST:event_nameFieldMouseClicked

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        this.requestFocusInWindow();
    }//GEN-LAST:event_formAncestorAdded

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        this.nameField.requestFocusInWindow();
    }//GEN-LAST:event_formFocusGained

    private void nameFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameFieldKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER || evt.getKeyCode()==KeyEvent.VK_SPACE)
            confirmButton.requestFocusInWindow();
    }//GEN-LAST:event_nameFieldKeyPressed

    private void confirmButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_confirmButtonKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER)
            this.confirmButtonActionPerformed(null);
    }//GEN-LAST:event_confirmButtonKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private menu.RetroButton confirmButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField nameField;
    private javax.swing.JLabel scoreLabel;
    // End of variables declaration//GEN-END:variables

    private void saveHighScoresOnFile() {
        
        int i = 0;
        boolean replaced = false;
        
        while(i < 5 && !replaced){
            GameStatus status = GameStatus.getInstance();
            if (Integer.parseInt(status.getHighscores().get(i).split(";")[1]) < this.score){
                status.getHighscores().add(i, this.name + ";" + Integer.toString(this.score));
                status.getHighscores().remove(5);
                replaced = true;
            }else{
                i++;
            }
        }
        try {
                BufferedWriter out = new BufferedWriter(new FileWriter(HIGHSCORES_FILE_PATH));
                for(int j = 0; j < 5; j++){
                    out.write(GameStatus.getInstance().getHighscores().get(j).split(";")[0] + " " + GameStatus.getInstance().getHighscores().get(j).split(";")[1] + "\n");
                }
                out.close();
            }
        catch (IOException ex) {
                Logger.getLogger(SetHighScoresMenuViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        
        confirmButton.addFocusListener(buttonFocusHandler);
        
    }
    
}