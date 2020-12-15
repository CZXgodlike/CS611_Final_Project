package assets;

import account.Account;

import java.util.ArrayList;
import java.util.List;

public class Customer implements User {
    private List<Account> customerAccts;

    public Customer(){
        setCustomerAccts(new ArrayList<>());
    }

    public void setCustomerAccts(List<Account> customerAcct) {
        this.customerAccts = customerAcct;
    }

    public List<Account> getAccounts() {
        return this.customerAccts;
    }
}
