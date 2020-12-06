package account;

import account.Account;

import java.io.File;
import java.util.*;
public class CustomerAccount extends Account {

    public CustomerAccount(String password, File data){
        super ("assets.Customer", 0, password, data);
    }

    CustomerAccount(){
        this("password", null);
    }


    public void open(){

    }

    public void close(){

    }

    public void transaction(Account acct){

    }

    public void transfer(Account acct){
        
    }

    public void checkBalance(){
    }

    public void display(){

    }

    public void getDailyReport(Date date){

    }
}
