import account.Account;
import account.CustomerAccount;
import account.CustomerAccountFactory;
import assets.Customer;
import controller.StockDataController;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        StockDataController c = new StockDataController();
        c.getData();

        /** Testing for the ability to create and delete an account's CSV file **/
//        CustomerAccount acct = new CustomerAccount("accountName", "password", 0, null);
//        acct.open();
//        acct.close();
        Customer testCustomer = new Customer("Leo", "1111");
        CustomerAccountFactory factory = new CustomerAccountFactory();
        testCustomer.getAccounts().add(factory.getCustomerAccount(testCustomer.getUserName(),"Checking"));
        for(Account acct: testCustomer.getAccounts()){
            if(acct instanceof CustomerAccount){
                ((CustomerAccount) acct).open();
            }
        }
    }
}
