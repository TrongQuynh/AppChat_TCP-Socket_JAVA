package Class;

/**
 *
 * @author Trong Quynh
 */
public class ClientSocket {

    /*
        1-unicast
        2-Multicast
        3-boardcast
    */
    
    private Client client;
    private static ClientSocket clientSocket;
    
    private GroupChatList groupChatList;
    private AccountList accountList;
    
    private int currentAddressType;
    
    
    public static ClientSocket getInstanceClientSocket() {
        if(clientSocket == null){
            clientSocket = new ClientSocket();
        }
        return clientSocket;
    }
    
    public void setClient(Client client){
        this.client = client;
    }
    
    public Client getClient(){
        return this.client;
    }

    public static ClientSocket getClientSocket() {
        return clientSocket;
    }

    public static void setClientSocket(ClientSocket clientSocket) {
        ClientSocket.clientSocket = clientSocket;
    }

    public GroupChatList getGroupChatList() {
        return groupChatList;
    }

    public void setGroupChatList(GroupChatList groupChatList) {
        this.groupChatList = groupChatList;
    }

    public AccountList getAccountList() {
        return accountList;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public int getCurrentAddressType() {
        return currentAddressType;
    }

    public void setCurrentAddressType(int currentAddressType) {
        this.currentAddressType = currentAddressType;
    }
    
    
    
}
