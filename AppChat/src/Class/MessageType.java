package Class;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;
import javax.imageio.ImageIO;

/**
 *
 * @author Trong Quynh
 */
public class MessageType implements Serializable {

    /*
        Message Type
        1-String message
        2- Object Account
        3-array Account Object message
        4-array GroupChat object message
        5-Image
        6-File
        7-Object Group
     */
    private int messageType;
    private String messageText;
    private Account account;
    private AccountList accountList;
    private GroupChatList groupChatList;
    private GroupChat groupChat;

    //1
    public MessageType(int messageType, String messageText) {
        this.messageType = messageType;
        this.messageText = messageText;
    }

    //2
    public MessageType(int messageType, Account account) {
        this.messageType = messageType;
        this.account = account;
    }

    //3
    public MessageType(int messageType, AccountList accountList) {
        this.messageType = messageType;
        this.accountList = accountList;
    }

    //4
    public MessageType(int messagetype, GroupChatList groupChatList) {
        this.messageType = messagetype;
        this.groupChatList = groupChatList;
    }

    //5
    /*
        Lớp BufferedImage là lớp chuyên để làm việc với ảnh, lớp này lưu một mảng 2 chiều chứa thông tin của từng pixel trong ảnh.
     */
    private byte[] bytesPicture;
    private String formatFile;
    public MessageType(int messagetype, BufferedImage bufferedImages, String format) {
        try {
            this.messageType = messagetype;
            this.formatFile = format;
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImages, format, baos);
            this.bytesPicture = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //6 Files
    private byte[] fileContent;
    private String fileLengh;
    private String filename;
    public MessageType(int messagetype, String filename, String fileLengh, byte[] fileContent) {
        try {
            this.messageType = messagetype;
            this.filename = filename;
            this.fileLengh = fileLengh;
            this.fileContent = fileContent;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //7 - Object Group
    public MessageType(int messageType, GroupChat groupChat) {
        this.messageType = messageType;
        this.groupChat = groupChat;
    }

    public int getMessageType() {
        return messageType;
    }

    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public AccountList getAccountList() {
        return accountList;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public GroupChatList getGroupChatList() {
        return groupChatList;
    }

    public void setGroupChatList(GroupChatList groupChatList) {
        this.groupChatList = groupChatList;
    }

    public byte[] getBytesPicture() {
        return bytesPicture;
    }

    public void setBytesPicture(byte[] bytesPicture) {
        this.bytesPicture = bytesPicture;
    }

    public byte[] getFileContent() {
        return fileContent;
    }

    public void setFileContent(byte[] fileContent) {
        this.fileContent = fileContent;
    }

    public String getFileLengh() {
        return fileLengh;
    }

    public void setFileLengh(String fileLengh) {
        this.fileLengh = fileLengh;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getformatFile() {
        return formatFile;
    }

    public void setFormatFile(String format) {
        this.formatFile = formatFile;
    }

    public GroupChat getGroupChat() {
        return groupChat;
    }

    public void setGroupChat(GroupChat groupChat) {
        this.groupChat = groupChat;
    }
    
}
