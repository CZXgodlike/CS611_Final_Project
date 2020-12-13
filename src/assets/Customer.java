package assets;

import account.Account;
import account.CustomerAccount;
import account.CustomerAccountFactory;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {
    private List<Account> customerAccts;


    public Customer(String name, String pass) {
            setUserName(name);
            setPassword(pass);
            addDataFileForCustomer();
            setCustomerAccts(new ArrayList<>());
    }

    public Customer(){
        this("Username", "Password");
    }

    public void setCustomerAccts(List<Account> customerAcct) {
        this.customerAccts = customerAcct;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

     public String getUserName(){
        return this.userName;
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

    public void addNewAccount(Account acct){
        if(acct instanceof CustomerAccount) this.customerAccts.add(acct);
    }

    public void addDataFileForCustomer(){
        File file = getCustomerData(this.userName);
        if(!file.exists()){
            String toAddTo = addAccountForCustomer(this.userName,this.password);
            if(new File(toAddTo).mkdir()){
                System.out.println("File sucessfully made!");
            }
        }
    }

    public File getCustomerData(String name){
        Path pathAbsolute = Paths.get("../../data/" + name+".csv");
        Path pathBase = Paths.get("../../");
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return new File(pathRelative.toUri());
    }

    public String addAccountForCustomer(String name, String pass){
        File customerData = getCustomerData(name);
        String pathToAddTo = customerData.getParent() + "\\" + name;
        if(!customerData.exists()){
            try {
                System.out.println("hey");
                CSVWriter writer = new CSVWriter(new FileWriter(customerData, true));
                List<String[]> data = new ArrayList<>();
                data.add(new String[]{name, pass, pathToAddTo.replace("\\","\\\\")});
                writer.writeAll(data);
                writer.close();
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
        return pathToAddTo;
    }

}
