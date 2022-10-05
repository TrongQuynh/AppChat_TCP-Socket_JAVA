package Class;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

public class UserThread implements Runnable {

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
        accountList.readDataFromFile();
        Set<Account> accounts = accountList.getAccountList();
        AccountList accOnline = this.server.getOnlineAccounts();
        for (Account account : accounts) {
            boolean isTrue = acc.isSameAccount(account);
            if (accOnline.isHaveAccount(acc)) {
                return false;
            }
            if (isTrue) {

                return true;
            }
        }

        return false;
    }

    private void boardcastRequet(Message message) {
        try {
            MessageType messType = new MessageType(1, "Server has recei your message");
            Message mess = new Message(4, this.serverAccount, messType);
            resObject.writeObject(mess);

            this.server.boardcast(message, this);

            System.out.println("Server Send message.....");

        } catch (Exception e) {
        }
    }

    public void loginHandle(MessageType reqMessage) {
        try {
            this.userAccount = this.server.getAllAccount()
                    .findAccountByNameAndPass(reqMessage.getAccount().getUsername(), reqMessage.getAccount().getPassword());
            userAccount.displayUserInfo();

            Account account = null;
            MessageType messageTyp = new MessageType(2, account);

            Message m = new Message(4, this.serverAccount, messageTyp);
            if (isLoginSuccess(userAccount)) { // Check login
                this.server.addUserInOnlineList(userAccount);

                messageTyp.setAccount(this.userAccount);
                resObject.writeObject(m);

                messageTyp.setMessageText(userAccount.getUsername() + " was join");
                m = new Message(4, this.serverAccount, messageTyp);
                this.server.boardcast(m, this);

                // Send List Account                    
                messageTyp = new MessageType(3, this.server.updateAccountStatusInList());
                m = new Message(4, this.serverAccount, messageTyp);
                resObject.writeObject(m);

                messageTyp = new MessageType(2, userAccount);
                m.setMessage(messageTyp);
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
            Account account = reqMessage.getAccount();
            if (account == null) {
                resObject.writeObject(new Message(4, this.serverAccount, new MessageType(1, "Register fail")));
                return;
            }

        } catch (Exception e) {
        }
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
                if (addressType == 3) {
                    boardcastRequet(reqMessage);
                } else if (addressType == 2) {
                    this.server.multicast(reqMessage, this);
                } else if (addressType == 1) {
                    this.server.unicast(reqMessage);
                } else {
                    // Server (Login - Register)
                    boolean isLogin = reqMessage.isIsLogin();
                    System.out.println("request Login: " + isLogin);
                    if (isLogin) {
                        loginHandle(reqMessage.getMessage());
                    } else {

                    }
                }

            }

//            System.out.println(this.userAccount.getUsername() + " Quit");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error When create new thread");
        }
    }

    public void sendMessage(Message message) throws IOException {
//        ObjectOutputStream resObject = new ObjectOutputStream(socket.getOutputStream());
        resObject.writeObject(message);

    }

}
