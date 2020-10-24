/**
 * Class representing a player's wallet
 * It takes care of player's money.
 */
public class Wallet {
    private int value;

    public Wallet(int value){
        this.value = value;
    }

    public Wallet(){}

    public int getValue() {
        return value;
    }

    //Receive money
    public void get(int amount){
        value += amount;
    }

    //Pay money
    public void pay(int amount){
        value -= amount;
    }

    public String toString() {
        return "$ " + value;
    }
}
