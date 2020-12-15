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

    public abstract void open();

    protected void open(CustomerAccount acctType){
        File toEdit = getCustomerData();
        String startingSymbol;
        if(acctType instanceof CheckingAccount){
            startingSymbol = "C";
        } else if(acctType instanceof SavingAccount){
            startingSymbol = "S";
        } else{
            startingSymbol = "A";
        }
        try {
            CSVReader reader = new CSVReader(new FileReader(toEdit));
            List<String[]> data = reader.readAll();;
            for(String[] d: data){
                if(d[0].equals(this.name)){
                    String accounts = d[2];
                    if(accounts.length() == 0){
                        accounts+= startingSymbol + id;
                    } else{
                        accounts += d[2] + ";" + startingSymbol + id;
                    }
                    d[2] = accounts;
                }
            }
            CSVWriter writer = new CSVWriter(new FileWriter(toEdit),CSVWriter.DEFAULT_SEPARATOR,
                    CSVWriter.NO_QUOTE_CHARACTER,
                    CSVWriter.NO_ESCAPE_CHARACTER,
                    CSVWriter.DEFAULT_LINE_END);
            writer.writeAll(data);
            writer.close();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
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

    public abstract void close();
        // Delete the account and all relevant info
//        File tempFile = getCustomerData();
//
//        if(tempFile.exists()){
//            if(tempFile.delete()){
//                System.out.println("File successfully deleted");
//            }
//            else{
//                System.out.println("File does not exist and thus was not deleted");
//            }
//
//        }

    public void close(CustomerAccount acct){
        String searchSymbol;
        if(acct instanceof CheckingAccount){
            searchSymbol = "C";
        } else if(acct instanceof SavingAccount){
            searchSymbol = "S";
        } else{
            searchSymbol = "A";
        }
        File customerData = getCustomerData();
        try {
            List<String[]> data = new CSVReader(new FileReader(customerData)).readAll();
            for(String[] d: data){
                if(d[0].equalsIgnoreCase(this.name)){
                    String[] accounts = d[2].split(";");
                    String newData = "";
                    for(String account: accounts){
                        if(!account.startsWith(searchSymbol)){
                            if(newData.length() == 0){
                                newData+= account;
                            } else{
                                newData+= ";" + account;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvException e) {
            e.printStackTrace();
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
    public void transfer(CustomerAccount otherAccount, double amount){
        if(exists(otherAccount)){

        }
    }
    
    public File getCustomerData(){
        Path pathAbsolute = Paths.get("../../data/customerData.csv");
        Path pathBase = Paths.get("../../");
        Path pathRelative = pathBase.relativize(pathAbsolute);
        return new File(pathRelative.toUri());
    }

    public double getBalance() {
        return balance;
    }

    public String getCurrencyType() {
        return currencyType;
    }

    public double getAmount() {
        return balance;
    }

    public void deposit(int amount){
        addBalance((double) amount);
    }

    public void withdraw(int amount){
        subBalance((double) amount);
    }

    public boolean exists(CustomerAccount acc){
        return true;
    }

    public abstract void display();


}
