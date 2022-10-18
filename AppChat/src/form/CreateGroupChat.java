/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package form;

import Class.Account;
import Class.ClientSocket;
import Class.GroupChat;
import Class.Message;
import Class.MessageType;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import net.miginfocom.swing.MigLayout;

/**
 *
 * @author Trong Quynh
 */
public class CreateGroupChat extends javax.swing.JFrame {

    /**
     * Creates new form CreateGroupChat
     */
    private Menu_ChooseUser menuChooser;

    public CreateGroupChat() {
        initComponents();
        this.panel_ItemUser.setLayout(new MigLayout("fillx, filly", "0[200!]5[fill, 100%]5[200!]0", "0[fill]0"));
        this.menuChooser = new Menu_ChooseUser();
        this.panel_ItemUser.add(this.menuChooser);

    }
    private int pX;
    private int pY;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel_TitleBar = new javax.swing.JPanel();
        btn_Close = new javax.swing.JLabel();
        btn_mini = new javax.swing.JLabel();
        Container = new javax.swing.JPanel();
        panel_ItemUser = new javax.swing.JPanel();
        txt_GroupName = new javax.swing.JTextField();
        btn_CreateGroup = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_TitleBar.setBackground(new java.awt.Color(102, 102, 102));
        panel_TitleBar.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                panel_TitleBarMouseDragged(evt);
            }
        });
        panel_TitleBar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                panel_TitleBarMousePressed(evt);
            }
        });
        panel_TitleBar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btn_Close.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/close.png"))); // NOI18N
        btn_Close.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CloseMouseClicked(evt);
            }
        });
        panel_TitleBar.add(btn_Close, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 0, -1, -1));

        btn_mini.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/minimize.png"))); // NOI18N
        btn_mini.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_miniMouseClicked(evt);
            }
        });
        panel_TitleBar.add(btn_mini, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 0, -1, 20));

        getContentPane().add(panel_TitleBar, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 550, 20));

        Container.setBackground(new java.awt.Color(204, 204, 204));
        Container.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel_ItemUser.setBackground(new java.awt.Color(153, 153, 153));

        javax.swing.GroupLayout panel_ItemUserLayout = new javax.swing.GroupLayout(panel_ItemUser);
        panel_ItemUser.setLayout(panel_ItemUserLayout);
        panel_ItemUserLayout.setHorizontalGroup(
            panel_ItemUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 200, Short.MAX_VALUE)
        );
        panel_ItemUserLayout.setVerticalGroup(
            panel_ItemUserLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 380, Short.MAX_VALUE)
        );

        Container.add(panel_ItemUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 200, 380));
        Container.add(txt_GroupName, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 200, -1));

        btn_CreateGroup.setText("Create Group");
        btn_CreateGroup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btn_CreateGroupMouseClicked(evt);
            }
        });
        Container.add(btn_CreateGroup, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 120, -1, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Group Name");
        Container.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 60, -1, -1));

        getContentPane().add(Container, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 550, 380));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void panel_TitleBarMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_TitleBarMouseDragged
        this.setLocation(this.getLocation().x + evt.getX() - pX, this.getLocation().y + evt.getY() - pY);
    }//GEN-LAST:event_panel_TitleBarMouseDragged

    private void panel_TitleBarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panel_TitleBarMousePressed
        pX = evt.getX();
        pY = evt.getY();
    }//GEN-LAST:event_panel_TitleBarMousePressed

    private void btn_CloseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CloseMouseClicked
        dispose();
    }//GEN-LAST:event_btn_CloseMouseClicked

    private void btn_miniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_miniMouseClicked
        this.setState(JFrame.ICONIFIED);
    }//GEN-LAST:event_btn_miniMouseClicked

    private void btn_CreateGroupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btn_CreateGroupMouseClicked
        int numberUserInList = this.menuChooser.getUserInNewGroup().size();
        String groupName = this.txt_GroupName.getText();
        Set<Account> groupChat = this.menuChooser.getUserInNewGroup();
        if (numberUserInList < 3 || groupName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Error when create new group chat", "ERROR",
                    JOptionPane.OK_OPTION, new ImageIcon("src/Icons/group_selected.png"));
        } else {
            try {
                GroupChat newGChat = new GroupChat(groupName, groupChat);
                //add account create group chat into group
                newGChat.addNewUser(ClientSocket.getInstanceClientSocket().getClient().getUserAccount());
                MessageType mType = new MessageType(7, newGChat);
                Message message = new Message(4, mType);
                ClientSocket.getInstanceClientSocket().getClient().reqMessage = message;
                ClientSocket.getInstanceClientSocket().getClient().runRequestThread();
                JOptionPane.showMessageDialog(this, "Create new group chat success", "Notificate",
                        JOptionPane.OK_OPTION, new ImageIcon("src/Icons/group_selected.png"));
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_btn_CreateGroupMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CreateGroupChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CreateGroupChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CreateGroupChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CreateGroupChat.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CreateGroupChat().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Container;
    private javax.swing.JLabel btn_Close;
    private javax.swing.JButton btn_CreateGroup;
    private javax.swing.JLabel btn_mini;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel_ItemUser;
    private javax.swing.JPanel panel_TitleBar;
    private javax.swing.JTextField txt_GroupName;
    // End of variables declaration//GEN-END:variables
}
