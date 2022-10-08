/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB;

import Components.Chat_Body;
import Components.Chat_Title;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JPanel;

/**
 *
 * @author Trong Quynh
 */
public class MessageStore {

    private int ID;

    private JPanel messagePanel;

    public MessageStore(int accountID, JPanel messageContent) {
        this.ID = accountID;
        this.messagePanel = messageContent;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public JPanel getMessagePanel() {
        return messagePanel;
    }

    public void setMessagePanel(JPanel messagePanel) {
        this.messagePanel = messagePanel;
    }

}
