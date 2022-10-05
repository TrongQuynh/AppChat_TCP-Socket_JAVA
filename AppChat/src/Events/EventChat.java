package Events;

import Class.Account;
import Class.Message;
import java.io.File;

public interface EventChat {

    public void sendMessage(String text, Account receiver);
    
    public void sendFile(File file);
    
    public void downloadFile(byte[] fileContent, String filename);
    
    public void receiveMessage(Message data);
}
