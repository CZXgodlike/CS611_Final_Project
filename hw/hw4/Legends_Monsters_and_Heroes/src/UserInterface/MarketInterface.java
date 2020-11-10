package UserInterface;

import Game.Legends;
import Helper.DataBase;
import Helper.MyScanner;
import Heros.Hero;
import Items.Item;
import Market.Market;
import Player.Player;

import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Class represents the interface of the market
 */
public class MarketInterface implements checkYesOrNo{

    private Market market;
    private MyScanner scanner;
    private Player customer;
    private static final String red = "\033[0;31m";
    private static final String cyan = "\033[0;36m";

    public MarketInterface(){}

    public MarketInterface(Legends legends){
        this.market = new Market();
        scanner = new MyScanner(legends);
        this.customer = legends.getPlayer();
        printWelcomeMsg();
        if(yesOrNo("enter the market")){
            start();
        }
    }

    public void printWelcomeMsg(){
        StringBuilder marketBanner = new Graphic().getMarket();
        System.out.println(marketBanner);
    }

    public void showList(String category){
        DataBase dataBase = new DataBase();
        switch (category.toLowerCase()) {
            case "armor" -> dataBase.print_armor_list();
            case "spell" -> dataBase.print_spell_list();
            case "potion" -> dataBase.print_potion_list();
            case "weapon" -> dataBase.print_weapon_list();
        }
    }

    public Hero getHero(Player player){
        System.out.println(cyan + "Please select the hero you want to buy for:" + Arrays.toString(customer.getHeroes()));
        String name;

        while(true){
            try{
                name = scanner.nextLine();
                if(player.hasHero(name)){
                    break;
                } else if(name.equalsIgnoreCase("m")){
                    System.out.println(cyan + "Please select the hero you want to buy for:" + Arrays.toString(customer.getHeroes()));
                } else {
                    System.out.println(red + "You don't have hero named " + name + ". Please check spell and enter again:");
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again:");
                scanner.next();
            }
        }
        return player.getHero(name);
    }

    public void getCategory(){
        System.out.println(cyan + "Please select what kind of item you want to buy(Armor/Weapon/Spell/Potion)");
        String category;

        while(true){
            try{
                category = scanner.nextLine();
                if(category.equalsIgnoreCase("armor")
                || category.equalsIgnoreCase("weapon")
                || category.equalsIgnoreCase("spell")
                || category.equalsIgnoreCase("potion")){
                    showList(category);
                    break;
                } else if(category.equalsIgnoreCase("m")){
                    System.out.println(cyan + "Please select what kind of item you want to buy(Armor/Weapon/Spell/Potion)");
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again:");
                scanner.next();
            }
        }
    }

    public String getItemName(){
        System.out.println(cyan + "Please enter the item name you want to buy or enter q to go back:");
        String name;
        DataBase dataBase = new DataBase();

        while(true){
            try{
                name = scanner.nextLine();
                if(!dataBase.inquire(name).isEmpty()){
                    return name;
                } else if(name.equalsIgnoreCase("m")){
                    System.out.println(cyan + "Please enter the item name you want to buy or enter q to go back:");
                } else if(name.equalsIgnoreCase("q")){
                    return "q";
                } else {
                    System.out.println(red + "No item called " + name + ". Please check your spell and enter again.");
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again:");
                scanner.next();
            }
        }
    }

    public void sellItem(Hero hero){
        String name = getItemName();
        if(name.equals("q")){
            return;
        } else {
            Item item = market.getItem(name);
            if(hero.canBuy(item)){
                if(yesOrNo("buy " + item)){
                    hero.buy(item);
                } else {
                    sellItem(hero);
                }
            } else {
                sellItem(hero);
            }
        }
    }

    public void start(){
        Hero hero;
        if(customer.getHeroes().length == 1){
            hero = customer.getHeroes()[0];
        } else {
            hero = getHero(customer);
        }
        getCategory();
        sellItem(hero);
        if(yesOrNo("buy other items")){
            start();
        }
    }
}
