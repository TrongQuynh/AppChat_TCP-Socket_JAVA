package Class;

import java.io.File;
import java.io.Serializable;
import java.util.Set;

/**
 *
 * @author Trong Quynh
 */
public class Message implements Serializable {

    private Account receiver;
    private Account sender;
    private MessageType message;
    private int addressType;

    /*
        1-UniCast:  1 to 1
        2-Multicast: 1 to Group
        3-Broadcast: 1 to All
     */
    //Multicast
    private String senderName;
    private int groupID;
    public Message(int addressType, Account sender, int groupID, MessageType message) {
        this.message = message;
        this.sender = sender;
        this.groupID = groupID;
        this.addressType = addressType;
    }

    //UniCast
    public Message(int addressType, Account sender, Account receiver, MessageType message) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
        this.addressType = addressType;
    }

    //Broadcast
    public Message(int addressType, Account sender, MessageType message) {
        this.message = message;
        this.sender = sender;
        this.addressType = addressType;
    }

    // register and login
    private boolean isLogin;
    public Message(int addressType, boolean isLogin, MessageType messageType) {
        this.addressType = addressType;
        this.message = messageType;
        this.isLogin = isLogin;
    }
    
    //    END Constructor

    public Account getReceiver() {
        return receiver;
    }

    public void setReceiver(Account receiver) {
        this.receiver = receiver;
    }

    public Account getSender() {
        return sender;
    }

    public void setSender(Account sender) {
        this.sender = sender;
    }

    public int getAddressType() {
        return addressType;
    }

    public void setAddressType(int addressType) {
        this.addressType = addressType;
    }

    public MessageType getMessage() {
        return message;
    }

    public void setMessage(MessageType message) {
        this.message = message;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public boolean isIsLogin() {
        return isLogin;
    }

    public void setIsLogin(boolean isLogin) {
        this.isLogin = isLogin;
    }
    

}

/*
    message
    accountList
    acount

 */
