package Fight;

import Heros.Hero;
import Monsters.Monster;
import UserInterface.FightInterface;

/**
 * Class to represent a 1 on 1 fight between hero and monster
 */

public class Fight {
    private final Hero hero;
    private final Monster monster;
    private final FightInterface fi;

    public Fight(Hero hero, Monster monster){
        this.hero = hero;
        this.monster = monster;
        fi = new FightInterface(this);
    }

    public void round(){
        System.out.println("\033[0;37m" + "\u2012".repeat(50));
        System.out.println(this);
        hero.attack(fi.getAttackType(), monster, fi);
        if(!monster.isDead()){
            monster.attack(hero);
        }
        hero.regain();
        hero.printStat();
        if(isOver()){
            if(monster.isDead() || hero.isFaint()){
                if(hero.isFaint()){
                    System.out.println("\033[0;31m" + hero + " is faint!");
                } else {
                    System.out.println("\033[0;32m" + hero + " wins!");
                }
            }
        }
    }

    public Hero getHero() {
        return hero;
    }

    public Monster getMonster() {
        return monster;
    }

    public boolean isOver(){
        return monster.isDead() || hero.isFaint();
    }

    @Override
    public String toString() {
        return "fight between " + hero + " and " + monster;
    }
}
