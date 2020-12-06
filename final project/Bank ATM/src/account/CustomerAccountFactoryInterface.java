package account;

import account.CustomerAccount;

public interface CustomerAccountFactoryInterface {
    abstract CustomerAccount getCustomerAccount(String customerAccountName);
}
