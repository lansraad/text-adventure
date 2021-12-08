import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private String name;
    private int health;
    private ArrayList<Item> inventory;
    private Room location;

    public Player(String name, int health, Room location) {
        this.name = name;
        this.health = health;
        this.location = location;
    }

    public void move(int direction) {
        Room targetRoom = this.location.getAdjacent(direction);
        if (!Objects.isNull(targetRoom)){
            this.location = targetRoom;
        }
        look();
    }

    public void look() {
        this.location.describe();
    }
}
