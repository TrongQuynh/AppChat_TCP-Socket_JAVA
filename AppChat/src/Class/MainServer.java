package Class;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainServer {

    private int port;

    private Set<UserThread> userThreads;
    private AccountList accountOnlineList;

    public MainServer(int port) {
        this.port = port;
        this.accountOnlineList = new AccountList();
        this.userThreads = new HashSet<UserThread>();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New access Request From client");

                UserThread newUser = new UserThread(socket, this);
                // Add new UserThread into List
                this.userThreads.add(newUser);
                newUser.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error When start Server");
        }
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public static void main(String[] args) {
        MainServer mainServer = new MainServer(8081);
        mainServer.runServer();
        return;
    }

    public void addUserInOnlineList(Account account) {
        this.accountOnlineList.addNewAccount(account);
    }

    public AccountList getOnlineAccounts() {
        return this.accountOnlineList;
    }

    public void boardcast(Message message, UserThread excludeUser) throws IOException {
        for (UserThread thread : this.userThreads) {
            if (thread == excludeUser) {
//                System.out.println("User Send :" + thread.getUserAccount().getUsername() + " Thread: " + thread);
                continue;
            }
//            System.out.println("User :" + thread.getUserAccount().getUsername() + " Thread: " + thread);
            thread.sendMessage(message);
        }
    }

    public void multicast(Message message, UserThread excludeUser) throws IOException {
        GroupChatList gcl = getAllGroupChat();

        int groupID = message.getGroupID();
        int senderID = message.getSender().getID();

        GroupChat groupChat = gcl.findGroupChatByID(groupID);
        if (groupChat == null) {
            return;
        }

        for (UserThread thread : this.userThreads) {
            if (groupChat.findAccountByID(thread.getUserAccount().getID()) != null) {
                if (thread == excludeUser) {
                    continue;
                }
                thread.sendMessage(message);
            }
        }
    }

    public void unicast(Message message) throws IOException {
        Account receiver = message.getReceiver();
        for (UserThread thread : this.userThreads) {
            if (thread.getUserAccount().isSameAccount(receiver)) {
                thread.sendMessage(message);
                return;
            }
        }

    }

    public AccountList getAllAccount() {
        AccountList acc = new AccountList();
        acc.initFile();
        acc.readDataFromFile();
        return acc;
    }

    public GroupChatList getAllGroupChat() {
        GroupChatList gcl = new GroupChatList();
        gcl.initFile();
        gcl.readDataFromFile();
        return gcl;
    }

    public AccountList updateAccountStatusInList() {

        AccountList acc = getAllAccount();
        AccountList result = new AccountList();
        for (Account a : acc.getAccountList()) {
            Account accResult = a;
            for (Account accOn : this.accountOnlineList.getAccountList()) {
                if (a.isSameAccount(accOn)) {
                    accResult.setIsOnline(true);
                }
            }

            result.addNewAccount(accResult);
        }
        return acc;
    }

    public GroupChatList updateAccountStatusInGroupCaht() {
        GroupChatList gcl = getAllGroupChat();
        
        GroupChatList result = new GroupChatList();
        for (GroupChat gc : gcl.getGroupChats()) {
            for (Account account : this.accountOnlineList.getAccountList()) {
                gc.updateAccountStatus(account.getID(), true);
                result.addNewGroup(gc);
            }
        }
        return result;
    }

    public GroupChatList getAllGroupChatUserJoin(int userID) {
        GroupChatList gcl = getAllGroupChat();
        GroupChatList result = new GroupChatList();
        for (GroupChat groupChat : gcl.getGroupChats()) {
            if (groupChat.findAccountByID(userID) == null) {
                continue;
            }
            result.addNewGroup(groupChat);
        }
        return result;
    }

    public void removeUserThreadInList(UserThread user) {
//        boolean isHaveThread = false;
//        for (UserThread thread : this.userThreads) {
//            if (thread == user) {
//                isHaveThread = true;
//                break;
//            }
//        }
//        if (isHaveThread == false) {
//            return;
//        }
        this.userThreads.remove(user);
//        this.userThreads.removeAll(userThreads);

        System.out.println("Remove userthread successful: " + this.userThreads.size());
    }

    public MainServer() {
    }
    
   public void UpdateThread(UserThread thread){
       for(UserThread userThread: this.userThreads){
           if(userThread.getUserAccount().getID() == thread.getUserAccount().getID()){
               // if this thread exist in list
               return;
           }
       }
       this.userThreads.add(thread);
   }
    
    public void removeUserinOnlineList(Account reAccount){
        boolean isHaveAccount = false;
        for(Account account:this.accountOnlineList.getAccountList()){
            if(account.isSameAccount(reAccount)){
                isHaveAccount = true;
                break;
            }
        }
        if(isHaveAccount){
            this.accountOnlineList.getAccountList().remove(reAccount);
            System.out.println("Remove user in online list successful: " + this.accountOnlineList.getAccountList().size());
        }
    }

//    END
}
