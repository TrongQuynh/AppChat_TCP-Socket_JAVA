
package Class;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Trong Quynh
 */
public class Account implements Serializable{
    private String ID;
    private String username;
    private String password;
    private boolean isOnline;
    private File avartar;
    
    public Account(){
        
    }
    
    public Account(String username){
        this.avartar = new File("src/Images/DefaultAccountImg.png");
    }
    
    public Account(String ID, String username, String password){
        this(username, password);
        this.ID = ID;
    }

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.isOnline = false;
        this.avartar = new File("src/Images/DefaultAccountImg.png");
    }
    
    public Account(Account account){
        this.username = account.username;
        this.password = account.password;
    }
    
    public void displayUserInfo(){
        System.out.println("ID: " + ID + "\n Username: " + username + "\n Password: " + password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIsOnline() {
        return isOnline;
    }

    public void setIsOnline(boolean isOnline) {
        this.isOnline = isOnline;
    }

    public File getAvartar() {
        return avartar;
    }

    public void setAvartar(File avartar) {
        this.avartar = avartar;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    
    
    public boolean isSameAccount(Account account){
        if(account == null) return false;
        return ((this.getUsername().equals(account.getUsername())) &&
                (this.getPassword().equals(account.getPassword())) &&
                (this.getID().equals(account.getID()))) ? true : false;
        
    }
    
    
    
}
