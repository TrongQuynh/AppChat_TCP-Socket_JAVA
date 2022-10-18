package Components;

import Class.Message;
import swing.ScrollBar;
import java.awt.Adjustable;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JScrollBar;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class Chat_Body extends javax.swing.JPanel {

    public Chat_Body() {
        initComponents();
        init();

//        addItemRight("hello", new ImageIcon("C:\\Users\\pc\\Pictures\\Nitro\\Background\\SunsetAnime.jpg"));
//        addItemRight("Send a text message to a group of contacts. Include photos, personalize your texts, and track who clicked your links.", new ImageIcon(new File("src/testing/cat.png").getAbsolutePath()), new ImageIcon(new File("src/testing/pic.jpg").getAbsolutePath()));
//        addItemRight("hello\nHi");
//        addItemLeft("Simpletext started as a passion project because I couldn’t find what I was looking for."
//                + " Most apps were trying to do too much and ended up bloated with features I don’t need. So I built Simpletext based on a simple premise — what if there’s an app that refuses to do more, choosing instead to do just one thing, and do it well? For Simpletext,"
//                + " that one thing is writing.", "Raven", new ImageIcon(new File("src/testing/dog.jpg").getAbsolutePath()), new ImageIcon(new File("src/testing/pic.jpg").getAbsolutePath()));
        addDate(Helper.Helper.getCurrentDate());
//        addDate("05/06/2021");

//        String img[] = {"LRMj,K-:?G9G_JIon}WqD~ITRPs,", "LCGlO@00.R~o.9DOO[%L02?aJ7D*"};
//        addItemLeft("hello\nerererew\newewe", "Dara", img);
//        addItemRight("hello\nerererew\newewe", new ImageIcon(getClass().getResource("/com/raven/icon/testing/pic.jpg")));
//        addItemLeft("Hello this is my friend", "Jonh", new ImageIcon(getClass().getResource("/com/raven/icon/testing/dog.jpg")), new ImageIcon(getClass().getResource("/com/raven/icon/testing/dog.jpg")));
//        addItemRight("Ok\nWhat is he name ?");
//        addItemLeft("Welcome to AppChat", "Server", new ImageIcon(new File("src/testing/pic.jpg").getAbsolutePath()));
//        addItemFile("", "Dara", "my doc.pdf", "1 MB", null);
//        addItemFileRight("", "myfile.rar", "15 MB", null);
          addItemLeft("Welcome to AppChat", "Server");
          addItemLeft("Let's chat now!", "Server");
    }

    private void init() {
        body.setLayout(new MigLayout("fillx", "", "5[]5"));
        sp.setVerticalScrollBar(new ScrollBar());
        sp.getVerticalScrollBar().setBackground(Color.WHITE);
    }

    public void addItemLeft(String text, String user, Icon... image) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setImage(image);
        item.setTime();
        item.setUserProfile(user);
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addItemLeft(Message message) {
        if (message.getMessage().getMessageType() == 1) {
            Chat_Left item = new Chat_Left();
            item.setText(message.getMessage().getMessageText());
//            item.setImage(new ImageIcon(message.getSender().getAvartar().getAbsolutePath()));
            item.setTime();
            body.add(item, "wrap, w 100::80%");
        }
        repaint();
        revalidate();
    }

    public void addItemLeftWithProfile(Message message) {
        try {
            int messType = message.getMessage().getMessageType();
            System.out.println("Show messType at chat_body.java: " + messType);
            Chat_Left_With_Profile item = new Chat_Left_With_Profile();
            item.setUserProfile(message.getSender().getUsername());
            item.setImageProfile(new ImageIcon(message.getSender().getAvartar().getAbsolutePath()));
            if (messType == 1) {
                item.setText(message.getMessage().getMessageText());
            } else if (messType == 5) {
                // Picture
                byte[] bytesPicture = message.getMessage().getBytesPicture();
                String format =  message.getMessage().getformatFile();
                String fileName = "Picture1." + format;
                // convert byte[] to BufferedImage
                InputStream is = new ByteArrayInputStream(bytesPicture);
                BufferedImage bufferedImage = ImageIO.read(is);
//                item.setImage(new ImageIcon(bufferedImage));
                item.setPictureContent(fileName,bufferedImage, new ImageIcon(bufferedImage));
            }else if (messType == 6) {
                
            }
            item.setTime();
            body.add(item, "wrap, w 100::80%");
            repaint();
            revalidate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addItemLeft(String text, String user, String[] image) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setImage(image);
        item.setTime();
        item.setUserProfile(user);
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        body.repaint();
        body.revalidate();
    }

    public void addItemFile(String text, String user, String fileName, String fileSize, byte[] fileContent) {
        Chat_Left_With_Profile item = new Chat_Left_With_Profile();
        item.setText(text);
        item.setChatPanel(this);
        item.setFileContent(fileContent);
        item.setFile(fileName, fileSize);
        item.setTime();
        item.setUserProfile(user);
        item.initEvent();
        
        body.add(item, "wrap, w 100::80%");
        //  ::80% set max with 80%
        repaint();
        revalidate();
    }

    public void addItemRight(String text, Icon... image) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        item.setImage(image);
        body.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        repaint();
        revalidate();
        item.setTime();
        scrollToBottom();
    }
    
    public void addItemRight(String text, File file) throws IOException {
        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
        BufferedImage bufferedImage = ImageIO.read(file);
        Chat_Right item = new Chat_Right();
        item.setText(text);
        String filename = file.getName();
        item.setPictureContent(bufferedImage,filename,imageIcon);
        body.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        repaint();
        revalidate();
        item.setTime();
        scrollToBottom();
    }

    public void addItemFileRight(String text, String fileName, String fileSize, byte[] fileContent) {
        Chat_Right item = new Chat_Right();
        item.setText(text);
        item.setChatPanel(this);
        item.setFileContent(fileContent);
        item.setFile(fileName, fileSize);
        item.initEvent();
        
        
        body.add(item, "wrap, al right, w 100::80%");
        //  ::80% set max with 80%
        repaint();
        revalidate();
    }

    public void addDate(String date) {
        Chat_Date item = new Chat_Date();
        item.setDate(date);
        body.add(item, "wrap, al center");
        body.repaint();
        body.revalidate();
    }

    public void clearChat() {
        body.removeAll();
        repaint();
        revalidate();
    }
    
    public void setMessageDataPanel(JPanel messageContent){
//        clearChat();
        System.out.println("ChatBody.java " + messageContent.getComponentCount());
//        for(Component component : messageContent.getComponents()){
//            body.add(component, "wrap, w 100::80%");
//        }
        body = messageContent;
        repaint();
        revalidate();
    }
    
    public JPanel getMessageData(){
        if(body.getComponentCount() > 0){
            return this.body;
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sp = new javax.swing.JScrollPane();
        body = new javax.swing.JPanel();

        sp.setBorder(null);
        sp.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        body.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout bodyLayout = new javax.swing.GroupLayout(body);
        body.setLayout(bodyLayout);
        bodyLayout.setHorizontalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 826, Short.MAX_VALUE)
        );
        bodyLayout.setVerticalGroup(
            bodyLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 555, Short.MAX_VALUE)
        );

        sp.setViewportView(body);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sp)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void scrollToBottom() {
        JScrollBar verticalBar = sp.getVerticalScrollBar();
        AdjustmentListener downScroller = new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                Adjustable adjustable = e.getAdjustable();
                adjustable.setValue(adjustable.getMaximum());
                verticalBar.removeAdjustmentListener(this);
            }
        };
        verticalBar.addAdjustmentListener(downScroller);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel body;
    private javax.swing.JScrollPane sp;
    // End of variables declaration//GEN-END:variables
}
