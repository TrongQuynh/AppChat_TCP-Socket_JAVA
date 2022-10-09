package form;

import Class.Account;
import Class.AccountList;
import Class.ClientSocket;
import Components.IPeople_GChat;
import Components.Item_People;
import Events.EventCreateGroupChat;
import Events.PublicEvent;
import java.util.HashSet;
import java.util.Set;
import swing.ScrollBar;
import net.miginfocom.swing.MigLayout;

public class Menu_ChooseUser extends javax.swing.JPanel {
    
    private AccountList accList;
    private Set<Account> userInNewGroup;
    
    public Set<Account> getUserInNewGroup(){
        return this.userInNewGroup;
    }
    
    public Menu_ChooseUser() {
        initComponents();
        init();
    }
    
    private void init() {
        sp.setVerticalScrollBar(new ScrollBar());
        menuList.setLayout(new MigLayout("fillx", "0[]0", "0[]0"));
        
        accList = ClientSocket.getInstanceClientSocket().getAccountList();
        userInNewGroup = new HashSet<Account>();
        
        PublicEvent.getInstance().addEventCreateGroupChat(new EventCreateGroupChat() {
            @Override
            public void addUserIntoGroup(Account account) {
                userInNewGroup.add(account);
            }

            @Override
            public void removeUserFromGroup(Account account) {
                userInNewGroup.remove(account);
            }
            
        
        });
        
        menuMessage.setSelected(true);
        showMessage();
    }
    
    private void showMessage() {
        Account currentaAAcc = ClientSocket.getInstanceClientSocket().getClient().getUserAccount();
        menuList.removeAll();
        for (Account acc : accList.getAccountList()) {
            if (currentaAAcc.isSameAccount(acc)) {
                continue;
            }
            menuList.add(new IPeople_GChat(acc), "wrap");
            refreshMenuList();
        }
    }
    
    private void refreshMenuList() {
        menuList.repaint();
        menuList.revalidate();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JLayeredPane();
        menuMessage = new Components.MenuButton();
        sp = new javax.swing.JScrollPane();
        menuList = new javax.swing.JLayeredPane();

        setBackground(new java.awt.Color(242, 242, 242));
        setPreferredSize(new java.awt.Dimension(200, 636));

        menu.setBackground(new java.awt.Color(229, 229, 229));
        menu.setOpaque(true);
        menu.setLayout(new java.awt.GridLayout(1, 3));

        menuMessage.setIconSelected(new javax.swing.ImageIcon(getClass().getResource("/Icons/message_selected.png"))); // NOI18N
        menuMessage.setIconSimple(new javax.swing.ImageIcon(getClass().getResource("/Icons/message.png"))); // NOI18N
        menuMessage.setSelected(true);
        menuMessage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuMessageActionPerformed(evt);
            }
        });
        menu.add(menuMessage);

        sp.setBackground(new java.awt.Color(242, 242, 242));
        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        menuList.setBackground(new java.awt.Color(242, 242, 242));
        menuList.setOpaque(true);

        javax.swing.GroupLayout menuListLayout = new javax.swing.GroupLayout(menuList);
        menuList.setLayout(menuListLayout);
        menuListLayout.setHorizontalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        menuListLayout.setVerticalGroup(
            menuListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 576, Short.MAX_VALUE)
        );

        sp.setViewportView(menuList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu)
            .addComponent(sp, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sp)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void menuMessageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuMessageActionPerformed
        if (!menuMessage.isSelected()) {
           accList = ClientSocket.getInstanceClientSocket().getAccountList();
           showMessage();
        }
    }//GEN-LAST:event_menuMessageActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLayeredPane menu;
    private javax.swing.JLayeredPane menuList;
    private Components.MenuButton menuMessage;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
