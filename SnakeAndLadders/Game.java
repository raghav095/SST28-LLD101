import java.util.List;

public class Game {
    private Board board;
    private Dice dice;
    private List<Player> players;
    private int currentPlayerIndex;
    private boolean isGameOver;
    private GameRule rule;
    private GameLogger logger;

    public Game(Board board, Dice dice, List<Player> players, GameRule rule, GameLogger logger) {
        this.board = board;
        this.dice = dice;
        this.players = players;
        this.rule = rule;
        this.logger = logger;
        this.currentPlayerIndex = 0;
        this.isGameOver = false;
    }

    public void startGame() {
        int playersWon = 0;
        int totalPlayers = players.size();
        int gameEndThreshold = totalPlayers > 1 ? totalPlayers - 1 : 1;

        while (!isGameOver && playersWon < gameEndThreshold) {
            Player currentPlayer = players.get(currentPlayerIndex);

            if (currentPlayer.getPosition() == board.getTotalCells()) {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }

            int oldPosition = currentPlayer.getPosition();
            int diceRoll = dice.roll();

            int newPosition = oldPosition + diceRoll;


            if (newPosition > board.getTotalCells()) {
                logger.logOverflow(currentPlayer, oldPosition, diceRoll);

                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
                continue;
            }

            int finalPosition = board.getFinalPosition(newPosition);


            if (finalPosition != newPosition) {
                logger.logJump(currentPlayer, newPosition, finalPosition);
            }

            currentPlayer.setPosition(finalPosition);

            logger.logMove(currentPlayer, oldPosition, diceRoll, finalPosition);

      
            if (finalPosition == board.getTotalCells()) {
                logger.logWin(currentPlayer);
                playersWon++;
                if (playersWon >= gameEndThreshold) {
                    isGameOver = true;
                    break;
                }
            }

          
            boolean continueTurn = rule.shouldContinueTurn(currentPlayer, diceRoll);

            if (continueTurn && finalPosition != board.getTotalCells()) {
                logger.logExtraTurn(currentPlayer);
            } else {
                currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
            }
        }
    }
}