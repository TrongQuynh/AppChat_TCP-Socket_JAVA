
package Data;

import Class.Account;
import Class.AccountList;

public class GenarateData {
    
    public static void GenerateAccountList(){
        AccountList accountList = new AccountList();
        accountList.initFile();
        accountList.addNewAccount(new Account(100,"abc@gmail.com","admin", "123"));
        accountList.addNewAccount(new Account(101,"abc1@gmail.com","admin2", "123"));
        accountList.addNewAccount(new Account(102,"abc2@gmail.com","admin3", "123"));
//        accountList.addNewAccount(new Account(103,"abc3@gmail.com","admin4", "123"));
//        accountList.addNewAccount(new Account(104,"abc4@gmail.com","admin5", "123"));
        accountList.writeDataToFile();
    }
    
    public static void AppendNewAccountToFile(){
        AccountList accountList = new AccountList();
        accountList.initFile();
          Account account = new Account(105,"abc5@gmail.com","admin6", "123");
        accountList.addNewAccount(account);
//        accountList.writeDataToFile();
            accountList.appendDataToFile(account);
    }
    
    public static void ReadDataAccountList(){
        AccountList accountList = new AccountList();
        accountList.initFile();
        accountList.readDataFromFile();
        for(Account account:accountList.getAccountList()){
            account.displayUserInfo();
        }
    }
    
    public static void main(String[] args) {
//        GenerateAccountList();
//            AppendNewAccountToFile();
            ReadDataAccountList();
    }
}
