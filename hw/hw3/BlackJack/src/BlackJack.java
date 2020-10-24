/**
 * Class representing the body of BlackJack.
 * Also with some help functions.
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Scanner;

public class BlackJack {

    private final Dealer dealer;
    private ArrayList<Player> players;
    private final int minBet = 5;
    private final int maxBet = 50;
    private final int minPlayer = 1;
    private final int maxPlayer = 7;

    public BlackJack(){
        makeAnnouncement();
        dealer = new Dealer();
        setPlayers();
    }

    /**
     * Welcome players and announce some basic rules.
     */
    public void makeAnnouncement(){
        String announcement = "Welcome to BlackJack! Please read the following rules carefully:" + "\n"
                + "The min bet is $" + minBet + ". The max bet is $" + maxBet + "!" + "\n"
                + "The min required player number is " + minPlayer + ". The max allowed player number is " + maxPlayer + "!" + "\n"
                + "Your initial property is $100!";
        System.out.println(announcement);
    }

    /**
     * Ask user to decide the number of players and initialize an arraylist of players
     */
    public void setPlayers(){
        System.out.println();
        System.out.println("First, please enter the number of players:");
        Scanner sc = new Scanner(System.in);
        int number;

        while(true){
            try {
                number = sc.nextInt();
                if(number > maxPlayer || number < minPlayer){
                    System.out.println("Please enter a number between " + minPlayer + " and " + maxPlayer+ ":");
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println("Invalid input! Please enter a number between " + minPlayer + " and " + maxPlayer + ":");
                sc.next();
                continue;
            }
            players = new ArrayList<>(number);
            for(int i = 0; i < number; i ++){
                players.add(new Player("Player " + (i + 1)));
            }
            setNames();
            return;
        }
    }

    /**
     * Ask player to change the default name
     */
    public void setNames(){
        System.out.println();
        System.out.println("Welcome! Please set your username!");
        for(int i = 0; i < players.size(); i ++){
            Player player = players.get(i);
            player.rename("Dealer Jack");
            for(int j = 0; j < i; j ++){
                while(player.getName().equals(players.get(j).getName())){
                    System.out.println("Username taken! Please enter another name!");
                    player.setName("player" + (i + 1));
                    player.rename("Dealer Jack");
                }
            }
        }
    }

    /**
     * Ask players to place bets
     */
    public void placeBets(){
        System.out.println();
        System.out.println("Let's place your bet!");
        System.out.println();
        for(Player player: players){
            player.placeBet(minBet, maxBet);
        }
        System.out.println();
    }

    //Show balance of players
    public void showBalance(){
        for(Player player: players){
            System.out.println(player + "'s balance: " + player.getWallet());
        }
        System.out.println();
        dealer.showProfit();
        System.out.println();
    }

    /**
     * Ask players if they want to cash out
     */
    public void cashOut(){
        Iterator<Player> iter = players.iterator();
        while (iter.hasNext()){
            Player player = iter.next();
            if(player.cashOut()){
                iter.remove();
                System.out.println(player + " has cashed out!");
            }
        }
    }

    /**Ruthless function
     * Kick out the players who can't afford the min bet.
     */
    public void kickOut(){
        Iterator<Player> iter = players.iterator();
        while (iter.hasNext()){
            Player player = iter.next();
            if(player.isBankrupt(minBet)){
                iter.remove();
                System.out.println(player + " runs out of cash!");
            }
        }
    }

    /**
     * Refresh the game to start a new round
     */
    public void renew(){
        kickOut();
        cashOut();
        for(Player player: players){
            player.renew();
        }
        dealer.renew();
        go();
    }

    /**
     * Game starts
     */
    public void go(){
        while(players.size() > 0){
            System.out.println("\n" + "Round starts!");
            placeBets();
            dealer.dealCards(players);
            for(Player player: players){
                player.go(dealer);
            }
            dealer.go(players);
            showBalance();
            renew();
        }
    }
}
