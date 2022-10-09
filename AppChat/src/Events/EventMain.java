package Events;

import Class.Account;
import Class.GroupChat;

public interface EventMain {

    public void showLoading(boolean show);
    
    public void showLogin(boolean show);

    public void initChat();
    
    public void destroyChat();
    
    public void selectUser(Account user);
    
    public void selectGroup(GroupChat group);
    
    public void selectSendAll();

    public void updateUser(Account user);
}
