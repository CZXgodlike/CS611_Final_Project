package Fight;

import Helper.DataBase;
import Helper.MonsterFactory;
import Heros.Hero;
import Monsters.Monster;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to represent the fights between all the heroes and monsters
 */

public class FreeForAll {

    private Hero[] heroes;
    private Monster[] monsters;
    private List<Fight> fights;

    public FreeForAll(Hero[] heroes){
        System.out.println("\033[0;31m" + "Oh no! You encountered with a group of monsters!\n");
        this.heroes = heroes;
        createMonsters();
        fights = new ArrayList<>(heroes.length);
        matchFights();
        start();
        reward();
    }

    public FreeForAll(){}

    public Monster firstLiveMonster(){
        for(Monster monster: monsters){
            if(!monster.isDead()){
                return monster;
            }
        }
        return null;
    }

    public Hero firstLiveHero(){
        for(Hero hero: heroes){
            if(!hero.isFaint()){
                return hero;
            }
        }
        return null;
    }


    public boolean notInFight(Monster monster){
        for(Fight fight: fights){
            if(!fight.isOver()){
                if(fight.getMonster().equals(monster)){
                    return false;
                }
            }
        }
        return true;
    }

    public boolean notInFight(Hero hero){
        for(Fight fight: fights){
            if(!fight.isOver()){
                if(fight.getHero().equals(hero)){
                    return false;
                }
            }
        }
        return true;
    }

    public void matchFights(){
        for(int i = 0; i < heroes.length; i++){
            fights.add(new Fight(heroes[i], monsters[i]));
        }
    }

    public void rematch(){
        if(isOver()){
            fights.clear();
        } else {
            List<Fight> newFights = new ArrayList<>();
            for(Fight fight: fights){
                if(!fight.isOver()){
                    newFights.add(fight);
                }
            }
            for(Monster monster: monsters){
                if(notInFight(monster) && !monster.isDead()){
                    newFights.add(new Fight(firstLiveHero(), monster));
                }
            }
            for(Hero hero: heroes){
                if(notInFight(hero) && !hero.isFaint()){
                    newFights.add(new Fight(hero, firstLiveMonster()));
                }
            }
            fights = newFights;
        }
    }

    public boolean hasLiveMonsters(){
        for(Monster monster: monsters){
            if(!monster.isDead()){
                return true;
            }
        }
        return false;
    }

    public boolean hasLiveHero(){
        for(Hero hero: heroes){
            if(!hero.isFaint()){
                return true;
            }
        }
        return false;
    }

    public boolean hasFinishedFight(){
        for(Fight fight: fights){
            if(fight.isOver()){
                return true;
            }
        }
        return false;
    }

    public int getLowestLevel(){
        int minLevel = 10;
        for(Hero hero: heroes){
            if(hero.getLevel() < minLevel){
                minLevel = hero.getLevel();
            }
        }
        return minLevel;
    }

    public void createMonsters(){
        MonsterFactory monsterFactory = new MonsterFactory();
        DataBase dataBase = new DataBase();
        monsters = monsterFactory.createMonsters(dataBase.getMonsters(getLowestLevel()), heroes.length);
    }

    public boolean isOver(){
        return !hasLiveHero() || !hasLiveMonsters();
    }

    public void reward(){
        for(Hero hero: heroes){
            if(!hero.isFaint()){
                hero.getReward();
            }
        }
    }

    public void start(){
        while (!isOver()){
            while(!hasFinishedFight()){
                for(Fight fight: fights){
                    if(!isOver()){
                        fight.round();
                    } else {
                        break;
                    }
                }
            }
            rematch();//If there is fight over, rematch fights and start over
            start();
        }
    }
}
