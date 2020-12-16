import account.*;
import assets.Customer;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import controller.StockDataController;
import utils.ReadFileUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) throws IOException {

        //

        /** Testing for the ability to create and delete an account's CSV file **/
//        CustomerAccount acct = new CustomerAccount("accountName", "password", 0, null);
//        acct.open();
//        acct.close();
//        Customer testCustomer = new Customer();
//        CustomerAccountFactory factory = new CustomerAccountFactory();
//        testCustomer.getAccounts().add(factory.getCustomerAccount("Leo","Checking"));
//        testCustomer.getAccounts().add(factory.getCustomerAccount("Leo","Saving"));
//        testCustomer.getAccounts().add(factory.getCustomerAccount("Leo","Securities"));
//        for(Account acct: testCustomer.getAccounts()){
//            if(acct instanceof CustomerAccount){
//                ((CustomerAccount) acct).open();
//            }
//        }

//        CheckingAccount testerAccount = new CheckingAccount("Leo",50,10000.0,"USD");
//        SavingAccount testerAccount2 = new SavingAccount("Leo",50,10000.0,"CAN");
//        SecuritiesAccount testerAccount3 = new SecuritiesAccount("Leo",50,10000.0,"USD");

        //        testerAccount.open();
//        testerAccount2.open();
//        testerAccount3.open();
//        testerAccount.transfer(testerAccount3,50.0);

//        for(Account acct: testCustomer.getAccounts()){
//            if(acct instanceof CustomerAccount){
//                ((CustomerAccount) acct).close();
//            }
//        }

        new
        File customerData = ReadFileUtil.getPathToCustomerData();
        CSVReader reader = new CSVReader(new FileReader(customerData));
        try {
            List<String[]> data= reader.readAll();
            List<CustomerAccount> accountsForLeo = new ArrayList<>();
            for(String[] d: data){
                if(d[0].equalsIgnoreCase("Leo")){
                    String[] accounts = d[2].split(";");
                    for(String acct: accounts){
                        if(acct.startsWith("C")){
                            acct = acct.substring(1);
                            int testAcctID = Integer.parseInt(acct);
                            File checkingAcctData = ReadFileUtil.getPathToAccountData("checkingAccounts");
                            String[] readData = ReadFileUtil.lookUpIDInFile(checkingAcctData,""+testAcctID);
                            accountsForLeo.add(new CheckingAccount("Leo", Integer.parseInt(readData[0]),Double.parseDouble(readData[1]),readData[2]));
                        }
                        else if(acct.startsWith("S")){
                            acct = acct.substring(1);
                            int testAcctID = Integer.parseInt(acct);
                            File checkingAcctData = ReadFileUtil.getPathToAccountData("savingAccounts");
                            String[] readData = ReadFileUtil.lookUpIDInFile(checkingAcctData,""+testAcctID);
                            accountsForLeo.add(new SavingAccount("Leo", Integer.parseInt(readData[0]),Double.parseDouble(readData[1]),readData[2]));
                        }
                        else if(acct.startsWith("A")){
                            acct = acct.substring(1);
                            int testAcctID = Integer.parseInt(acct);
                            File checkingAcctData = ReadFileUtil.getPathToAccountData("securityAccounts");
                            String[] readData = ReadFileUtil.lookUpIDInFile(checkingAcctData,""+testAcctID);
                            accountsForLeo.add(new SecuritiesAccount("Leo", Integer.parseInt(readData[0]),Double.parseDouble(readData[1]),readData[2]));
                        }
                    }
                }
            }

//            accountsForLeo.get(0).transfer(accountsForLeo.get(1),50.0);
//            System.out.println(accountsForLeo.get(1).getClass());
//            accountsForLeo.get(0).transfer(accountsForLeo.get(2),50.0);
//            System.out.println(accountsForLeo.get(2).getClass());

//            accountsForLeo.get(1).deposit(30);
//            accountsForLeo.get(2).withdraw(20);

            if(accountsForLeo.get(2) instanceof SecuritiesAccount){
                SecuritiesAccount acct = (SecuritiesAccount) accountsForLeo.get(2);
                acct.buy("AAPL",2);
                acct.sell("AAPL",3);
                System.out.println(acct.getBalance());
            }



        } catch (CsvException e) {
            e.printStackTrace();
        }

    }
}
