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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

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
                System.out.println("SET Menu RIght: ");
                account.displayUserInfo();
                User_Avatar.setBorderColor(Color.GREEN);
                User_Avatar.setImage(new ImageIcon(account.getAvartar().getAbsolutePath()));
                txt_Username.setText(account.getUsername());
            }

            @Override
            public void logout() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        PublicEvent.getInstance().getEventMain().showLoading(true);
                        try {
                            Thread.sleep(1000);
                            eventSendRequestLogOut();
                            
                            PublicEvent.getInstance().getEventMain().destroyChat();
                            PublicEvent.getInstance().getEventMain().showLogin(true);
                            PublicEvent.getInstance().getEventMain().showLoading(false);
                        } catch (InterruptedException e) {
                        }

                    }
                }).start();
            }
        });
    }

    private void eventSendRequestLogOut() {
        try {
            Client client = ClientSocket.getInstanceClientSocket().getClient();
            MessageType messageType = new MessageType(1, "logout");
            Message message = new Message(5, messageType);
            client.reqMessage = message;
            client.runRequestThread();
        } catch (Exception e) {
        }
    }

    private void eventLogout() {
         ImageIcon icon = new ImageIcon("src/Icons/logout.png");
        int chooser = JOptionPane.showConfirmDialog(this, 
                "Are you sure want to \"Log out\"? ", "Log out!", 
                JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,icon);
        if(chooser == 0){
            //Yes
            PublicEvent.getInstance().getEventMenuRight().logout();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        User_Avatar = new swing.ImageAvatar();
        txt_Username = new javax.swing.JLabel();
        btn_Logout = new javax.swing.JLabel();

        setBackground(new java.awt.Color(249, 249, 249));

        User_Avatar.setImage(new javax.swing.ImageIcon(getClass().getResource("/Images/AccountImg.jpg"))); // NOI18N

        txt_Username.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txt_Username.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        txt_Username.setText("Username");

        btn_Logout.setBackground(new java.awt.Color(249, 249, 249));
        btn_Logout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btn_Logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/logout.png"))); // NOI18N
        btn_Logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_Logout.setOpaque(true);
        btn_Logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_LogoutMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btn_LogoutMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btn_LogoutMouseExited(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(User_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
            .addComponent(txt_Username, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btn_Logout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(User_Avatar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txt_Username)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 504, Short.MAX_VALUE)
                .addComponent(btn_Logout)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btn_LogoutMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogoutMouseEntered
        this.btn_Logout.setBackground(new Color(240, 240, 240));
    }//GEN-LAST:event_btn_LogoutMouseEntered

    private void btn_LogoutMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogoutMouseExited
        try {
            Thread.sleep(300);
        } catch (Exception e) {
        }
        this.btn_Logout.setBackground(new Color(249, 249, 249));
    }//GEN-LAST:event_btn_LogoutMouseExited

    private void btn_LogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_LogoutMouseClicked
        eventLogout();
    }//GEN-LAST:event_btn_LogoutMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ImageAvatar User_Avatar;
    private javax.swing.JLabel btn_Logout;
    private javax.swing.JLabel txt_Username;
    // End of variables declaration//GEN-END:variables
}
