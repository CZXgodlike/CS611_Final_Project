package Game;

import Fight.FreeForAll;
import Map.LegendMap;
import Player.Player;
import UserInterface.Controller;
import UserInterface.*;

/**
 * Body of Legends: Hero and Monster
 */

public class Legends extends RPGGame{
    private Controller controller;
    private LegendMap map;
    private UserInterface ui;
    private Manual manual;

    public Legends(){
        ui = new UserInterface();
        ui.printBanner();
        map = new LegendMap(ui.getMapLength(), ui.getMapWidth());
        player = new Player(ui);
        initializeGame();
        controller = new Controller(this);
        manual = new Manual(this);
    }

    public void initializeGame(){
        map.setPlayer();
    }

    public boolean meetMonsters(){
        double rnd = Math.random();
        return rnd < 0.3;
    }

    public Manual getManual() {
        return manual;
    }

    public LegendMap getMap() {
        return map;
    }

    public void start(){
        map.printGrids();
        while(true){
            controller.getNextMove();
            if(!controller.getCurrentCell().hasMarket() && meetMonsters()){
                FreeForAll freeForAll = new FreeForAll(player.getHeroes());
                freeForAll.start();
                map.printGrids();
            } else if(controller.getCurrentCell().hasMarket()){
                MarketInterface mi = new MarketInterface(this);
                map.printGrids();
                System.out.println("\033[0;35m" + "Please continue your move:");
            }
        }
    }
}
