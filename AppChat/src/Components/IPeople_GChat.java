package Components;

import Class.Account;
import Events.PublicEvent;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class IPeople_GChat extends javax.swing.JPanel {

    public Account getUser() {
        return user;
    }
    private boolean mouseOver;
    private Account user;

    public IPeople_GChat(Account user) {
        this.user = user;
        initComponents();
        lb.setText(user.getUsername());
        imageAvatar2.setBorderColor(Color.BLACK);
        activeStatus.setActive(user.isIsOnline());
        this.imageAvatar2.setImage(new ImageIcon(user.getAvartar().getAbsolutePath()));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        init();
    }

    public void updateStatus(boolean isOnline) {
        activeStatus.setActive(isOnline);
    }

    private void init() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(new Color(230, 230, 230));
                mouseOver = true;
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(new Color(242, 242, 242));
                mouseOver = false;
            }

           
            private boolean isChooser = false;

            @Override
            public void mouseClicked(MouseEvent me) {
                if (!isChooser) {
                    PublicEvent.getInstance().getEventCreateGroupChat().addUserIntoGroup(user);
                    imageAvatar2.setBorderColor(Color.yellow);
                } else {
                    PublicEvent.getInstance().getEventCreateGroupChat().removeUserFromGroup(user);
                    imageAvatar2.setBorderColor(Color.BLACK);
                }
                
                isChooser = !isChooser;
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        lbStatus = new javax.swing.JLabel();
        imageAvatar2 = new swing.ImageAvatar();
        activeStatus = new swing.ActiveStatus();

        setBackground(new java.awt.Color(242, 242, 242));
        setMinimumSize(new java.awt.Dimension(205, 50));
        setPreferredSize(new java.awt.Dimension(205, 50));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb.setFont(new java.awt.Font("sansserif", 0, 14)); // NOI18N
        lb.setText("Name");
        add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(61, 3, 132, -1));

        lbStatus.setFont(new java.awt.Font("sansserif", 2, 12)); // NOI18N
        lbStatus.setForeground(new java.awt.Color(117, 117, 117));
        lbStatus.setText("New User");
        add(lbStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, -1, -1));

        imageAvatar2.setImage(new javax.swing.ImageIcon(getClass().getResource("/Icons/AppIcon.png"))); // NOI18N
        add(imageAvatar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 44, 44));

        activeStatus.setActive(true);
        add(activeStatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 10, 30));
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ActiveStatus activeStatus;
    private swing.ImageAvatar imageAvatar2;
    private javax.swing.JLabel lb;
    private javax.swing.JLabel lbStatus;
    // End of variables declaration//GEN-END:variables
}
