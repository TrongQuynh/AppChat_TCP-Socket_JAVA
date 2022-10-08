/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Trong Quynh
 */
public class MessageDB {

    private ArrayList<MessageStore> messageDB;

    public MessageDB() {
        this.messageDB = new ArrayList<MessageStore>();
    }

    public ArrayList<MessageStore> getMessageStore() {
        return messageDB;
    }

    public void setMessageStore(ArrayList<MessageStore> messageStore) {
        this.messageDB = messageStore;
    }

    public MessageStore findMessageAccountByID(int ID) {
        for (MessageStore ms : messageDB) {
            if (ms.getID() == ID) {
                return ms;
            }
        }
        return null;
    }

    public int findIndexOfMessage_Account(int ID) {
        for (int i = 0; i < messageDB.size(); i++) {
            if (messageDB.get(i).getID() == ID) {
                return i;
            }
        }
        return -1;
    }

    public void UpdateMessageDataAccount(MessageStore newMessage) {
        int accountID = newMessage.getID();
        int index = findIndexOfMessage_Account(accountID);
        if (index < 0) {
            return;
        }
        messageDB.set(index, newMessage);
    }

    public void addNewMessageAccount(MessageStore newMessage) {
        this.messageDB.add(newMessage);
    }

}
