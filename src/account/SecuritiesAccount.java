package account;

import assets.Stock;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import utils.ReadFileUtil;
import utils.WriteFileUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class SecuritiesAccount extends CustomerAccount {
    

    public boolean buy(Stock stock){

        if(balance < Double.parseDouble(stock.getLast())){
            return false;
        } else{
            // Run code to add the stock to the files
            return true;
        }

    }

    public boolean sell(Stock stock){
        // check if they even have the stock to be sold and run code to deal with both cases
        return true;
    }

    public void displayOpenPositions(){

    }

    public void checkProfits(){

    }

    public void display(){
        // Connect to the GUI
    }

    @Override
    public void updateBalance() {

    }

    @Override
    public void transfer(CustomerAccount otherAccount, double amount) {

    }

    @Override
    public void open() {
        super.open(this);
        File checkingAccountFile = ReadFileUtil.getPathToAccountData("securityAccounts");
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
        super.close(this);
        File securityAccountFile = ReadFileUtil.getPathToAccountData("securityAccounts");
        if(securityAccountFile.exists()){

            WriteFileUtil.removeLineFromCustomerAccountData(securityAccountFile.getAbsolutePath(), this);
        }
    }
}
