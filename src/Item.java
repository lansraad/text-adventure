import java.util.Objects;

public class Item {
    public String name;
    public String description;

    public Item (String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void interact (Item item, Room currentRoom) {
        if (item.name.equalsIgnoreCase("key")) {
            for (Room room: currentRoom.getExits()) {
                if (!Objects.isNull(room)) {
                    if (room.locked) {
                        room.locked = false;
                        System.out.println("KEY> You have unlocked the door!");
                        break;
                    } else {
                        System.out.println("KEY> The room you are in is not locked!");
                    }
                }
            }
        }
    }
}
