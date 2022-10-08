
package Class;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Trong Quynh
 */
public class GroupChatList implements Serializable{
    private Set<GroupChat> groupChats;

    public GroupChatList() {
        this.groupChats = new HashSet<GroupChat>();
    }

    public GroupChatList(Set<GroupChat> groupChats) {
        this.groupChats = groupChats;
    }
    
    public void readDataFromFiles(){
        GroupChat gc = new GroupChat(0, "Group 1");
        GroupChat gc1 = new GroupChat(1, "Group 2");
        GroupChat gc2 = new GroupChat(2, "Group 3");
        
        gc.addNewUser(new Account(100,"abc@gmail.com","admin", "123"));
        gc.addNewUser(new Account(101,"abc1@gmail.com","admin2", "123"));
        gc.addNewUser(new Account(102,"abc2@gmail.com","admin3", "123"));
        
        gc1.addNewUser(new Account(103,"abc3@gmail.com","admin4", "123"));
        gc1.addNewUser(new Account(104,"abc4@gmail.com","admin5", "123"));
        gc1.addNewUser(new Account(102,"abc2@gmail.com","admin3", "123"));
        
        gc2.addNewUser(new Account(100,"abc@gmail.com","admin", "123"));
        gc2.addNewUser(new Account(104,"abc4@gmail.com","admin5", "123"));
        gc2.addNewUser(new Account(103,"abc2@gmail.com","admin3", "123"));
        
        this.groupChats.add(gc);
        this.groupChats.add(gc1);
        this.groupChats.add(gc2);
        
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
    
    public GroupChat findGroupChatByID(int ID){
        for(GroupChat groupChat : this.groupChats){
            if(groupChat.getGroupID() == ID) return groupChat;
        }
        return null;
    }
    
    public void updateAccountStatusInList(String userID){
        
    }
    
}
