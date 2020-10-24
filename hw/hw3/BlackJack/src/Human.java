/**
 * Abstract class representing BlackJack players
 * including the dealer and players
 */
public abstract class Human {
    protected String name;

    public Human(){}

    public Human(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
