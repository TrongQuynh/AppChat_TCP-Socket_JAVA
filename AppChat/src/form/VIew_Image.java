package form;

import Events.PublicEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class VIew_Image extends javax.swing.JComponent {

    public VIew_Image() {
        initComponents();
    }

    private Icon image;
    private BufferedImage bImage;
    private String filename;

    public void viewImage(Icon image, BufferedImage bImage,String filename) {
        this.image = image;
        pic.setImage(image);
        this.bImage = bImage;
        this.filename = filename;
        setVisible(true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pic = new swing.PictureBox();
        cmdSave = new javax.swing.JButton();

        pic.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                picMousePressed(evt);
            }
        });

        cmdSave.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/save.png"))); // NOI18N
        cmdSave.setContentAreaFilled(false);
        cmdSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cmdSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdSaveActionPerformed(evt);
            }
        });

        pic.setLayer(cmdSave, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout picLayout = new javax.swing.GroupLayout(pic);
        pic.setLayout(picLayout);
        picLayout.setHorizontalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, picLayout.createSequentialGroup()
                .addContainerGap(711, Short.MAX_VALUE)
                .addComponent(cmdSave)
                .addContainerGap())
        );
        picLayout.setVerticalGroup(
            picLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, picLayout.createSequentialGroup()
                .addContainerGap(440, Short.MAX_VALUE)
                .addComponent(cmdSave)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pic, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void picMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_picMousePressed
        if (SwingUtilities.isLeftMouseButton(evt)) {
            setVisible(false);
        }
    }//GEN-LAST:event_picMousePressed

    private void cmdSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdSaveActionPerformed
        int chooser = JOptionPane.showConfirmDialog(this,
                        "Do you want download \"" + "Picture" + "\" ?","Download Picture",
                        JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE,
                        new ImageIcon("src/Icons/file.png")
                );
        if(chooser == 0){
            PublicEvent.getInstance().getEventImageView().saveImage(image, bImage, filename);
        }
        
        
    }//GEN-LAST:event_cmdSaveActionPerformed

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(grphcs);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cmdSave;
    private swing.PictureBox pic;
    // End of variables declaration//GEN-END:variables
}
