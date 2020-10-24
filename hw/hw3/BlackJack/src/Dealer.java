/**
 * Class representing a dealer.
 * Dealer must stand on or over 17.
 */

import java.util.ArrayList;

public class Dealer extends Human{
    private Deck deck;
    private Hand hand;
    private int profit;

    public Dealer(){
        super("Dealer Jack");
        hand = new Hand();
        deck = new Deck();
        profit = 0;
    }

    /**
     * Deal cards to the players and the dealer himself alternatively
     * @param players all players in the game
     */
    public void dealCards(ArrayList<Player> players){
        System.out.println("Dealing cards...");

        for(int i = 1; i <= 2; i++){
            for(Player player: players){
                player.getHands().get(0).add(deck.pop());
            }
            hand.add(deck.pop());
        }

        System.out.println();

        //Announce the cards every player got
        for(Player player: players){
            System.out.println(player + " gets: " + player.getHands().get(0).toString());
        }

        System.out.println();
        System.out.println(this + " gets: " + hand.get(0)+ " and another card.");
    }

    public void Hit(Hand hand){
        hand.add(deck.pop());
    }

    /**
     * Create a new hand and give the second card to it, then deal a card to each of the hands
     * @param hand the hand to be split
     * @param player the player asked to split
     */
    public void Split(Hand hand, Player player){
        //Split
        player.getHands().add(new Hand(hand.remove(1), hand.getBet()));
        player.getWallet().pay(hand.getBet());
        //Deal
        int size = player.getHands().size();
        Hit(hand);
        Hit(player.getHands().get(size - 1));
    }

    public void Double(Hand hand){
        hand.setBet(2 * hand.getBet());
        Hit(hand);
    }

    /**
     * Show dealer's total profit
     */
    public void showProfit(){
        System.out.println(this + "'s profit is: $" + profit);
    }

    /**
     * Dealer lose profit
     * @param amount the amount dealer loses
     */
    public void loseProfit(int amount){
        profit -= amount;
    }

    /**
     * Dealer earn profit
     * @param amount the samount dealer earns
     */
    public void earnProfit(int amount){
        profit += amount;
    }
    /**Here we practice the rule that the dealer stands on soft 17 (Ace and six).
     * The Dealer's first ace counts as 11 unless. Subsequent aces count as one.
     * Compare the value of the dealer to players and check out.
     * @param players all players in the game
     */
    public void go(ArrayList<Player> players){
        //First the dealer should turn over his second card
        System.out.println();
        System.out.println(this + "'s second card is: " + hand.get(1));

        //Then deal card to himself until the total is over 17
        int total;
        Boolean hasAce = hand.hasAce();
        System.out.println();
        System.out.println("Dealing cards to " + this + "...");
        while(true){
            if(hasAce){
                total = hand.getMinTotal() + 10;
            } else {
                total = hand.getMinTotal();
            }
            if (total < 17){
                Hit(hand);
            } else {
                break;
            }
        }//End of hit
        System.out.println(this + " finally gets: " + hand);
        System.out.println("Dealer's total: " + total);
        System.out.println();
        hand.setTotal(total);

        //If dealer busts, everyone win
        if(total > 21){
            System.out.println(this + " busts! Everyone win!");
        }

        //Check out to players
        System.out.println("Checking out ...");
        System.out.println();
        for(Player player: players){
            for(Hand hand: player.getHands()){
                if(this.hand.compareTo(hand) < 0){
                    player.wins(hand.getBet());
                    loseProfit(hand.getBet());
                } else if(this.hand.compareTo(hand) == 0){
                    player.getWallet().get(hand.getBet());
                    System.out.println("Returned $" + hand.getBet() + " to "+ player);
                } else{
                    player.loses(hand.getBet());
                    earnProfit(hand.getBet());
                }
            }
        }
    }

    /**
     * Prepare for a new game
     */
    public void renew(){
        hand = new Hand();
        deck = new Deck();
    }

}
