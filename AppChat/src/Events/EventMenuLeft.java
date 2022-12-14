package Events;

import Class.AccountList;
import Class.GroupChatList;

public interface EventMenuLeft {

    public void newUser(AccountList users);

    public void newUserConnect(int userID);

    public void userDisconnect(int userID);

    public void newGroup(GroupChatList groupChatList);
}
