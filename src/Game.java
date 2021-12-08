import java.util.ArrayList;
import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class Game {
    public ArrayList<Room> map;
    public Player player;

    public Game() {
        // Create the room objects
        map = new ArrayList<>();
        Room Forrest = new Room();
        Room Cave = new Room();
        Room Dungeon = new Room();
        Room Mine = new Room();
        Room House = new Room();
        Room HiddenRoom = new Room();

        //               HOUSE             N
        //                 |            W -|- E
        //       CAVE - FORREST            S
        //         |
        //       DUNGEON - HIDDEN ROOM
        //         |
        //       MINE

        // Initialise the Rooms
        Forrest.init("Forest", "Luscious greenery fills your view", House, null, null, Cave);
        Cave.init("Cave", "A dark and gloomy passage...", null, Dungeon, Forrest, null);
        Dungeon.init("Dungeon", "A dank room smelling of rot and decay.", Cave, null, HiddenRoom, null);
        Mine.init("Abandoned Mine", "An old, forgotten coal mine", Dungeon, null, null, null);
        House.init("Your House", "A humble cottage", null, Forrest, null, null);
        HiddenRoom.init("Hidden Tomb", "A small concrete cell", null, null, null, Dungeon);

        HiddenRoom.addItem(new Item("Key", "A golden key with runic symbols inscribed"));
        House.addItem(new Item("iPhone", "Jamie's Iphone"));
        House.addItem(new Item("Bed", "Your comfy bed"));

        map.add(Forrest);
        map.add(Cave);
        map.add(Dungeon);
        map.add(Mine);
        map.add(House);
        map.add(HiddenRoom);

        // Set the cave to require a key
        Cave.locked = true;
    }

    public void intro() {
        // Teach the player how to navigate the game
        Scanner nameScanner = new Scanner(System.in);
        System.out.println("Welcome to my text adventure!" +
                "---------------------------------------------" +
                "Your goal is simple - got home and go to bed!" +
                "Valid actions: " +
                "   'look' - Describes the player's surroundings" +
                "   'inventory' - Displays the player's inventory" +
                "   'use {item}' - Use an item" +
                "   'move {n / e / s / w} - move in a direction" +
                "----------------------------------------------" +
                "\n" +
                "* You find yourself in..");

        // Ask the user for a name
        // TODO; use the player name in interactions
        System.out.println("What is your name, adventurer?");
        System.out.print("> ");
        player = new Player(nameScanner.nextLine(), 100, map.get(3)); // Start in the Mine
        player.look();
    }

    public void parse(String input) {
        // Decodes each input into a verb and noun to be processed
        String[] words = input.split(" ", 2);
        String verb = words[0];
        String noun = "";
        if (words.length > 1) noun = words[1];

        switch (verb.toLowerCase(Locale.ROOT)) {
            case "look", "l" -> player.look();
            case "take", "t", "get" -> player.take(noun);
            case "inventory", "i" -> player.showInventory();
            case "drop", "d", "place", "put" -> player.drop(noun);
            case "use" -> {
                player.use(noun);
                checkVictory(noun);
            }

            case "move", "m", "go" -> {
                switch (noun.toLowerCase(Locale.ROOT)) {
                    case "north", "n" -> player.move(0);
                    case "east", "e" -> player.move(1);
                    case "south", "s" -> player.move(2);
                    case "west", "w" -> player.move(3);
                    default -> System.out.printf("Invalid Direction '%s'\n", noun);
                }
            }
            default -> System.out.printf("Cannot understand '%s'\n", verb);
        }
    }

    private void checkVictory(String noun) {
        // Rewards the player after using the bed in their house for completing the game.
        if (!Objects.isNull(player.getLocation().findItem("bed"))) {
            if (noun.equalsIgnoreCase("bed")) {
                System.out.println("You take a well deserved nap! GG!");

                System.out.println(
                        " /$$      /$$           /$$ /$$       /$$$$$$$  /$$                                     /$$ /$$\n" +
                        "| $$  /$ | $$          | $$| $$      | $$__  $$| $$                                    | $$| $$\n" +
                        "| $$ /$$$| $$  /$$$$$$ | $$| $$      | $$  \\ $$| $$  /$$$$$$  /$$   /$$  /$$$$$$   /$$$$$$$| $$\n" +
                        "| $$/$$ $$ $$ /$$__  $$| $$| $$      | $$$$$$$/| $$ |____  $$| $$  | $$ /$$__  $$ /$$__  $$| $$\n" +
                        "| $$$$_  $$$$| $$$$$$$$| $$| $$      | $$____/ | $$  /$$$$$$$| $$  | $$| $$$$$$$$| $$  | $$|__/\n" +
                        "| $$$/ \\  $$$| $$_____/| $$| $$      | $$      | $$ /$$__  $$| $$  | $$| $$_____/| $$  | $$    \n" +
                        "| $$/   \\  $$|  $$$$$$$| $$| $$      | $$      | $$|  $$$$$$$|  $$$$$$$|  $$$$$$$|  $$$$$$$ /$$\n" +
                        "|__/     \\__/ \\_______/|__/|__/      |__/      |__/ \\_______/ \\____  $$ \\_______/ \\_______/|__/\n" +
                        "                                                              /$$  | $$                        \n" +
                        "                                                             |  $$$$$$/                        \n" +
                        "                                                              \\______/ "
                );
                System.exit(0);
            }
        }
    }
}
