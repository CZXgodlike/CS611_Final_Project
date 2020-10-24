/**
 * This class represents a player for BlackJack.
 * A player can do hit, stand, split and double up.
 */

import java.util.*;

public class Player extends Human{

    private ArrayList<Hand> hands;
    private final Wallet wallet;

    public Player(String name){
        super(name);
        hands = new ArrayList<>();
        hands.add(new Hand());
        wallet = new Wallet(100);
    }

    public Player(){
        hands = new ArrayList<>();
        hands.add(new Hand());
        wallet = new Wallet(100);
    }

    public ArrayList<Hand> getHands() {
        return hands;
    }

    public Wallet getWallet() {
        return wallet;
    }


    /**Ask the player to customize his/her name
     * @param dealerName prevent players from having the same name as the dealer
     */
    public void rename(String dealerName){
        System.out.println("Please enter " + this + "'s name:");
        String name;
        Scanner sc = new Scanner(System.in);

        while(true){
            try{
                name = sc.nextLine();
                if(name.equalsIgnoreCase(dealerName)){
                    System.out.println("That's the dealer's name! Please enter another name:");
                    continue;
                }//Avoid using same name as the dealer
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please try again:");
                continue;
            }
            this.name = name;
            return;
        }
    }

    /**
     * Ask player to place their bet.
     * @param min the minimum bet player can bet
     * @param max the maximum bet player can bet
     */
    public void placeBet(int min, int max){
        System.out.println(this + ", please place your bet:");
        Scanner sc = new Scanner(System.in);
        int bet;

        while(true){
            try {
                bet = sc.nextInt();
                if(bet > max || bet < min){
                    System.out.println("Invalid number! Please enter a number between " + min + " and " + max + ":");
                    continue;
                } else if(bet > wallet.getValue()){
                    System.out.println("Insufficient balance! Please enter a smaller bet:");
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid number! Please enter a number between " + min + " and " + max + ":");
                sc.next();
                continue;
            }
            hands.get(0).setBet(bet);
            wallet.pay(bet);
            return;
        }
    }

    /**
     * Player gets double of the bets if wins
     * @param amount the bet player wins
     */
    public void wins(int amount){
        System.out.println(this + " wins $" + amount);
        wallet.get(2 * amount);
    }

    public void loses(int amount){
        System.out.println(this + " loses $" + amount);
    }

    /**
     * Check if player can double up
     * @param bet bet going to be doubled
     * @return can double or not
     */
    public Boolean canDouble(int bet){
        return wallet.getValue() >= bet;
    }

    //A player is defined as bankrupt if the money in his wallet is less than the minimum bet
    public Boolean isBankrupt(int min){
        return wallet.getValue() < min;
    }

    /**
     * Helper function for reading player's action from terminal
     * @param hand get action for the hand
     * @return the name of the action
     */
    public String getAction(Hand hand){
        System.out.println("Current hand: " + hand + " with bet: " + hand.getBet() + "\n"
                + "Please indicate your next action (Hit, Stand, Split, Double up):");
        Scanner sc = new Scanner(System.in);
        String action;

        while (true) {
            try {
                action = sc.nextLine();
                if (!(action.equalsIgnoreCase("Hit") ||
                        action.equalsIgnoreCase("Stand") ||
                        action.equalsIgnoreCase("Split") ||
                        action.equalsIgnoreCase("Double up"))) {
                    System.out.println("Invalid input! Please enter Hit, Stand, Split or Double up:");
                    continue;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please enter Hit, Stand, Split or Double up:");
                continue;
            }//End of validation and error handling
            return action.toLowerCase();
        }
    }

    /**
     * Cash out and quit game
     * @return cash out or not
     */
    public Boolean cashOut(){
        System.out.println(this + ", do you want to cash out? (Y/N)");
        Scanner sc = new Scanner(System.in);
        String s;

        //Validate input and error handling
        while (true) {
            try {
                s = sc.nextLine();
                if (!(s.equalsIgnoreCase("Yes") ||
                        s.equalsIgnoreCase("Y") ||
                        s.equalsIgnoreCase("No") ||
                        s.equalsIgnoreCase("N"))) {
                    System.out.println("Invalid input! Please enter Y/N:");
                    continue;
                }
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please enter Y/N:");
                continue;
            }//End of validation and error handling
            return s.equalsIgnoreCase("Yes") || s.equalsIgnoreCase("Y");
        }
    }

    /**
     * Prepare for a new game
     */
    public void renew(){
        hands = new ArrayList<>();
        hands.add(new Hand());
    }

    /**
     * Player's turn
     * @param dealer
     */
    public void go(Dealer dealer) {
        System.out.println();
        System.out.println(this + "'s turn.");
        goFrom(dealer, 0);
    }

    /**
     * This function will iterate when player choose split.
     * The iteration of this function updates the hands ArrayList to the newest one with newly added elements.
     * To iterate from the start, set index to 0.
     * @param dealer
     * @param index: where the iteration inside hands starts from
     */
    public void goFrom(Dealer dealer, int index){
        String action;
        for(int i = index; i < hands.size(); i ++){
            Hand hand = hands.get(i);
            System.out.println("Please take action for hand" + (i + 1) + ":");
            if(hand.hasNaturalBlackJack()){
                System.out.println("You have BlackJack on this hand!");
                continue;
            }
            while(true){
                action = getAction(hand);

                if (action.equals("hit")){
                    dealer.Hit(hand);
                    if(hand.isBust()){
                        System.out.println("Current hand: " + hand);
                        System.out.println("You bust!");
                        break;
                    }
                } //Hit
                else if(action.equals("stand")){
                    break;
                } //Stand
                else if(action.equals("split")){
                    if(hand.canSplit() && wallet.getValue() >= hand.getBet()){
                        dealer.Split(hand, this);
                        goFrom(dealer, hands.indexOf(hand));
                        return;
                    } else {
                        if( wallet.getValue() < hand.getBet()){
                            System.out.println("Insufficient balance! Please choose another action:");
                        }else{
                            System.out.println("Can't split! Please choose another action:");
                        }
                    }
                } //Split
                else {
                    if(canDouble(hand.getBet())){
                        wallet.pay(hand.getBet());
                        dealer.Double(hand);
                        if (hand.isBust()){
                            System.out.println("You bust!");
                        }
                        break;
                    } else {
                        System.out.println("Insufficient balance! Please choose another action:");
                    }
                } //Double up
            }
            System.out.println("This hand finally has " + hand + " with total: " + hand.getMaxTotal());
        }
    }

}
