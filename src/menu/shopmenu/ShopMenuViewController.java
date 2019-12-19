/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.shopmenu;

import controllers.IAmTheAntivirus;
import controllers.game.GameController;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JLabel;
import javax.swing.JPanel;
import menu.RetroButton;
import models.GameStatus;
import models.shop.Stat;

/**
 *
 * @author gabri
 */
public class ShopMenuViewController extends javax.swing.JPanel {
    
    private List<ShopItemView> items = new ArrayList<>();
    private GameStatus gameStatus = GameStatus.getInstance();
    
    
    /**
     * Creates new form ShopMenuViewController
     */
    public ShopMenuViewController() {
        this.setPreferredSize(new Dimension(1300,747));
        
        initComponents();
        initItems();
       
        
        this.titleLabel.setFont(new Font("Minecraft", Font.BOLD, 72));
        this.moneyLabel.setFont(new Font("Minecraft",Font.BOLD, 32));
        this.updateMoneyLabel();
        
        this.addButtonListener();
    }
    
    private void initItems(){
        List<Stat> stats = gameStatus.getStats();
        
        for( Stat s : stats ){
            ShopItemView newShopItem = new ShopItemView(s);
            items.add(newShopItem);
        }
        
        for( ShopItemView s : items ){
            System.out.println(s.getPreferredSize());
            s.setSize(s.getPreferredSize());
            this.shelfPanel.add(s);
            s.setVisible(true);
        }
    }
    
    private void addButtonListener(){
        
        final ActionListener buttonActionHandler = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RetroButton target = (RetroButton) e.getSource();
                
                ShopItemView shopItem = (ShopItemView) target.getParent();
                JPanel shelf = (JPanel)shopItem.getParent();
                ShopMenuViewController shop = (ShopMenuViewController) shelf.getParent();
                shop.buyItem(shopItem);
            }
        };
        
        for( ShopItemView s : items ){
            s.getPlusButton().addActionListener(buttonActionHandler);
        }
        
    }
    
    private void buyItem(ShopItemView shopItem){
        Stat s = shopItem.getStat();
        int balance = gameStatus.getBitcoins();
        int cost = s.getCost();
        if( balance-cost >= 0 )
        {
            s.buy();
            gameStatus.withdrawBitcoins(cost);
            this.updateMoneyLabel();
            EventQueue.invokeLater(() -> {
             shopItem.updateValues();
            });
        }
        
       
    }
    
    public void updateMoneyLabel(){
        this.moneyLabel.setText(Integer.toString(this.gameStatus.getBitcoins()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nextWaveButton = new menu.RetroButton();
        shelfPanel = new javax.swing.JPanel();
        titleLabel = new javax.swing.JLabel();
        moneyLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(0, 0, 0));
        setPreferredSize(new java.awt.Dimension(1300, 747));
        addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                formAncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
                formAncestorRemoved(evt);
            }
        });
        setLayout(null);

        nextWaveButton.setText("Start next Wave!");
        nextWaveButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nextWaveButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nextWaveButtonFocusLost(evt);
            }
        });
        nextWaveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextWaveButtonActionPerformed(evt);
            }
        });
        add(nextWaveButton);
        nextWaveButton.setBounds(360, 640, 640, 90);

        shelfPanel.setOpaque(false);
        shelfPanel.setLayout(new java.awt.GridLayout());
        add(shelfPanel);
        shelfPanel.setBounds(0, 190, 1300, 430);

        titleLabel.setFont(new java.awt.Font("Arial", 0, 48)); // NOI18N
        titleLabel.setForeground(java.awt.Color.green);
        titleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titleLabel.setText("Deep Web Shop");
        add(titleLabel);
        titleLabel.setBounds(0, 0, 1300, 130);

        moneyLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        moneyLabel.setForeground(new java.awt.Color(255, 204, 51));
        moneyLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        moneyLabel.setText("MONEY");
        add(moneyLabel);
        moneyLabel.setBounds(0, 120, 1300, 70);
    }// </editor-fold>//GEN-END:initComponents

    private void formAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorAdded
        this.updateMoneyLabel();
    }//GEN-LAST:event_formAncestorAdded

    private void nextWaveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextWaveButtonActionPerformed
        IAmTheAntivirus iata = IAmTheAntivirus.getGameInstance();
        iata.closeShopMenu();
    }//GEN-LAST:event_nextWaveButtonActionPerformed

    private void nextWaveButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nextWaveButtonFocusGained
        nextWaveButton.toggleColors();
    }//GEN-LAST:event_nextWaveButtonFocusGained

    private void nextWaveButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nextWaveButtonFocusLost
        nextWaveButton.toggleColors();
    }//GEN-LAST:event_nextWaveButtonFocusLost

    private void formAncestorRemoved(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_formAncestorRemoved
        // TODO add your handling code here:
    }//GEN-LAST:event_formAncestorRemoved

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel moneyLabel;
    private menu.RetroButton nextWaveButton;
    private javax.swing.JPanel shelfPanel;
    private javax.swing.JLabel titleLabel;
    // End of variables declaration//GEN-END:variables
}