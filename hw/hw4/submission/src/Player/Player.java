package Player;

import Helper.HeroFactory;
import Heros.Hero;
import UserInterface.UserInterface;

/**
 * Class represents a player of Legends: Monster and Heroes
 */
public class Player {

    private final Hero[] heroes;
    private String name;

    public Player(UserInterface ui){
        heroes = new Hero[ui.getHeroNumber(3)];
        createHeroes(ui);
    }

    private void createHeroes(UserInterface ui){
        HeroFactory hf = new HeroFactory();
        for(int i = 0; i < heroes.length; i++){
            String name;
            while(hasHero(name = ui.getHeroName())){
                System.out.println("\033[0;31m" + "You have already had this hero! Please select another one:");
            }
            heroes[i] = hf.create(name);
        }
    }

    public boolean hasHero(String name){
        for(Hero hero: heroes){
            if(hero == null){
            } else if(hero.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    public Hero getHero(String name){
        for(Hero hero: heroes){
            if(hero.getName().equalsIgnoreCase(name)){
                return hero;
            }
        }
        return null;
    }

    public Hero[] getHeroes() {
        return heroes;
    }

    public void printInventory(){
        for(Hero hero: heroes){
            hero.getInventory().printInventory();
        }
    }

    @Override
    public String toString() {
        return name;
    }
}
