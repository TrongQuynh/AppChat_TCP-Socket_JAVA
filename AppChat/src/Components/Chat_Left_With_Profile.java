package Components;

import Events.PublicEvent;
import Helper.Helper;
import static com.sun.java.accessibility.util.AWTEventMonitor.addActionListener;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Chat_Left_With_Profile extends javax.swing.JLayeredPane {

    public Chat_Left_With_Profile() {
        initComponents();
        txt.setBackground(new Color(242, 242, 242));
    }
    
    private JPanel chatPanel;
    private byte[] fileContent;
    
    public void initEvent() {
        System.out.println("Download file at chat left with profile");
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                
            int chooser = JOptionPane.showConfirmDialog(chatPanel,
                        "Do you want download \"" + fileName + "\" ?","Download File",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("src/Icons/file.png")
                );
                System.out.println("Chosoer option: " + chooser);
                if(chooser == 0){
                    // Yes
                    PublicEvent.getInstance().getEventChat().downloadFile(fileContent, fileName);
                }else if(chooser == 1){
                    // No
                }
            }
        });
    }

    public void setUserProfile(String user) {
        txt.setUserProfile(user);
    }

    public void setImageProfile(Icon image) {
        IaImage.setImage(image);
    }

    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }

    }

    public void setImage(Icon... image) {
        txt.setImage(false, image);
    }

    public void setImage(String... image) {
        txt.setImage(false, image);
    }

    private String fileName;
    public void setFile(String fileName, String fileSize) {
        txt.setFile(fileName, fileSize);
        this.fileName = fileName;
    }

    public void setTime() {
        String time = Helper.getCurrentTime();
        txt.setTime(time);    //  Testing
    }

    public JPanel getChatPanel() {
        return chatPanel;
    }

    public void setChatPanel(JPanel chatPanel) {
        this.chatPanel = chatPanel;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }
    
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        IaImage = new swing.ImageAvatar();
        txt = new Components.Chat_Item();

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        IaImage.setBorderSize(0);
        IaImage.setImage(new javax.swing.ImageIcon(getClass().getResource("/testing/dog.jpg"))); // NOI18N
        IaImage.setMaximumSize(new java.awt.Dimension(31, 31));
        IaImage.setMinimumSize(new java.awt.Dimension(31, 31));
        jLayeredPane1.add(IaImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 31, 31));

        add(jLayeredPane1);
        add(txt);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private swing.ImageAvatar IaImage;
    private javax.swing.JLayeredPane jLayeredPane1;
    private Components.Chat_Item txt;
    // End of variables declaration//GEN-END:variables

}
