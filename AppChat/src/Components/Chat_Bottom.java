package Components;

import Class.Account;
import Class.GroupChat;
import Events.PublicEvent;
import form.VideoCall;
import swing.JIMSendTextPane;
import swing.ScrollBar;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import net.miginfocom.swing.MigLayout;

public class Chat_Bottom extends javax.swing.JPanel {

    private Account account;
    private GroupChat groupChat;
    private Panel_More panelMore;
    private MigLayout mig;

    public Chat_Bottom() {
        initComponents();
        init();
    }

    public void setUser(Account account) {
        this.account = account;
//        panelMore.setUser(account);
    }

    public void setGroup(GroupChat groupChat) {
        this.groupChat = groupChat;
    }

    private void init() {
//        setLayout(new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2"));

        mig = new MigLayout("fillx, filly", "0[fill]0[]0[]2", "2[fill]2[]0");
        setLayout(mig);
        /*
            init input text area
         */
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        JIMSendTextPane txt = new JIMSendTextPane();
        txt.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent ke) {
                refresh();
            }
        });
        txt.setBorder(new EmptyBorder(5, 5, 5, 5));
        txt.setHintText("Write Message Here ...");
        scroll.setViewportView(txt);
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(229, 229, 229));
        sb.setPreferredSize(new Dimension(2, 10));
        scroll.setVerticalScrollBar(sb);
        add(sb);
        add(scroll, "w 100%");

        /*
            init panel include button SEND
         */
        JPanel panel = new JPanel();
        panel.setLayout(new MigLayout("filly", "0[]0", "0[bottom]0"));
        panel.setPreferredSize(new Dimension(30, 28));
        panel.setBackground(Color.WHITE);

        /*
            intit Button Send
         */
        JButton btnSend = new JButton();
        btnSend.setBorder(null);
        btnSend.setContentAreaFilled(false);
        btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnSend.setIcon(new ImageIcon(new File("src/Icons/send.png").getAbsolutePath()));
        btnSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String text = txt.getText().trim();
                if (!text.equals("")) {
                    PublicEvent.getInstance().getEventChat().sendMessage(text, account);
                    txt.setText("");
                    txt.grabFocus();
                    refresh();
                } else {
                    txt.grabFocus();
                }
            }
        });

        /*
            intt Button more
         */
        JButton btnMore = new JButton();
        btnMore.setBorder(null);
        btnMore.setContentAreaFilled(false);
        btnMore.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnMore.setIcon(new ImageIcon(new File("src/Icons/more_disable.png").getAbsolutePath()));
        btnMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                btnMore.setIcon(new ImageIcon(new File("src/Icons/more_disable.png").getAbsolutePath()));
                try {
                    eventChooserFile();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Chat_Bottom.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        /*
            init btn video call
         */
        JButton btnCallVideo = new JButton();
        btnCallVideo.setBorder(null);
        btnCallVideo.setContentAreaFilled(false);
        btnCallVideo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnCallVideo.setIcon(new ImageIcon(new File("src/Icons/callVideo.png").getAbsolutePath()));
        btnCallVideo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    if(account != null){
                        VideoCall call = new VideoCall();
                        call.setVisible(true);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Chat_Bottom.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        panel.add(btnCallVideo);
        panel.add(btnMore);
        panel.add(btnSend);

        add(panel);

        panelMore = new Panel_More();
        panelMore.setVisible(false);
        add(panelMore, "dock south,h 0!");
    }

    private void eventChooserFile() throws InterruptedException {
        JFileChooser chooser = new JFileChooser();

        File[] files;
        chooser.setDialogTitle("Chooser Picture");
        chooser.setMultiSelectionEnabled(true);
        chooser.setCurrentDirectory(new File("C:\\Users\\pc\\Pictures\\Nitro\\Background"));
        if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            files = chooser.getSelectedFiles();
            for (File file : files) {
                PublicEvent.getInstance().getEventChat().sendFile(file);
            }
        }
    }

    private void refresh() {
        revalidate();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(229, 229, 229));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 40, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
