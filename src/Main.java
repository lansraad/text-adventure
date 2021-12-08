import java.util.Scanner;

public class Main {
    static Game game;

    public static void main (String[] args) {
        game = new Game();
        game.intro();
        Scanner inputScanner = new Scanner(System.in);
        String input;

        do {
            System.out.print("> ");
            input = inputScanner.nextLine();
            game.parse(input);
        } while (!"q".equals(input));

        System.out.println("Game over.");
    }
}
