/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Abstract account class
 */
import java.util.*;

public abstract class Account{
    
    protected String name; // name of the account owner
    protected int id; // Account/Rountine number
    protected String password; // pass of the account
    protected File data; // Data for the account
    
    /** Default Constructor */
    public Account(){
        this.name = "TempAccount";
        this.id = "0";
        this.password = "0";
    }
    
    public Account(String name, int id, String password, File data){
        this.name = name;
        this.id = id;
        this.password = password;
        this.data = data;
    }
    
    /** Utilities */
    public String getName(){return this.name;}
    public int getId(){return this.id;}
    public String getPassword(){return this.password;}
    public void setName(String name){this.name = name;}
    public void setId(int id){this.id = id;}
    public void setPassword(String password){this.password = password;}
    
    /**
     * display account info
     * Connect to GUI
     */
    
    public abstract void display();
    /**
     * Get daliy report of money in/out
     * diplay by GUI
     * @param, date for report
     */
    public abstract void getDailyReport(Date date);
    
    /** transfer money to another accoun */
    public abstract void transfer(Account otherAccount);
}
