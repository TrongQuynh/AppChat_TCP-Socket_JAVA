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

public class Chat_Right extends javax.swing.JLayeredPane {

    public Chat_Right() {
        initComponents();
        txt.setBackground(new Color(179, 233, 255));
    }

    private JPanel chatPanel;
    private byte[] fileContent;
    
    public void initEvent() {

        System.out.println("At chat_Right.java IS FILES");
        
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

    public void setText(String text) {
        if (text.equals("")) {
            txt.hideText();
        } else {
            txt.setText(text);
        }
        txt.seen();
    }

    public void setImage(Icon... image) {
        txt.setImage(true, image);
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

        txt = new Components.Chat_Item();

        setLayer(txt, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(txt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Components.Chat_Item txt;
    // End of variables declaration//GEN-END:variables
}
