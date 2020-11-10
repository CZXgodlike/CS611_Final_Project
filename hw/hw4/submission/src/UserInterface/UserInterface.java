package UserInterface;

import Helper.DataBase;
import Helper.ItemFactory;
import Heros.Hero;
import Items.Spell;
import Market.Market;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class represent the interface of Legends: Monsters and Heroes
 */
public class UserInterface implements checkYesOrNo{

    private final Scanner scanner;
    private static final String purple = "\033[0;35m";
    private static final String red = "\033[0;31m";
    private final Graphic graphic;

    public UserInterface(){
        scanner = new Scanner(System.in);
        graphic = new Graphic();
    }


    public int getHeroNumber(int maxNumber){
        System.out.println(purple + "Please select the number of your heroes (1-" + maxNumber + "): ");
        int number;

        while(true){
            try{
                number = scanner.nextInt();
                if(number < 1 || number > maxNumber){
                    System.out.println(red + "Invalid input. Please enter a number between 1-" + maxNumber + ": ");
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter a number between 1-" + maxNumber + ": ");
                scanner.next();
                continue;
            }
            return number;
        }
    }

    public String getHeroName(){
        System.out.println(purple + "Please enter the name of your hero:");
        String name;
        DataBase dataBase = new DataBase();
        dataBase.printHeroList();
        scanner.nextLine();

        while(true){
            try{
                name = scanner.nextLine();
                if(dataBase.inquire(name).isEmpty()){
                    System.out.println(red + "No hero with name " + name + ". Please check your spell and try again:");
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again:");
                scanner.next();
                continue;
            }
            return name;
        }
    }

    public int getMapLength(){
        System.out.println(purple + "Please enter the length of map (3~50):");
        int length;

        while(true){
            try{
                length = scanner.nextInt();
                if(length > 50 || length <= 2){
                    System.out.println(red + "Please enter a number between 3 and 50:");
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter a number between 3 and 50:");
                scanner.next();
                continue;
            }
            return length;
        }
    }

    public int getMapWidth(){
        System.out.println(purple + "Please enter the width of map (3~50):");
        int width;

        while(true){
            try{
                width = scanner.nextInt();
                if(width > 50 || width <= 0){
                    System.out.println(red + "Please enter a number between 3 and 50:");
                } else {
                    break;
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter a number between 3 and 50:");
                scanner.next();
            }
        }
        return width;
    }

    public void enterMarket(Market market){
        if(yesOrNo("enter the market")){
            MarketInterface marketInterface = new MarketInterface();
        }
    }

    public void printBanner(){
        System.out.println(graphic.getBanner());
    }



}
