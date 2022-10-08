package Class;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class AccountList implements Serializable {

    private ArrayList<Account> accountList;
    private File file;

    public AccountList() {
        this.accountList = new ArrayList<Account>();
    }

    public ArrayList<Account> getAccountList() {
        return accountList;
    }

    public void setAccountList(ArrayList<Account> accountList) {
        this.accountList = accountList;
    }

    public void addNewAccount(Account account) {
        this.accountList.add(account);
    }

    public boolean isHaveAccount(Account acc) {
        for (Account account : this.accountList) {
            if (acc.isSameAccount(account)) {
                return true;
            }
        }

        return false;
    }

    public void updateStatusAccount(int userID, boolean isOnline) {
        for (Account account : this.accountList) {
            if (account.getID() == userID) {
                account.setIsOnline(isOnline);
            }
        }
    }

    public int generateAccountID() {
        int size = this.accountList.size() + 1;
        return 100 + size;
    }
    
    //Work with File

    public void readDataFromFile() {
        try {
            FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);

            Account newAccount = null;
            while (true) {
                Object obj = ois.readObject();
                if (obj == null) {
                    break;
                }
                newAccount = (Account) obj;
                this.accountList.add(newAccount);
            }
            fis.close();
            ois.close();
        } catch (Exception e) {
         
//            System.out.println("Error when read data from file");
        }
    }

    public void initFile() {
        this.file = new File("src/Data/AccountList.txt");
    }

    public void writeDataToFile() {
        try {
            FileOutputStream fout = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fout);
            for (Account acc : accountList) {
                oos.writeObject(acc);
            }
            oos.flush();
            fout.close();
            oos.close();
            System.out.println("Save Account Success");
        } catch (Exception e) {
            System.out.println("WriteFile" + e);
        }
    }

    public void appendDataToFile(Account account) {
        try {
            FileOutputStream fout = new FileOutputStream(file, true);
            ObjectOutputStream oos = new ObjectOutputStream(fout) {
                protected void writeStreamHeader() throws IOException {
                    reset();
                }
            };
            oos.writeObject(account);
            oos.flush();
            fout.close();
            oos.close();
        } catch (Exception e) {
            System.out.println("Append file" + e);
        }
    }
    
    //Search
    public Account findAccountByEmail(String email) {
        for (Account account : this.accountList) {
            if (account.getEmail().equals(email)) {
                return account;
            }
        }
        return null;
    }
    
    public Account findAccountByID(int ID){
        for (Account account : this.accountList) {
            if (account.getID() == ID) {
                return account;
            }
        }
        return null;
    }

    public Account findAccountByEmailAndPass(String email, String pass) {
        for (Account account : this.accountList) {
            if (account.getEmail().equals(email) && account.getPassword().equals(pass)) {
                return account;
            }
        }
        return null;
    }

}
