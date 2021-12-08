import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Game {
    private ArrayList<Room> map;
    public Player player;

    public Game() {
        map = new ArrayList<Room>();
        Room testRoom1 = new Room();
        Room testRoom2 = new Room();

        testRoom1.init("Room1", "A generic room", null, testRoom2, null, null);
        testRoom2.init("Room2", "Another generic room", testRoom1, null, null, null);

        map.add(testRoom1);
        map.add(testRoom2);
    }

    public void intro() {
        Scanner nameScanner = new Scanner(System.in);
        System.out.println("Welcome to [untitled] text adventure!");
        System.out.println("What is your name, adventurer?");
        System.out.print("> ");
        player = new Player(nameScanner.nextLine(), 100, map.get(0));
        player.look();
    }

    public void parse(String input) {
        String[] words = input.split(" ", 2);
        String verb = words[0];
        String noun = "";
        if (words.length > 1) noun = words[1];

        switch (verb.toLowerCase(Locale.ROOT)) {
            case "look": case "l":
                System.out.println("You see nothing around you.");
                break;

            case "move": case "m":
                switch (noun.toLowerCase(Locale.ROOT)) {
                    case "north": case "n":
                        System.out.println("NORTH");
                        player.move(0);
                        break;
                    case "east": case "e":
                        System.out.println("EAST");
                        player.move(1);
                        break;
                    case "south": case "s":
                        System.out.println("SOUTH");
                        player.move(2);
                        break;
                    case "west": case "w":
                        System.out.println("WEST");
                        player.move(3);
                        break;
                    default:
                        System.out.printf("Invalid Direction '%s'\n", noun);
                }
                break;
            default:
                System.out.printf("Cannot understand '%s'\n", verb);
        }
    }
}
