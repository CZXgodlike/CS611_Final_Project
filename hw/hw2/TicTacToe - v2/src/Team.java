//This class defines a team and its property. A team is an array of player.

import java.util.*;

public class Team{
    private Player[] team;
    private int team_size;
    private String team_name;
    private int total_score;
    private String sign = null;

    //Constructor
    public Team(int team_size, String sign, String team_name){
        this.team_size = team_size;
        this.sign = sign;
        this.team_name = team_name;
        team = new Player[team_size];
        for(int i = 1; i <= team_size; i++){
            team[i - 1] = new Player(sign, "player" + i, this);
        }
    }

    public Team(int team_size, String team_name){
        this.team_size = team_size;
        this.team_name = team_name;
        team = new Player[team_size];
        for(int i = 1; i <= team_size; i++){
            team[i - 1] = new Player("player" + i, this);
        }//Initialize team members
    }

    //No arg constructor
    public Team(){
        this.total_score = 0;
    }

    public String toString() {
        return team_name;
    }

    public String getTeam_name(){
        return team_name;
    }

    public int getScore(){
        return total_score;
    }

    //Allow user to customize their team's name
    public void rename(String used_name) {
        System.out.println("Please customize a team name for " + team_name);
        String name;
        Scanner sc = new Scanner(System.in);

        while(true){
            try{
                name = sc.nextLine();
                if(name.equalsIgnoreCase(used_name)){
                    System.out.println("Name taken! Please enter another name:");
                    continue;
                }//Avoid using same name
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input! Please try again:");
                continue;
            }
            this.team_name = name;
            return;
        }
    }

    //Allow user to decide how many players in the team
    public void setTeam_size() {
        System.out.println("Please enter how many players in " + team_name);
        int size;
        Scanner sc = new Scanner(System.in);

        while(true){
            try {
                size = sc.nextInt();
                if(size <= 0) {
                    System.out.println("Invalid input. Please try again:");
                    continue;
                }
            } catch(InputMismatchException e) {
                System.out.println("Invalid input. Please try again:");
                sc.next();
                continue;
            }//End of validation
            team = new Player[size];
            team_size = size;
            for(int i = 1; i <= team_size; i++){
                team[i - 1] = new Player(sign, "player" + i, this);
            }
            return;
        }
    }

    //Allow user to select their own name for the players
    public void setPlayerName(){
        System.out.println("For players in team " + team_name +", please select your username:");
        for(int i = 0; i < team_size; i ++){
            team[i].rename("");
            for(int j = 0; j < i; j ++){
                while(team[i].getName().equals(team[j].getName())){
                    System.out.println("Username taken! Please enter another name!");
                    team[i].setName("player" + (i + 1));
                    team[i].rename("");
                }
            }
        }
    }

    //Print total score and players' score
    public void printScore(){
        System.out.println(team_name + " 's total score: " + total_score);
        for(Player p: team){
            p.printScore();
        }
    }

    public void scoreIncrease(){
        total_score ++;
    }

    //Randomly select a player
    public Player selectRandomPlayer(){
        int rnd = new Random().nextInt(team.length);
        return team[rnd];
    }

    //Used for deciding MVP
    public ArrayList<Player> selectBestPlayer(){
        ArrayList<Player> best_player = new ArrayList<>();
        int best_score = 0;
        for(Player p: team){
            if(p.getScore() > best_score){
                best_player.clear();
                best_player.add(p);
                best_score = p.getScore();
            } else {
                if (p.getScore() == best_score){
                    best_player.add(p);
                }
            }
        }
        return best_player;
    }
}
