import java.util.ArrayList;
import java.util.Objects;

public class Player {
    private String name;
    private int health;
    private ArrayList<Item> inventory;
    private Room location;

    public Player(String name, int health, Room location) {
        this.inventory = new ArrayList<>();
        this.name = name;
        this.health = health;
        this.location = location;
    }

    public void take(String itemName) {
        Item item = this.location.findItem(itemName);
        if(!Objects.isNull(item)) {
            this.location.removeItem(item);
            this.inventory.add(item);
            System.out.println(item.name + " added to inventory!");
        } else {
            System.out.println("Cannot find item '"+itemName+"'");
        }
    }

    public void drop(String itemName) {
        for (Item item: inventory) {
            if (item.name.equalsIgnoreCase(itemName)) {
                inventory.remove(item);
                this.location.addItem(item);
                System.out.println(item.name + " removed from inventory!");
                break;
            } else {
                System.out.println("Cannot find item '" + itemName + "'");
            }
        }
    }

    public void use(String itemName) {
        for (Item item: inventory) {
            if (item.name.equalsIgnoreCase(itemName)) {
                item.interact(item, this.location);
            }
        }
    }

    public void showInventory() {
        if (inventory.size() > 0) {
            System.out.println("You look into your pockets and find:");
            for (Item item : inventory) {
                System.out.printf("  * %s: %s\n", item.name, item.description);
            }
        } else {
            System.out.println("You look into your pockets and find nothing.");
        }
    }

    public Room getLocation() {
        return location;
    }

    public void move(int direction) {
        Room targetRoom = this.location.getExit(direction);
        if (!Objects.isNull(targetRoom)){
            if (targetRoom.locked) {
                System.out.println("The exit is locked, perhaps you need a key..");
            } else {
                this.location = targetRoom;
                this.location.describe();
            }
        } else {
            System.out.println("No exit!");
        }
    }

    public void look() {
        this.location.describe();
        this.location.describeItems();
    }
}
