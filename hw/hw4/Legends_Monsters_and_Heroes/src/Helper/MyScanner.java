package Helper;

import Game.Legends;

import java.util.Scanner;

/**
 * Self defined scanner
 * Used for detecting user input to enter the manual
 */

public class MyScanner {

    private final Scanner scanner;
    private final Legends legends;

    public MyScanner(Legends legends){
        scanner = new Scanner(System.in);
        this.legends = legends;
    }

    public String nextLine(){
        String s = scanner.nextLine();
        if(s.equalsIgnoreCase("m")){
            legends.getManual().getAction();
        }
        return s;
    }

    public String next(){
        return scanner.next();
    }

    public int nextInt(){
        return scanner.nextInt();
    }
}
