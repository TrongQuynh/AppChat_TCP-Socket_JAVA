
package Class;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Trong Quynh
 */
public class GroupChatList implements Serializable{
    private Set<GroupChat> groupChats;
    private File file;
    
    public void initFile() {
        this.file = new File("src/Data/GroupChatList.txt");
    }

    public GroupChatList() {
        this.groupChats = new HashSet<GroupChat>();
    }

    public GroupChatList(Set<GroupChat> groupChats) {
        this.groupChats = groupChats;
    }

    public Set<GroupChat> getGroupChats() {
        return groupChats;
    }

    public void setGroupChats(Set<GroupChat> groupChats) {
        this.groupChats = groupChats;
    }
        
    public void addNewGroup(GroupChat gc){
        this.groupChats.add(gc);
    }
    
    public int GenerateGroupChatID(){
        return this.groupChats.size() + 3000;
    }
    
    public GroupChat findGroupChatByID(int ID){
        for(GroupChat groupChat : this.groupChats){
            if(groupChat.getGroupID() == ID) return groupChat;
        }
        return null;
    }
    
     public void writeDataToFile() {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            for (GroupChat group : this.groupChats) {
                oos.writeObject(group);
            }
            oos.flush();
            fout.close();
            oos.close();
            System.out.println("Save Account Success");
        } catch (Exception e) {
            System.out.println("WriteFile" + e);
        }
    }
     
     public void appendDataToFile(GroupChat groupChat) {
        try {
            FileOutputStream fout = new FileOutputStream(file, true);
            ObjectOutputStream oos = new ObjectOutputStream(fout) {
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
            oos.writeObject(groupChat);
            oos.flush();
            fout.close();
            oos.close();
        } catch (Exception e) {
            System.out.println("Append file" + e);
        }
    }
     
     public void readDataFromFile() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            GroupChat groupChat = null;
            while (true) {
                Object obj = ois.readObject();
                if (obj == null) {
                    break;
                }
                groupChat = (GroupChat) obj;
                this.groupChats.add(groupChat);
            }
            fis.close();
            ois.close();
        } catch (Exception e) {
         
//            System.out.println("Error when read data from file");
        }
    }
    
}
