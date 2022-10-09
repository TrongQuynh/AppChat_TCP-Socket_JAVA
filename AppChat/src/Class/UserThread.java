package Class;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class UserThread extends Thread{

    private Socket socket;
    private MainServer server;
    private Account userAccount;
    private ObjectOutputStream resObject;

    private Account serverAccount = new Account("Server");

    public UserThread(Socket socket, MainServer server) {
        this.socket = socket;
        this.server = server;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public void userLogout() {

    }

    private boolean isLoginSuccess(Account acc) throws IOException {
        if (acc == null) {
            return false;
        }
        AccountList accountList = new AccountList();
        accountList.initFile();
        accountList.readDataFromFile();

        //Check if whether account is online or not
        AccountList accOnline = this.server.getOnlineAccounts();
        if (accOnline.isHaveAccount(acc)) {
            System.out.println("This account already online");
            return false;
        }

        for (Account account : accountList.getAccountList()) {
            boolean isTrue = acc.isSameAccount(account);
            if (isTrue) {
                return true;
            }
        }

        System.out.println("This account not have in list");
        return false;
    }

    private void boardcastRequet(Message message) {
        try {
//            MessageType messType = new MessageType(1, "Server has recei your message");
//            Message mess = new Message(4, this.serverAccount, messType);
//            resObject.writeObject(mess);

            this.server.boardcast(message, this);

            System.out.println("Server Send message.....");

        } catch (Exception e) {
        }
    }

    public void loginHandle(MessageType reqMessage) {
        try {
            String email = reqMessage.getAccount().getEmail();
            String password = reqMessage.getAccount().getPassword();
            this.userAccount = this.server.getAllAccount().findAccountByEmailAndPass(email, password);

            Account account = null;
            MessageType messageTyp = new MessageType(2, account);

            Message m = new Message(4, this.serverAccount, messageTyp);
            if (isLoginSuccess(userAccount)) { // Check login
                this.server.addUserInOnlineList(userAccount);
                this.userAccount.setIsOnline(true);

                messageTyp.setAccount(this.userAccount);
                resObject.writeObject(m);

                messageTyp.setMessageText(userAccount.getUsername() + " was join");
                m = new Message(4, this.serverAccount, messageTyp);
                this.server.boardcast(m, this);

                // Send List Account                    
                messageTyp = new MessageType(3, this.server.updateAccountStatusInList());
                m = new Message(4, this.serverAccount, messageTyp);
                resObject.writeObject(m);

                // send new user connect
                messageTyp = new MessageType(2, userAccount);
                m = new Message(4, messageTyp);
                this.server.boardcast(m, this);

                //Send list GroupChat
                messageTyp = new MessageType(4, this.server.getAllGroupChatUserJoin(userAccount.getID()));
                m = new Message(4, this.serverAccount, messageTyp);
                resObject.writeObject(m);

            } else {
                resObject.writeObject(m);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void registerHandle(MessageType reqMessage) {
        try {

            // If account request Login is NULL
            Account accountRegister = reqMessage.getAccount();
            if (accountRegister == null) {
                resObject.writeObject(new Message(4, this.serverAccount, new MessageType(1, "Register fail")));
                return;
            }
            AccountList accountList = new AccountList();
            accountList.initFile();
            accountList.readDataFromFile();

            //check whether this email is exist
            String email = accountRegister.getEmail();
            Account account = accountList.findAccountByEmail(email);
            if (account == null) {
                int ID = accountList.generateAccountID();
                //Check if ID is valid
                while (accountList.findAccountByID(ID) != null) {
                    ID += 1;
                }
                accountRegister.setID(ID);
                accountList.appendDataToFile(accountRegister);
                System.out.println("Add new Account successfule");
                resObject.writeObject(new Message(4, this.serverAccount, new MessageType(1, "true")));
            } else {
                // Account exist
                resObject.writeObject(new Message(4, this.serverAccount, new MessageType(1, "Register fail")));
                return;
            }

        } catch (Exception e) {
        }
    }
    
    private void createNewGroupChatHandle(GroupChat groupChat) throws IOException{
        GroupChatList groupChatList = this.server.getAllGroupChat();
        int ID = groupChatList.GenerateGroupChatID();
        groupChat.setGroupID(ID);
        groupChatList.appendDataToFile(groupChat);
        groupChatList.getGroupChats().removeAll(groupChatList.getGroupChats());
        groupChatList.readDataFromFile();
        // Send back respone
        System.out.println("Current number group chat: " + groupChatList.getGroupChats().size());
        Message message = new Message(4, new MessageType(4, groupChatList));
        resObject.writeObject(message);
        boardcastRequet(message);
    }

    @Override
    public void run() {
        try {

            resObject = new ObjectOutputStream(socket.getOutputStream());
            while (true) {
                ObjectInputStream reqObject = new ObjectInputStream(socket.getInputStream());
                System.out.println("Start Run");
                Message reqMessage = (Message) reqObject.readObject();
                /*
                    1-UniCast:  1 to 1
                    2-Multicast: 1 to Group
                    3-Broadcast: 1 to All
                 */
                System.out.println(reqMessage.getAddressType());
                int addressType = reqMessage.getAddressType();
                int messageType = reqMessage.getMessage().getMessageType();
                if (addressType == 3) {
                    boardcastRequet(reqMessage);
                } else if (addressType == 2) {
                    this.server.multicast(reqMessage, this);
                } else if (addressType == 1) {
                    this.server.unicast(reqMessage);
                } else if (addressType == 4) {
                    // Server (Login - Register)
                    if(messageType == 2){
                        boolean isLogin = reqMessage.isIsLogin();
                        System.out.println("request Login: " + isLogin);
                        if (isLogin) {
                            loginHandle(reqMessage.getMessage());
                        } else {
                            registerHandle(reqMessage.getMessage());
                        }
                    }else if(messageType == 7){
                        // Create new group chat
                        createNewGroupChatHandle(reqMessage.getMessage().getGroupChat());
                    }
                    
                } else {
                    if (reqMessage.getMessage().getMessageType() == 1) {
                        if (reqMessage.getMessage().getMessageText().equals("logout")) {
                            System.out.println("User " + this.userAccount.getUsername() + " quit");
                            this.userAccount.setIsOnline(false);
                            Message message = new Message(5, new MessageType(2, userAccount));
                            boardcastRequet(message);
                            this.server.removeUserThreadInList(this);
                            this.server.removeUserinOnlineList(userAccount);
                            break;
                        }
                    }
                }

            }//End while

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error When create new thread");
        }
    }

    public void sendMessage(Message message) throws IOException {
//        ObjectOutputStream resObject = new ObjectOutputStream(socket.getOutputStream());
//        System.out.println("Respone OBJ: " + resObject);
        if(message.getAddressType() == 1)System.out.println("To: " + message.getReceiver().getUsername());
        
        resObject.writeObject(message);

    }

}
