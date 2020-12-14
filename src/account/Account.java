package account;
/**
 * BankATM
 *
 * @author Zhuyun Chen
 * @note Part of final
 * Abstract account class
 */
import java.io.File;
import java.util.*;

public abstract class Account{

    protected String name; // name of the account owner
    protected int id; // Account/Rountine number

    /** Default Constructor */
    public Account(){
        this.name = "TempAccount";
        this.id = 0;
    }

    public Account(String name, int id){
        this.name = name;
        this.id = id;
    }

    /** Utilities */
    public String getName(){return this.name;}
    public int getId(){return this.id;}
    public void setName(String name){this.name = name;}
    public void setId(int id){this.id = id;}

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
    public abstract void transfer(Account otherAccount, double amount, int addOrSub);
}

