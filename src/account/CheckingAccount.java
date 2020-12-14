package account;

import utils.*;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CheckingAccount extends CustomerAccount{

    public CheckingAccount(String accountName, int id, double amount, String currencyType){
        super(accountName, id, amount, currencyType);
    }

    public void open(){
        super.open();
        File fileToCustomerData = this.getCustomerData();
        try {
            CSVReader reader = new CSVReader(new FileReader(fileToCustomerData));
            System.out.println(fileToCustomerData.toString());
            String pathToThisAcct = reader.readAll().get(0)[2];
            reader.close();
            pathToThisAcct = pathToThisAcct + "\\CheckingAccountData.csv";
            System.out.println(pathToThisAcct);

            if(new File(pathToThisAcct).createNewFile()){
                System.out.println("Checking Account added!");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }
    }
    
    public void transfer(CustomerAccount otherAccount, double amount){
        this.subBalance(amount);
        otherAccount.addBalance(amount);
    }
    
    public void updateBalance(){
        WriteFileUtil.writeField(this.id,1,balance);
    }
    
}
