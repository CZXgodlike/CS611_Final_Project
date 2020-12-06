package account; /**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 */
import assets.Customer;
import services.StockMarket;

import java.io.File;
import java.util.*;

public class BankerAccount extends Account {
    
    protected ArrayList<Customer> customers; // A list of all customers
    
    /** Constructor */
    public BankerAccount(String password, File data){
        super("Banker",000000,password,data);
        customers = new Arraylist<Customer>();
    }
    
    
    public void getDailyReport(Date date){
        
    }

    /** Checkup all customers */
    public void checkUpCustomers(){
        for(Customer c: customers){
            checkUp(c);
        }
    }
    
    /** Checkup a specific customer */
    public void checkUp(Customer customer){
        ArrayList<CustomerAccount> accounts = customer.getAccounts();
        // display each account that customer has
        for(CustomerAccount a: accounts){
            a.display();
        }
    }
    
    /** Update price of a stock */
    public void updatePrice(Stock stock){
        // GUI DISPLAY
        int newPrice = GUI_INPUT;
        stock.setPrice(newPrice);
    }
    
    /** Add/remove/update stock from all stocks */
    public void updateStockList(StockMarket market){
        market.displayStocks();
        // USE GUI BUTTON TO SELECT OPTION ADD/REMOVE/UPDATE
        int opt = GUI_INPUT;
        if(opt == 1){
            // USE BUTTON TO GET NEW STOCK
            Stock newStock = GUI_INPUT;
            market.add(newStock);
        }else if(opt == 2){
            // USE BOTTON TO SELECT STOCK TO BE REMOVED
            int stockId = GUI_INPUT;
            market.remove(stockId);
        }else{
            // USE BOTTON TO SELECT STOCK TO BE UPDATE
            int stockId = GUI_INPUT;
            updatePice(market.get(stockId));
        }
    }
}
