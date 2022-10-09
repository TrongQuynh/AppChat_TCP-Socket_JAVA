/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Events;

import Class.Account;

/**
 *
 * @author Trong Quynh
 */
public interface EventCreateGroupChat {
    public void addUserIntoGroup(Account account);
    
    public void removeUserFromGroup(Account account);
}
