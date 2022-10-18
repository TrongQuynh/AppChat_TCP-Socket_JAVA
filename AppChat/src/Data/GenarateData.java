package Data;

import Class.Account;
import Class.AccountList;
import Class.GroupChat;
import Class.GroupChatList;
import java.io.File;

public class GenarateData {

    public static void GenerateAccountList() {
        AccountList accountList = new AccountList();
        accountList.initFile();
        Account acc1 = new Account(100, "abc@gmail.com", "Quynh", "123");
        acc1.setAvartar(new File("src/Images/AccountImg.jpg").getAbsoluteFile());
        accountList.addNewAccount(acc1);
        
        
        Account acc2 = new Account(101, "abc1@gmail.com", "Anh", "123");
        acc2.setAvartar(new File("src/Images/AccountImg.png").getAbsoluteFile());
        accountList.addNewAccount(acc2);
        
        accountList.addNewAccount(new Account(102, "abc2@gmail.com", "admin3", "123"));
//        accountList.addNewAccount(new Account(103,"abc3@gmail.com","admin4", "123"));
//        accountList.addNewAccount(new Account(104,"abc4@gmail.com","admin5", "123"));
        accountList.writeDataToFile();
    }

    public static void GenerateGroupList() {
        GroupChatList groupChatList = new GroupChatList();
        groupChatList.initFile();

        GroupChat gc = new GroupChat(0, "Group 1");
        GroupChat gc1 = new GroupChat(1, "Group 2");
        GroupChat gc2 = new GroupChat(2, "Group 3");

        gc.addNewUser(new Account(100, "abc@gmail.com", "admin", "123"));
        gc.addNewUser(new Account(101, "abc1@gmail.com", "admin2", "123"));
        gc.addNewUser(new Account(102, "abc2@gmail.com", "admin3", "123"));

        gc1.addNewUser(new Account(103, "abc3@gmail.com", "admin4", "123"));
        gc1.addNewUser(new Account(104, "abc4@gmail.com", "admin5", "123"));
        gc1.addNewUser(new Account(102, "abc2@gmail.com", "admin3", "123"));

        gc2.addNewUser(new Account(100, "abc@gmail.com", "admin", "123"));
        gc2.addNewUser(new Account(104, "abc4@gmail.com", "admin5", "123"));
        gc2.addNewUser(new Account(103, "abc2@gmail.com", "admin3", "123"));

        groupChatList.addNewGroup(gc);
        groupChatList.addNewGroup(gc1);
        groupChatList.addNewGroup(gc2);

        groupChatList.writeDataToFile();
    }

    public static void ReadDataGroupChatList() {
        GroupChatList groupChatList = new GroupChatList();
        groupChatList.initFile();
        groupChatList.readDataFromFile();
        System.out.println("Number Group chat: " + groupChatList.getGroupChats().size());
    }

    public static void AppendNewAccountToFile() {
        AccountList accountList = new AccountList();
        accountList.initFile();
        Account account = new Account(105, "abc5@gmail.com", "admin6", "123");
        accountList.addNewAccount(account);
//        accountList.writeDataToFile();
        accountList.appendDataToFile(account);
    }

    public static void AppendNewGroupChatToFile() {
        GroupChatList groupChatList = new GroupChatList();
        groupChatList.initFile();
        GroupChat gc2 = new GroupChat(2, "IT");
        gc2.addNewUser(new Account(100, "abc@gmail.com", "admin", "123"));
        gc2.addNewUser(new Account(104, "abc4@gmail.com", "admin5", "123"));
        gc2.addNewUser(new Account(103, "abc2@gmail.com", "admin3", "123"));
        groupChatList.appendDataToFile(gc2);

    }

    public static void ReadDataAccountList() {
        AccountList accountList = new AccountList();
        accountList.initFile();
        accountList.readDataFromFile();
        for (Account account : accountList.getAccountList()) {
            account.displayUserInfo();
        }
    }

    public static void main(String[] args) {
        GenerateAccountList();
//            AppendNewAccountToFile();
            ReadDataAccountList();
GenerateGroupList();
        ReadDataGroupChatList();
//        AppendNewGroupChatToFile();
    }
}
