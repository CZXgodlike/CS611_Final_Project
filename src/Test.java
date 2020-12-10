import account.CustomerAccount;
import controller.StockDataController;

import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        StockDataController c = new StockDataController();
        c.getData();

        /** Testing for the ability to create and delete an account's CSV file **/
        CustomerAccount acct = new CustomerAccount("accountName", "password", 0, null);
        acct.open();
        acct.close();
    }
}
