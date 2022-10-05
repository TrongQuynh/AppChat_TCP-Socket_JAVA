package form;

import Class.Account;
import Class.Client;
import Class.ClientSocket;
import Class.GroupChat;
import Class.Message;
import Class.MessageType;
import Components.Chat_Body;
import Components.Chat_Bottom;
import Components.Chat_Title;
import Events.EventChat;
import Events.PublicEvent;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.MimetypesFileTypeMap;
import javax.swing.ImageIcon;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import net.miginfocom.swing.MigLayout;

public class Chat extends javax.swing.JPanel {

    private Chat_Title chatTitle;
    private Chat_Body chatBody;
    private Chat_Bottom chatBottom;

    public Chat() {
        initComponents();
        init();
    }

    private void init() {
        setLayout(new MigLayout("fillx", "0[fill]0", "0[]0[100%, bottom]0[shrink 0]0"));
        chatTitle = new Chat_Title();
        chatBody = new Chat_Body();
        chatBottom = new Chat_Bottom();
        PublicEvent.getInstance().addEventChat(new EventChat() {
            @Override
            public void sendMessage(String text, Account receiver) {
                // show message on chat screen
                chatBody.addItemRight(text);

                Client client = ClientSocket.getInstanceClientSocket().getClient();
                Account sender = client.getUserAccount();
                try {
                    int currentAddressType = ClientSocket.getInstanceClientSocket().getCurrentAddressType();
                    if (currentAddressType == 1) {
                        // Send message to other user

                        client.reqMessage = new Message(1, sender, receiver, new MessageType(1, text));
                        client.runRequestThread();
                    } else if (currentAddressType == 2) {
                        // Sendto group
                        int groupID = chatTitle.getGroupChat().getGroupID();
                        MessageType messageType = new MessageType(1, text);
                        Message message = new Message(2, sender, groupID, messageType);
                        client.reqMessage = message;
                        client.runRequestThread();
                    } else {
                        MessageType messageType = new MessageType(1, text);
                        Message message = new Message(3, sender, messageType);
                        client.reqMessage = message;
                        client.runRequestThread();
                    }

                } catch (IOException ex) {
                    Logger.getLogger(Chat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void receiveMessage(Message data) {
                System.out.println("Recei data at chat.java");
                int currentAddressType = ClientSocket.getInstanceClientSocket().getCurrentAddressType();
                int messageType = data.getMessage().getMessageType();
                try {
                    if (currentAddressType == 1) {
                        if (chatTitle.getAccount().getID().equals(data.getSender().getID())) {
                            if(messageType == 1 || messageType == 5){
                                chatBody.addItemLeftWithProfile(data);
                            }else{
                                String fileLength = data.getMessage().getFileLengh();
                                String fileName = data.getMessage().getFilename();
                                String senderName = data.getSender().getUsername();
                                byte[] fileContent = data.getMessage().getFileContent();
                                chatBody.addItemFile("", senderName, fileName, fileLength,fileContent);
                            }
                        }
                    } else if (currentAddressType == 2) {
                        int groupID = data.getGroupID();
                        if (groupID == chatTitle.getGroupChat().getGroupID()) {
                            chatBody.addItemLeftWithProfile(data);
                        }
                    } else {
                        chatBody.addItemLeftWithProfile(data);
                    }
                } catch (Exception e) {
                }
            }

            @Override
            public void sendFile(File file) {
                System.out.println("Show file chat.java " + file.toString());
                MessageType messageType = null;
                Client client = ClientSocket.getInstanceClientSocket().getClient();
                Account sender = client.getUserAccount();
                if (isFileAPicture(file)) {
                    try {
                        // show message of sender
                        ImageIcon imageIcon = new ImageIcon(file.getAbsolutePath());
                        chatBody.addItemRight("", imageIcon);
                       
                        BufferedImage bufferedImage = ImageIO.read(file);
                        String format = getFileExtension(file);
                        if (format == null) {
                            System.out.println("Format == null");
                            return;
                        }
                        messageType = new MessageType(5, bufferedImage, format);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        System.out.println("File is not image");
                        FileInputStream fileInputStream = new FileInputStream(file.getAbsolutePath());
                        String fileName = file.getName();
                        // Create a byte array the size of the file
                        byte[] bytesFile = new byte[(int) file.length()];
                        // Put the contents of the file into the array of bytes
                        fileInputStream.read(bytesFile);
                        String fileLength = (double) file.length() / (1024 * 1024) + " MB";
                        messageType = new MessageType(6,fileName, fileLength, bytesFile);
                        
                        
                        // show message has send
                        chatBody.addItemFileRight("", fileName, fileLength, bytesFile);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                if (messageType == null) {
                    System.out.println("Messagetype == null");
                    return;
                }

                // Check send to user, group or all
                try {
                    int currentAddressType = ClientSocket.getInstanceClientSocket().getCurrentAddressType();
                    if (currentAddressType == 1) {
                        System.out.println("Show messType before send chat.java: " + messageType.getMessageType());
                        Account receiver = chatTitle.getAccount();
                        Message message = new Message(1, sender, receiver, messageType);
                        client.reqMessage = message;
                        client.runRequestThread();
                        System.out.println("Send picture inChat.java");
                    } else if (currentAddressType == 2) {
                        // Sendto group
                        int groupID = chatTitle.getGroupChat().getGroupID();
                        Message message = new Message(2, sender, groupID, messageType);
                        client.reqMessage = message;
                        client.runRequestThread();
                    } else {
                        Message message = new Message(3, sender, messageType);
                        client.reqMessage = message;
                        client.runRequestThread();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void downloadFile(byte[] fileContent, String filename) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int chooser = fileChooser.showDialog(null, "Save");
                if(chooser == JFileChooser.APPROVE_OPTION){
                  File fileDirectory = fileChooser.getSelectedFile();
                  System.out.println("Chat.java Place save file: " + fileDirectory);  
                  String filePath = fileDirectory.toString()+"\\"+filename;
                    try {
                        System.out.println("cha.java filepath: " + filePath);
                        File newFile = new File(filePath);
                        OutputStream os = new FileOutputStream(newFile);
                        os.write(fileContent);
                        System.out.println("Save file sucessful");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                
                
            }
                      
        });
        add(chatTitle, "wrap");
        add(chatBody, "wrap");
        add(chatBottom, "h ::50%");
    }

    private boolean isFileAPicture(File file) {
        String fileExtension = getFileExtension(file);

        if (fileExtension.equalsIgnoreCase("png") || fileExtension.equals("jpg")) {
            return true;
        }
        return false;
    }

    private String getFileExtension(File file) {

        // convert the file name into string
        String fileName = file.toString();

        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            String extension = fileName.substring(index + 1);
            return extension;
        }
        return null;
    }

    public void setUser(Account account) {
        if(account.isSameAccount(chatTitle.getAccount())) return;
        chatTitle.setUserName(account);
        chatBottom.setUser(account);
        chatBody.clearChat();
    }

    public void setGroup(GroupChat groupChat) {
        groupChat.displayInfo();
        chatTitle.setGroupName(groupChat);
        chatBottom.setGroup(groupChat);
        chatBody.clearChat();
    }

    public void setAlluser() {

        chatTitle.setAllUser("All User");
        chatBody.clearChat();
    }

    public void updateUser(Account account) {
        chatTitle.updateUser(account);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 727, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 681, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
