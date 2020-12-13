package account;

public class CustomerAccountFactory {

    public CustomerAccount getCustomerAccount(String userName, String accountType){
        if(accountType == null){
            return null;
        }
        if(accountType.equalsIgnoreCase("CHECKING")){
            return new CheckingAccount(userName);

        } else if(accountType.equalsIgnoreCase("SAVING")){
            return new SavingAccount();

        } else if(accountType.equalsIgnoreCase("SECURITIES")){
            return new SecuritiesAccount();
        }

        return null;
    }
    
}
