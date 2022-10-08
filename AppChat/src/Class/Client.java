package Class;

import java.io.IOException;
import java.net.Socket;
import javax.swing.JPanel;

/**
 *
 * @author Trong Quynh
 */
public class Client {

    private int port;
    private String ip;
    private Account userAccount;
    private Socket clientSocket;

    public Message reqMessage;

    public Thread threadRespone;
    public Thread threadRequest;

    public JPanel loginForm;

    public boolean isLogin;

    public Client(String ip, int port) {
        this.port = port;
        this.ip = ip;
        this.isLogin = false;
    }

    public void runMainClient() {
        try {
            clientSocket = new Socket(this.ip, this.port);
            runResponeThread();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error when start client");
        }
    }

    public void runResponeThread() throws IOException {
        GetRespone respone = new GetRespone(clientSocket, this);
        respone.start();
    }

    public void runRequestThread() throws IOException {
        SendRequest request = new SendRequest(clientSocket, this);
        request.start();
//        threadRequest = new Thread(request);
//        threadRequest.start();
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    //    Main
    public static void main(String[] args) {
        Client client = new Client("127.0.0.1", 8081);
        client.runMainClient();
    }
}
