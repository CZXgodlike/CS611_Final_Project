package UserInterface;

import Game.Legends;
import Helper.MyScanner;
import Map.Cell;
import Map.LegendMap;

/**
 * Class represents a controller of the game
 */
public class Controller {

    private final MyScanner scanner;
    private int x;
    private int y;
    private final LegendMap map;
    private static final String red = "\033[0;31m";

    public Controller(Legends legends){
        scanner = new MyScanner(legends);
        this.map = legends.getMap();
        getPlayerPosition();
    }

    public void getPlayerPosition(){
        for(int i = 0; i < map.getLength(); i++){
            for (int j = 0; j < map.getWidth(); j++){
                if(map.getCell(i, j).hasPlayer()){
                    x = i;
                    y = j;
                    return;
                }
            }
        }
    }

    public void getNextMove(){
        String s;
        Cell currentCell = map.getCell(x, y);
        Cell nextCell;

        while(true){
            try{
                s = scanner.nextLine();
                if(s.equalsIgnoreCase("a")){
                    if(y == 0 || !map.getCell(x, y-1).canEnter()){
                        System.out.println(red + "Cannot move left.");
                    } else {
                        nextCell = map.getCell(x, y - 1);
                        y = y - 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("d")){
                    if(y == map.getWidth() - 1 || !map.getCell(x, y+1).canEnter()){
                        System.out.println(red + "Cannot move right.");
                    } else {
                        nextCell = map.getCell(x, y+1);
                        y = y + 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("w")){
                    if(x == 0 || !map.getCell(x-1, y).canEnter()){
                        System.out.println(red + "Cannot move up.");
                    } else {
                        nextCell = map.getCell(x-1, y);
                        x -= 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("s")){
                    if(x == map.getLength() || !map.getCell(x+1, y).canEnter()){
                        System.out.println(red + "Cannot move down");
                    } else {
                        nextCell = map.getCell(x+1, y);
                        x += 1;
                        break;
                    }
                } else if(s.equalsIgnoreCase("m")){
                    map.printGrids();
                    System.out.println("\033[0;36m" + "Please continue your move:");
                } else{
                    System.out.println(red + "Please enter w/s/a/d to move:");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please try again!");
            }
        }
        nextCell.playerEntered();
        currentCell.playerLeft();
        map.printGrids();
    }

    public Cell getCurrentCell(){
        return map.getCell(x, y);
    }
}
