package assets;

import account.Account;
import account.CustomerAccount;
import account.CustomerAccountFactory;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Account> customerAccts;


    public Customer(String name, String pass, String accountType){
        setUserName(name);
        setPassword(pass);
        CustomerAccountFactory getAccountType = new CustomerAccountFactory();
        CustomerAccount account = getAccountType.getCustomerAccount(accountType);
        setCustomerAccts(new ArrayList<>());
        this.customerAccts.add(account);
    }

    public Customer(String name, String pass){
        // Default account type is checking account
        this(name,pass,"CheckingAccount");
    }
    public Customer(){
        this("Username", "Password", "CheckingAccount");
    }

    public void setCustomerAccts(List<Account> customerAcct) {
        this.customerAccts = customerAcct;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Account> getAccounts() {
        return this.customerAccts;
    }

    public boolean isExistingCustomer(String username){
        return getCustomerData(username).exists();
    }

    public File getCustomerData(String name){
        Path pathAbsolute = Paths.get("../../data/" + name+".csv");
        Path pathBase = Paths.get("../../");
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return new File(pathRelative.toUri());
    }

}
