package Class;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SendRequest extends Thread{

    private Socket clientSocket;
    
    private Client client;

    public SendRequest(Socket socket, Client client) {
        this.clientSocket = socket;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream reqObject = new ObjectOutputStream(clientSocket.getOutputStream());
            Message message = this.client.reqMessage;
            reqObject.writeObject(message);
            System.out.println("Send Message successful sendrequest.java");
        } catch (IOException ex) {
            Logger.getLogger(SendRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

}
