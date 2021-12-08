import java.util.ArrayList;

public class Room {
    private Room n, s, e, w;
    public String name;
    private String description;
    public ArrayList<Item> items;
    public boolean locked;

    public Room() {
    }

    public void init(String name, String description, Room n, Room s, Room e, Room w) {
        this.items = new ArrayList<>();
        this.name = name;
        this.description = description;
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = w;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void removeItem(Item item) {
        items.remove(item);
    }

    public Item findItem(String itemName) {
        for (Item item: items) {
            if (item.name.equalsIgnoreCase(itemName)) {
                return item;
            }
        }
        return null;
    }

    public Room[] getExits() {
        return new Room[]{this.n, this.e, this.s, this.w};
    }

    public Room getExit(int direction) {
        return switch (direction) {
            case 0 -> this.n;
            case 1 -> this.e;
            case 2 -> this.s;
            case 3 -> this.w;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    public void describe() {
        System.out.printf("\n%s: %s\n", this.name, this.description);
    }

    public void describeItems() {
        if (items.size() > 0) {
            System.out.println("  You see the following:");
            for (Item item : items) {
                System.out.printf("    * %s: %s\n", item.name, item.description);
            }
        }
    }
}
