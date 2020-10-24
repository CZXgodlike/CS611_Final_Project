//This class defines players and their property and contains functions a player can do

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Player {

    private String sign;
    private int score;
    private String name;
    private Team team;

    public Player(String sign, String name, Team team){
        this.sign = sign;
        this.score = 0;
        this.name = name;
        this.team = team;
    }

    public Player(String name, Team team){
        this.score = 0;
        this.name = name;
        this.team = team;
    }

    //No arg constructor
    public Player() {
    }

    public String toString(){
        return name;
    }

    public String getSign(){
        return this.sign;
    }

    public int getScore(){
        return this.score;
    }

    public String getName(){
        return name;
    }

    public Team getTeam(){
        return team;
    }

    //Allow player to select the sign they want to use for this turn
    public void selectSign() {
        System.out.println("Please select which sign you want to use (O/X):");
        String sign;
        Scanner sc = new Scanner(System.in);

        while(true){
            try{
                sign = sc.nextLine();
                if(!(sign.equalsIgnoreCase("O") || sign.equalsIgnoreCase("X"))){
                    System.out.println("Invalid input! Please try again (O/X):");
                    continue;
                }//Avoid using same name
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please try again:");
                continue;
            }
            this.sign = sign.toUpperCase();
            return;
        }
    }

    //Increase player's score
    public void scoreIncrease(){
        score ++;
    }

    public void setName(String name){
        this.name = name;
    }

    //Allow player to customize their name
    public void rename(String used_name){
        System.out.println("Please enter " + this + "'s name:");
        String name;
        Scanner sc = new Scanner(System.in);

        while(true){
            try{
               name = sc.nextLine();
               if(name.equalsIgnoreCase(used_name)){
                   System.out.println("Name used! Please enter another name:");
                   continue;
               }//Avoid using same name
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please try again:");
                continue;
            }
            this.name = name;
            return;
        }
    }

    public void printScore(){
        System.out.println(name + "'s score: " + score);
    }

}
