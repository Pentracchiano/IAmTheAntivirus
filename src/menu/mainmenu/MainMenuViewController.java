/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.mainmenu;

import controllers.IAmTheAntivirus;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyboardFocusManager;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.KeyStroke;
import menu.AbstractMenuViewController;
import menu.MusicButton;
import menu.RetroButton;
import utilities.FocusTraversalKeysUtilities;
import utilities.ImageUtilities;

/**
 *
 * @author Emanuele
 */
public class MainMenuViewController extends AbstractMenuViewController {
    
    private static final double IMAGE_SCREEN_TOP_LEFT_X_SCREEN_RATIO = 0.4956;
    private static final double IMAGE_SCREEN_TOP_LEFT_Y_SCREEN_RATIO = 0.2899;
    private static final double IMAGE_SCREEN_WIDTH_SCREEN_RATIO = 0.31789;
    private static final double IMAGE_SCREEN_TOP_TITLE_MARGIN_RATIO = 0.05;
    
    private static final String BACKGROUND_IMAGE_MENU_PATH = "src/resources/background/backgroundMenu.jpg";
    private final Image backgroundImage;

    /**
     * Creates new form MainMenuViewController
     *
     * @param preferredSize
     */
    public MainMenuViewController(Dimension preferredSize) {
        super(preferredSize);
        //this.setBackground(Color.black);

        this.backgroundImage = ImageUtilities.loadImageFromPath(BACKGROUND_IMAGE_MENU_PATH);
        initComponents();
       
        initRetroButtons();
        FocusTraversalKeysUtilities.changeFocusTraversalKeys(this);
        
        // needed to place the label relatively to the image and the mainmenu preferredsize
        this.titleLabel.setBounds((this.getPreferredSize().width - this.titleLabel.getPreferredSize().width) / 2,
                (int) (IMAGE_SCREEN_TOP_TITLE_MARGIN_RATIO * this.getPreferredSize().height),
                this.titleLabel.getPreferredSize().width,
                this.titleLabel.getPreferredSize().height);
        
        // this makes the music button reflect the actual state of the music
        musicButton.setSelected(IAmTheAntivirus.getGameInstance().isMusicDisabled());
        
    }
    
    private void initRetroButtons(){
        this.setButtonBounds(playGameButton,0);
        this.setButtonBounds(highScoresButton, highScoresButton.getPreferredSize().height+30);
        this.setButtonBounds(exitGameButton,exitGameButton.getPreferredSize().height+56+30+30);
        
        
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
        
        playGameButton.addFocusListener(buttonFocusHandler);
        exitGameButton.addFocusListener(buttonFocusHandler);
        highScoresButton.addFocusListener(buttonFocusHandler);
    
    }
    
    // needed to place the label relatively to the image and the mainmenu preferredsize
    private void setButtonBounds( JButton button, int padding ){
                
        int buttonWidth = (int)(button.getPreferredSize().width);
        int buttonHeight = button.getPreferredSize().height;
        
        int x0 = (int)(IMAGE_SCREEN_TOP_LEFT_X_SCREEN_RATIO * this.getPreferredSize().width);
        int y0 = (int)(IMAGE_SCREEN_TOP_LEFT_Y_SCREEN_RATIO * this.getPreferredSize().height);
        
        int leftMargin = (int)((IMAGE_SCREEN_WIDTH_SCREEN_RATIO * this.getPreferredSize().width - buttonWidth) / 2);
        int x = x0 + leftMargin;
        
        int topMargin = buttonHeight;
        int y = y0 + topMargin + padding;
        
        button.setBounds(x, y, buttonWidth, buttonHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
        //g.fillRect((int)(0.4956*this.getWidth()), (int)(0.2899*this.getHeight()), (int)(0.3179*this.getWidth()), (int)(0.4406*this.getHeight()));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        exitGameButton = new menu.RetroButton();
        titleLabel = new javax.swing.JLabel();
        playGameButton = new menu.RetroButton();
        musicButton = new menu.MusicButton();
        highScoresButton = new menu.RetroButton();

        setOpaque(false);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                formFocusGained(evt);
            }
        });
        setLayout(null);

        exitGameButton.setBackground(new java.awt.Color(4, 55, 98));
        exitGameButton.setText("exit");
        exitGameButton.setPreferredSize(new java.awt.Dimension(205, 56));
        exitGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitGameButtonActionPerformed(evt);
            }
        });
        exitGameButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                exitGameButtonKeyPressed(evt);
            }
        });
        add(exitGameButton);
        exitGameButton.setBounds(250, 390, 300, 56);

        titleLabel.setBackground(java.awt.Color.black);
        titleLabel.setFont(new java.awt.Font("Minecraft", java.awt.Font.BOLD, 80));
        titleLabel.setForeground(java.awt.Color.green);
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("I Am The Antivirus");
        add(titleLabel);
        titleLabel.setBounds(200, 30, 910, 103);

        playGameButton.setBackground(new java.awt.Color(4, 55, 98));
        playGameButton.setText("play");
        playGameButton.setMaximumSize(new java.awt.Dimension(100, 56));
        playGameButton.setPreferredSize(new java.awt.Dimension(230, 56));
        playGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playGameButtonActionPerformed(evt);
            }
        });
        playGameButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                playGameButtonKeyPressed(evt);
            }
        });
        add(playGameButton);
        playGameButton.setBounds(250, 250, 300, 56);

        musicButton.setText("musicButton2");
        musicButton.setActionCommand("musicButton");
        musicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                musicButtonActionPerformed(evt);
            }
        });
        add(musicButton);
        musicButton.setBounds(40, 590, 70, 70);

        highScoresButton.setBackground(new java.awt.Color(4, 55, 98));
        highScoresButton.setText("high scores");
        highScoresButton.setPreferredSize(new java.awt.Dimension(270, 56));
        highScoresButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highScoresButtonActionPerformed(evt);
            }
        });
        highScoresButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                highScoresButtonKeyPressed(evt);
            }
        });
        add(highScoresButton);
        highScoresButton.setBounds(250, 320, 300, 56);
    }// </editor-fold>//GEN-END:initComponents

    private void exitGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitGameButtonActionPerformed
        IAmTheAntivirus gameApplication = IAmTheAntivirus.getGameInstance();
        gameApplication.closeGame();
    }//GEN-LAST:event_exitGameButtonActionPerformed

    private void playGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playGameButtonActionPerformed
        IAmTheAntivirus gameApplication = IAmTheAntivirus.getGameInstance();
        gameApplication.startGame();
    }//GEN-LAST:event_playGameButtonActionPerformed

    private void playGameButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_playGameButtonKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            playGameButtonActionPerformed(null);
        }
    }//GEN-LAST:event_playGameButtonKeyPressed

    private void exitGameButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_exitGameButtonKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            exitGameButtonActionPerformed(null);
        }
    }//GEN-LAST:event_exitGameButtonKeyPressed

    private void formFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_formFocusGained
        playGameButton.requestFocusInWindow();
    }//GEN-LAST:event_formFocusGained

    private void musicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_musicButtonActionPerformed
        IAmTheAntivirus gameApplication = IAmTheAntivirus.getGameInstance();
        if (musicButton.isSelected()){
            gameApplication.setMusicDisabled(true);
        } else {
            gameApplication.setMusicDisabled(false);
        }
    }//GEN-LAST:event_musicButtonActionPerformed

    private void highScoresButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highScoresButtonActionPerformed
        IAmTheAntivirus gameApplication = IAmTheAntivirus.getGameInstance();
        gameApplication.displayHighScoresMenu();
    }//GEN-LAST:event_highScoresButtonActionPerformed

    private void highScoresButtonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_highScoresButtonKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            highScoresButtonActionPerformed(null);
        }
    }//GEN-LAST:event_highScoresButtonKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private menu.RetroButton exitGameButton;
    private menu.RetroButton highScoresButton;
    private menu.MusicButton musicButton;
    private menu.RetroButton playGameButton;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}
