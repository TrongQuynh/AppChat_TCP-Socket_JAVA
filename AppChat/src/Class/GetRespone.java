package Class;

import Events.PublicEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class GetRespone extends Thread {

    private Socket clientSocket;
    private Client client;
    private ObjectInputStream resObject;

    public GetRespone(Socket socket, Client client) throws IOException {
        this.clientSocket = socket;
        this.client = client;
    }
    
    @Override
    public void run() {
        try {

            resObject = new ObjectInputStream(clientSocket.getInputStream());
            while (true) {
                
                Message resMessage = (Message) resObject.readObject();
                int messageType = resMessage.getMessage().getMessageType();
                if (!client.isLogin) {
                    if (messageType == 1) {
                        String result = resMessage.getMessage().getMessageText();
                        if (result.equalsIgnoreCase("true")) {
                            PublicEvent.getInstance().getEventLogin().goLogin();
                        } else {
//                            PublicEvent.getInstance().getEventLogin().setNotifycation("Register Fail");
                            JOptionPane.showMessageDialog(this.client.loginForm, "Register fail", "Register", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        Account account = resMessage.getMessage().getAccount();
                        if (account != null) {

                            PublicEvent.getInstance().getEventMain().showLoading(false);
                            PublicEvent.getInstance().getEventMain().initChat();
                            this.client.loginForm.setVisible(false);
                            System.out.println("Login Success");

                            client.setUserAccount(account);
                            client.isLogin = true;
                            PublicEvent.getInstance().getEventMenuRight().setUser(account);
                        } else {
                            PublicEvent.getInstance().getEventMain().showLoading(false);

                            JOptionPane.showMessageDialog(null, "Login fail", "Login", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                } else {

                    int addressType = resMessage.getAddressType();
                    System.out.println("New message from server: " + addressType);
                    if (addressType == 4) {
                        // From Server
                        if (messageType == 1) {
                            System.out.println("[SERVER]> " + resMessage.getMessage().getMessageText());
                        } else if (messageType == 2) {
                            Account account = resMessage.getMessage().getAccount();
                            AccountList accountList = ClientSocket.getInstanceClientSocket().getAccountList();
                            accountList.updateStatusAccount(account.getID(), true);
                            System.out.println("Update new user connect: " + account.getID());
                            PublicEvent.getInstance().getEventMenuLeft().newUserConnect(account.getID());
                        } else if (messageType == 3) {
                            AccountList accountList = resMessage.getMessage().getAccountList();
                            ClientSocket.getInstanceClientSocket().setAccountList(accountList);
                            System.out.println("get responr Account list messtype 3: " + accountList.getAccountList().size());
                            PublicEvent.getInstance().getEventMenuLeft().newUser(accountList);
                        } else if (messageType == 4) {
                            GroupChatList groupChatList = resMessage.getMessage().getGroupChatList();
                            System.out.println("getrespone groupChatList messtype 4: " + groupChatList.getGroupChats().size());
                            ClientSocket.getInstanceClientSocket().setGroupChatList(groupChatList);
                        }
                    } else {
                        // Address type: 1,2,3
                        /*
                            1-UniCast:  1 to 1
                            2-Multicast: 1 to Group
                            3-Broadcast: 1 to All
                            5-Logout
                         */

                        if (resMessage.getAddressType() == 5) {

                            if (resMessage.getMessage().getMessageType() == 2) {

                                Account account = resMessage.getMessage().getAccount();
                                AccountList accountList = ClientSocket.getInstanceClientSocket().getAccountList();
                                accountList.updateStatusAccount(account.getID(), false);
                                int userID = account.getID();
                                System.out.println("Receiver the user log out: " + userID);
                                PublicEvent.getInstance().getEventMenuLeft().userDisconnect(userID);
//                                client.runResponeThread();
//                                return;
                            }
                        }else{
                            System.out.println("Recei message at getrespone.java");
                        PublicEvent.getInstance().getEventChat().receiveMessage(resMessage);
                        }
                        
                    }

                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
