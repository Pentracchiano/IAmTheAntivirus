/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package menu.shopmenu;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import menu.RetroButton;
import models.shop.Stat;

/**
 *
 * @author gabri
 */
public class ShopItemView extends javax.swing.JPanel {

    /**
     * Creates new form MyComponent
     */
    private int margin;
    private String name;
    private Stat stat;
    
    public ShopItemView(Stat stat) {
        initComponents();
        this.stat = stat;
        this.costLabel.setText(Integer.toString(stat.getCost()));
        this.descriptionLabel.setText(stat.getDescription());
        this.nextValueLabel.setText(Integer.toString(stat.getNextValue()));
        this.nameLabel.setText(stat.getName());
    }

    public Stat getStat() {
        return stat;
    }

    public RetroButton getPlusButton() {
        return plusButton;
    }

    public void setPlusButton(RetroButton plusButton) {
        this.plusButton = plusButton;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }
    
    public void updateValues(){
        this.costLabel.setText(Integer.toString(stat.getCost()));
        this.descriptionLabel.setText(stat.getDescription());
        this.nextValueLabel.setText(Integer.toString(stat.getNextValue()));
        this.nameLabel.setText(stat.getName());
    }


    public void setDescriptionLabel(String value) {
        this.descriptionLabel.setText(value);
    }


    public void setNameLabel(String value) {
        this.nameLabel.setText(value);
    }

    public String getItemName() {
        return name;
    }

    
    
    public void setNextValueLabel(String value) {
        this.nextValueLabel.setText(value);
    }
    
    public void increaseCounter(){
        this.progressBar.increaseProgression();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        costLabel = new javax.swing.JLabel();
        descriptionLabel = new javax.swing.JLabel();
        nextValueLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        progressBar = new menu.shopmenu.ProgressBar();
        plusButton = new menu.RetroButton();

        setBackground(new java.awt.Color(0, 0, 0));
        setForeground(new java.awt.Color(255, 255, 255));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(830, 200));

        costLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        costLabel.setForeground(new java.awt.Color(255, 204, 0));
        costLabel.setText("500");

        descriptionLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        descriptionLabel.setForeground(new java.awt.Color(255, 255, 255));
        descriptionLabel.setText("Increase your max health to:");

        nextValueLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        nextValueLabel.setForeground(java.awt.Color.green);
        nextValueLabel.setText("50");

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        nameLabel.setForeground(new java.awt.Color(255, 255, 255));
        nameLabel.setText("Max Health:");
        nameLabel.setPreferredSize(new java.awt.Dimension(68, 50));

        javax.swing.GroupLayout progressBarLayout = new javax.swing.GroupLayout(progressBar);
        progressBar.setLayout(progressBarLayout);
        progressBarLayout.setHorizontalGroup(
            progressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 241, Short.MAX_VALUE)
        );
        progressBarLayout.setVerticalGroup(
            progressBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 47, Short.MAX_VALUE)
        );

        plusButton.setText("+");
        plusButton.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                plusButtonFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                plusButtonFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(costLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(descriptionLabel)
                        .addGap(5, 5, 5)
                        .addComponent(nextValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(plusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(costLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nextValueLabel)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void plusButtonFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_plusButtonFocusLost
        plusButton.toggleColors();
    }//GEN-LAST:event_plusButtonFocusLost

    private void plusButtonFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_plusButtonFocusGained
        plusButton.toggleColors();
    }//GEN-LAST:event_plusButtonFocusGained


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel costLabel;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel nextValueLabel;
    private menu.RetroButton plusButton;
    private menu.shopmenu.ProgressBar progressBar;
    // End of variables declaration//GEN-END:variables
}
