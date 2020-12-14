package account;

import utils.*;
import assets.Stock;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvException;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
public abstract class CustomerAccount extends Account {

    protected double balance;
    protected String currencyType;

    public CustomerAccount(String accountName, int id, double amount, String currencyType){
        super(accountName, id);
        this.balance = amount;
        this.currencyType = currencyType;
    }

    public CustomerAccount(){
        this("",-1,0,"USD");
    }

    public void open() {
        // New account, add account info and create path to files and display
        File tempFile = getCustomerData();
        if(tempFile.exists()){
            validatePassword(password);
        } else{
            try {
                if (tempFile.createNewFile()) {
                    System.out.println("File successfully made");
                } else {
                    System.out.println("File was not made");
                }
            }
            catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private boolean validatePassword(String password){
        File tempFile = getCustomerData();
        try (BufferedReader csvReader = new BufferedReader(new FileReader(tempFile.getAbsolutePath()))) {

            List<String[]> info = new ArrayList<>();
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split(",");
                info.add(data);
            }
            csvReader.close();
            if(info.size() == 0){
                System.out.println("There is no information for this account, but it exists!");
                return false;
            }
            if(password.equals(info.get(0)[1])){
                return true;
            }
            else{
                return false;
            }
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void close(){
        // Delete the account and all relevant info
        File tempFile = getCustomerData();

        if(tempFile.exists()){
            if(tempFile.delete()){
                System.out.println("File successfully deleted");
            }
            else{
                System.out.println("File does not exist and thus was not deleted");
            }

        }
    }

    public void transfer(Account acct, double amount, int addOrSub){
        File customerData = this.getCustomerData();
        try {
            CSVReader reader = new CSVReader(new FileReader(customerData));
            List<String[]> info = reader.readAll();
            if(info.size() >= 1){
                String pathToAcctInfo = info.get(0)[2];
                String currPathToAcctInfo = pathToAcctInfo + "\\" + this.name + ".csv";
                reader = new CSVReader(new FileReader(currPathToAcctInfo));
                info = reader.readAll();
                double currentAcctBalance = Double.parseDouble(info.get(0)[1]);
                currentAcctBalance += amount*addOrSub;
                info.get(0)[1] = currentAcctBalance + "";
                CSVWriter writer = new CSVWriter(new FileWriter(currPathToAcctInfo));
                writer.writeAll(info);
                String targetPathToAcctInfo = pathToAcctInfo + "\\" + acct.name + ".csv";
                reader = new CSVReader(new FileReader(targetPathToAcctInfo));
                info = reader.readAll();
                currentAcctBalance = Double.parseDouble(info.get(0)[1]);
                currentAcctBalance += -(amount*addOrSub);
                info.get(0)[1] = currentAcctBalance + "";
                writer = new CSVWriter(new FileWriter(targetPathToAcctInfo));
                writer.writeAll(info);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
        }

    }

    public void addBalance(double amount){
        this.balance += amount;
        this.updateBalance();
    }
    
    public void subBalance(double amount){
        this.balance -= amount;
        this.updateBalance();
    }

    public abstract void updateBalance();
    
    public void getDailyReport(Date date){
        // We could use this function to check how their stocks changed in on a certain date?
    }

    /** transfer money to another accoun */
    public abstract void transfer(CustomerAccount otherAccount, double amount);
    
    public File getCustomerData(){
        Path pathAbsolute = Paths.get("../../data/" + this.name+".csv");
        Path pathBase = Paths.get("../../");
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return new File(pathRelative.toUri());
    }
}
