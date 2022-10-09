package Class;

import java.io.File;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Trong Quynh
 */
public class GroupChat implements Serializable{

    private int groupID;
    private String groupName;
    private File avatar;
    private Set<Account> group;

    public GroupChat(int groupID, String groupName) {
        this.groupID = groupID;
        this.groupName = groupName;
        this.group = new HashSet<Account>();
        this.avatar = new File("src/Images/DefaultAccountImg.png");
    }
    
    public GroupChat(String groupName, Set<Account> group) {
        this.groupName = groupName;
        this.group = group;
        this.avatar = new File("src/Images/DefaultAccountImg.png");
    }

    public Set<Account> getGroup() {
        return group;
    }

    public void setGroup(Set<Account> group) {
        this.group = group;
    }

    public void addNewUser(Account account) {
        this.group.add(account);
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public File getAvatar() {
        return avatar;
    }

    public void setAvatar(File avatar) {
        this.avatar = avatar;
    }

    public void displayInfo() {
        String result = "GroupChat{" + "groupID=" + groupID + ", groupName=" + groupName + ", avatar=" + avatar + ", group=" + group + '}';
        System.out.println(result);
    }
    
    public Account findAccountByID(int ID){
        for(Account account : this.group){
            if(account.getID() == ID){
                return account;
            }
        }
        return null;
    }
    
    public void updateAccountStatus(int ID, boolean isOnline){
        Account account = findAccountByID(ID);
        if(account != null){
            account.setIsOnline(isOnline);
        }
    }

}
