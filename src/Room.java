import java.util.ArrayList;

public class Room {
    private Room n, s, e, w;
    private String name;
    private String description;
    private ArrayList<Item> items;

    public Room() {
    }

    public void init(String name, String description, Room n, Room s, Room e, Room w) {
        this.name = name;
        this.description = description;
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = w;
    }

    public Room getAdjacent(int direction) {
        return switch (direction) {
            case 0 -> this.n;
            case 1 -> this.e;
            case 2 -> this.s;
            case 3 -> this.w;
            default -> throw new IllegalStateException("Unexpected value: " + direction);
        };
    }

    public void describe() {
        System.out.println(this.description);
    }
}
