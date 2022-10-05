
package Class;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AccountList implements Serializable{
    private Set<Account> accountList;

    public AccountList() {
        this.accountList = new HashSet<Account>();
    }

    public Set<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(Set<Account> accountList) {
        this.accountList = accountList;
    }
    
    public void addNewAccount(Account account){
        this.accountList.add(account);
    }
    
    public Account findAccountByName(String username){
        for(Account account: this.accountList){
            if(account.getUsername().equals(username)){
                return account;
            }
        }
        return null;
    }
    
    public Account findAccountByNameAndPass(String username, String pass){
        for(Account account: this.accountList){
            if(account.getUsername().equals(username) && account.getPassword().equals(pass)){
                return account;
            }
        }
        return null;
    }
    
    public boolean isHaveAccount(Account acc){
        for(Account account: this.accountList){
            if(acc.isSameAccount(account)){
                return true;
            }
        }
        
        return false;
    }
    
    public void updateStatusAccount(String userID, boolean isOnline){
        for(Account account : this.accountList){
            if(account.getID().equals(userID)){
                account.setIsOnline(isOnline);
            }
        }
    }
    
    public String generateAccountID(){
        int index = this.accountList.size() + 1;
        return "Account" + index;
    }
    
    public void readDataFromFile(){
        this.accountList.add(new Account("Account1","admin", "123"));
        this.accountList.add(new Account("Account2","admin2", "123"));
        this.accountList.add(new Account("Account3","admin3", "123"));
        this.accountList.add(new Account("Account4","admin4", "123"));
        this.accountList.add(new Account("Account5","admin5", "123"));
    }
    
}
