/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Class.Account;
import Class.Client;
import Class.ClientSocket;
import Class.Message;
import Class.MessageType;
import Events.EventMenuRight;
import Events.PublicEvent;
import java.awt.Color;
import javax.swing.ImageIcon;

/**
 *
 * @author Trong Quynh
 */
public class Menu_Right extends javax.swing.JPanel {

    public Menu_Right() {
        initComponents();
        PublicEvent.getInstance().addEventMenuRight(new EventMenuRight() {
            @Override
            public void setUser(Account account) {
                User_Avatar.setBorderColor(Color.GREEN);
                User_Avatar.setImage(new ImageIcon(account.getAvartar().getAbsolutePath()));
                txt_Username.setText(account.getUsername());
            }

            @Override
            public void logout() {
                try {
                    Client client = ClientSocket.getInstanceClientSocket().getClient();
                    MessageType messageType = new MessageType(1, "logout");
                    Message message = new Message(5, messageType);
                    client.reqMessage = message;
                    client.runRequestThread();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        User_Avatar = new swing.ImageAvatar();
        txt_Username = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));

        User_Avatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/Images/AccountImg.jpg"))); // NOI18N

        txt_Username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_Username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Username.setText("Username");

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/logout.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(User_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addComponent(txt_Username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(User_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_Username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 504, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ImageAvatar User_Avatar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel txt_Username;
    // End of variables declaration//GEN-END:variables
}
