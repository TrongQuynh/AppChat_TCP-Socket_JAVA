package Class;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class MainServer {

    private int port;

    private Set<UserThread> userThreads;
    private AccountList accountOnlineList;

    public MainServer(int port) {
        this.port = port;
        this.accountOnlineList = new AccountList();
        this.userThreads = new HashSet<>();
    }

    public void runServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New access Request From client");

                UserThread newUser = new UserThread(socket, this);
                Thread serverThread = new Thread(newUser);
                serverThread.start();

                // Add new UserThread into List
                this.userThreads.add(newUser);

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
                continue;
            }
            thread.sendMessage(message);
        }
    }

    public void multicast(Message message, UserThread excludeUser) throws IOException {
        GroupChatList gcl = getAllGroupChat();

        int groupID = message.getGroupID();
        String senderID = message.getSender().getID();
        
        GroupChat groupChat = gcl.findGroupChatByID(groupID);
        if(groupChat == null) return;
        
        for(UserThread thread: this.userThreads){
            if(groupChat.findAccountByID(thread.getUserAccount().getID()) != null){
                if(thread == excludeUser) continue;
                thread.sendMessage(message);
            }
        }
    }

    public void unicast(Message message) throws IOException {
        Account receiver = message.getReceiver();
        System.out.println("Mess tyep: " + message.getMessage().getMessageType());
        System.out.println("Name of reciver: " + message.getReceiver().getUsername());
        for (UserThread thread : this.userThreads) {
            if (thread.getUserAccount().isSameAccount(receiver)) {
                thread.sendMessage(message);
                return;
            }
        }

    }
    
    public AccountList getAllAccount(){
        AccountList acc = new AccountList();
        acc.readDataFromFile();
        return acc;
    }
    
    public GroupChatList getAllGroupChat(){
        GroupChatList gcl = new GroupChatList();
        gcl.readDataFromFiles();
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
        GroupChatList gcl = new GroupChatList();
        gcl.readDataFromFiles();
        GroupChatList result = new GroupChatList();
        for (GroupChat gc : gcl.getGroupChats()) {
            for (Account account : this.accountOnlineList.getAccountList()) {
                gc.updateAccountStatus(account.getID(), true);
                result.addNewGroup(gc);
            }
        }
        return result;
    }
    
    public GroupChatList getAllGroupChatUserJoin(String userID){
        GroupChatList gcl = getAllGroupChat();
        GroupChatList result = new GroupChatList();
        for(GroupChat groupChat : gcl.getGroupChats()){
            if(groupChat.findAccountByID(userID) == null) continue;
            result.addNewGroup(groupChat);
        }
        return result;
    }

//    END
}
