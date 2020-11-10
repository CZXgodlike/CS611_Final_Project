package UserInterface;

import Fight.Fight;
import Helper.DataBase;
import Helper.ItemFactory;
import Heros.Hero;
import Items.Spell;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Class used for interacting with player about the fight
 */
public class FightInterface {


    private final Scanner scanner;
    private static final String purple = "\033[0;35m";
    private static final String red = "\033[0;31m";
    private Fight fight;

    public FightInterface(Fight fight){
        scanner = new Scanner(System.in);
        this.fight = fight;
    }

    public String getAttackType(){
        System.out.println(purple + "Please enter your attack type for " + fight.getHero() + " (normal/spell):");
        String type;

        while(true){
            try{
                type = scanner.nextLine();
                if(!type.equalsIgnoreCase("spell") && !type.equalsIgnoreCase("normal") && !type.equals(" ")){
                    System.out.println(red + "Invalid input. Please enter (normal/spell):");
                    continue;
                } else if(type.equalsIgnoreCase("spell")){
                    if(fight.getHero().getSpells().isEmpty() || !fight.getHero().hasAvailableSpell()){
                        System.out.println(red + fight.getHero() + " doesn't have any spell available!");
                        continue;
                    }
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again (normal/spell):");
                scanner.next();
                continue;
            }
            return type;
        }
    }

    public Spell getSpell(Hero hero){
        System.out.println(purple + "Please enter the name of the spell you want to use:" + fight.getHero().getSpells());
        String name;
        DataBase dataBase = new DataBase();

        while(true){
            try{
                name = scanner.nextLine();
                if(dataBase.inquire(name).isEmpty()){
                    System.out.println(red + "No spell with this name. Please check your spell and try again:");
                    continue;
                } else if(! hero.hasSpell(name)){
                    System.out.println(red + hero + " doesn't have this spell. Please check your spell and try again:");
                    continue;
                } else if(! hero.canUse(name)){
                    System.out.println(red + hero + " can't use this spell. Please enter another one:");
                    continue;
                }
            } catch (InputMismatchException e){
                System.out.println(red + "Invalid input. Please enter again:");
                scanner.next();
                continue;
            }
            ItemFactory itemFactory = new ItemFactory();
            return (Spell) itemFactory.create(name);
        }
    }
}
