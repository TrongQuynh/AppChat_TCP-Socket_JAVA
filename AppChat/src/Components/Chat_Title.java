package Components;

import Class.Account;
import Class.GroupChat;
import java.awt.Color;

public class Chat_Title extends javax.swing.JPanel {

    private Account account;
    private GroupChat groupChat;
    private Account AllUser;

    public Chat_Title() {
        initComponents();
//        this.lbName.setText("Server");
//        this.AllUser = new Account("Server");
    }

    public void setUserName(Account account) {
        this.account = account;
        lbName.setText(account.getUsername());
        if (account.isIsOnline()) {
            statusActive();
        } else {
            setStatusText("Offline");
        }
    }
    
    public void setGroupName(GroupChat groupChat) {
        this.groupChat = groupChat;
        lbName.setText(groupChat.getGroupName());
        if (true) {
            statusActive();
        } else {
            setStatusText("Offline");
        }
    }
    
    public void setAllUser(Account AllUser){
        System.out.println("SET ALL USERS: " + AllUser.getUsername());
        lbName.setText(AllUser.getUsername());
        this.AllUser = AllUser;
        statusActive();
    }

    public Account getAccount() {
        return account;
    }
    
    public GroupChat getGroupChat(){
        return this.groupChat;
    }
    
    public Account getAllUser(){
        return this.AllUser;
    }

    public void updateUser(Account account) {
        if (this.account == account) {
            lbName.setText(account.getUsername());
            if (account.isIsOnline()) {
                statusActive();
            } else {
                setStatusText("Offline");
            }
        }
    }

    public void statusActive() {
        lbStatus.setText("Active now");
        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
    }

    public void setStatusText(String text) {
        lbStatus.setText(text);
        lbStatus.setForeground(new Color(160, 160, 160));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        layer = new javax.swing.JLayeredPane();
        lbName = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();

        setBackground(new java.awt.Color(229, 229, 229));

        layer.setLayout(new java.awt.GridLayout(0, 1));

        lbName.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        lbName.setForeground(new java.awt.Color(66, 66, 66));
        lbName.setText("Name");
        layer.add(lbName);

        lbStatus.setForeground(new java.awt.Color(40, 147, 59));
        lbStatus.setText("Active now");
        layer.add(lbStatus);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(406, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(layer, javax.swing.GroupLayout.PREFERRED_SIZE, 34, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane layer;
    private javax.swing.JLabel lbName;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
