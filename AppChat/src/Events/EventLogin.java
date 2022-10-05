package Events;

import Class.Account;

public interface EventLogin {

    public void login(Account account);

    public void register();

    public void goRegister();

    public void goLogin();
    
    public void setNotifycation(String message);
}
