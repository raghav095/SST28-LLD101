import java.util.*;

public class GameSetup {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter difficulty (easy/hard): ");
        String difficulty = sc.nextLine().toLowerCase();

        System.out.print("Enter board size (N for NxN): ");
        int size = sc.nextInt();

        System.out.print("Enter number of players: ");
        int numPlayers = sc.nextInt();

       
        GameRule rule = GameRuleFactory.getRule(difficulty);

        Board board = BoardGenerator.createBoard(size);
        Dice dice = new Dice(6);

        List<Player> players = new ArrayList<>();
        for (int i = 1; i <= numPlayers; i++) {
            players.add(new Player("Player " + i));
        }

        GameLogger logger = new GameLogger();

        Game game = new Game(board, dice, players, rule, logger);

        game.startGame();

        sc.close();
    }
}