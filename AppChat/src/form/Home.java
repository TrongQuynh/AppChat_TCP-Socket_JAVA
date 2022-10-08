package form;

import Class.Account;
import Class.GroupChat;
import net.miginfocom.swing.MigLayout;

public class Home extends javax.swing.JLayeredPane {

    private Chat chat;
    
    public Home() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx, filly", "0[200!]5[fill, 100%]5[200!]0", "0[fill]0"));
        chat = new Chat();
        this.add(new Menu_Right());
        this.add(chat);
        this.add(new Menu_Left());
    }

    public void setUser(Account user) {
        chat.setUser(user);
        chat.setVisible(true);
    }
    
    public void setGroup(GroupChat groupChat){
        chat.setGroup(groupChat);
        chat.setVisible(true);
    }
    
    public void selectAllUser(){
        chat.setAlluser();
        chat.setVisible(true);
    }

    public void updateUser(Account user) {
        chat.updateUser(user);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1007, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 551, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
