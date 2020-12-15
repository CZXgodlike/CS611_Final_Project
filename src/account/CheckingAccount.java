package account;

import com.opencsv.CSVWriter;
import utils.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CheckingAccount extends CustomerAccount{

    public CheckingAccount(String accountName, int id, double amount, String currencyType){
        super(accountName, id, amount, currencyType);
    }

    public CheckingAccount(String accountName){
        this(accountName, 0, 0.0,"USD");
    }

    public CheckingAccount(){
        this("",0,0.0,"USD");
    }

    public void open(){
        // Add to the specific customer's checking, saving or security account
        super.open(this);
        File checkingAccountFile = ReadFileUtil.getPathToAccountData("checkingAccounts");
        if(checkingAccountFile.exists()){
            try {
                CSVReader reader = new CSVReader(new FileReader(getCustomerData().getAbsolutePath()));
                List<String[]> data = reader.readAll();
                boolean customerExists = false;
                for(String[] d: data){
                    if(d[0].equalsIgnoreCase(this.name)){
                        customerExists = true;
                    }
                }
                if(customerExists){
                    WriteFileUtil.writeLine(checkingAccountFile.getPath(), id +"," + balance + "," + currencyType);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (CsvException e) {
                e.printStackTrace();
            }
        }
    }

    public void close(){
        // Add to the specific customer's checking, saving or security account
        super.close(this);
        File checkingAccountFile = ReadFileUtil.getPathToAccountData("checkingAccounts");
        if(checkingAccountFile.exists()){

            WriteFileUtil.removeLineFromCustomerAccountData(checkingAccountFile.getAbsolutePath(), this);
        }
    }

    
    public void transfer(CustomerAccount otherAccount, double amount){
        this.subBalance(amount);
        otherAccount.addBalance(amount);
    }
    
    public void updateBalance(){
        // put new updated balance in
//        WriteFileUtil.writeField(this.id,1,balance);
    }

    @Override
    public void display() {
        // display info in GUI
    }
}
