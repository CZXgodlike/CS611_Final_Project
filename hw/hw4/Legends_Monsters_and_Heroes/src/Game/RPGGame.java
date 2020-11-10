package Game;

import Player.Player;

/**
 * Abstract class representing RPG games
 */

public abstract class RPGGame extends Game{
    protected Player player;

    public RPGGame(){
    }

    public Player getPlayer() {
        return player;
    }
}
