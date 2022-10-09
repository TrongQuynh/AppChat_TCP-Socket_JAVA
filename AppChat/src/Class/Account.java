package Class;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author Trong Quynh
 */
public class Account implements Serializable {

    private int ID;
    private String username;
    private String password;
    private boolean isOnline;
    private File avartar;
    private String email;

    public Account() {

    }

    public Account(String username) {
        this.username = username;
        this.isOnline = true;
        this.avartar = new File("src/Images/DefaultAccountImg.png");
    }

    public Account(int ID, String email, String username, String password) {
        this(email, password);
        this.username = username;
        this.ID = ID;
    }
    
    public Account(String email, String username, String password) {
        this(email, password);
        this.username = username;
    }

    public Account(String email, String password) {
        this.password = password;
        this.isOnline = false;
        this.email = email;
        this.avartar = new File("src/Images/DefaultAccountImg.png");
    }

    public Account(Account account) {
        this.username = account.username;
        this.password = account.password;
    }

    public void displayUserInfo() {
        System.out.println("ID: " + ID + "\n Email: " + email +"\n Username: " 
                + username + "\n Password: " + password + "\n isOnline: " + isOnline
                + "\n Avatar: " + avartar
        );
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

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSameAccount(Account account) {
        if (account == null) {
            return false;
        }
        return this.email.equals(account.getEmail());

    }

}
