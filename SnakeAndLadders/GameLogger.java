public class GameLogger {

    public void logMove(Player player, int oldPos, int diceRoll, int newPos) {
        System.out.println(player.getName() + " rolled " + diceRoll +
                " → moved from " + oldPos + " to " + newPos);
        System.out.println("----------------------");
    }

    public void logJump(Player player, int from, int to) {
        if (to > from) {
            System.out.println(player.getName() + " hit a Ladder! 🚀 (" + from + " → " + to + ")");
        } else {
            System.out.println(player.getName() + " bitten by Snake! 🐍 (" + from + " → " + to + ")");
        }
    }

    public void logOverflow(Player player, int position, int diceRoll) {
        System.out.println(player.getName() + " rolled " + diceRoll +
                " but cannot move from " + position);
        System.out.println("----------------------");
    }

    public void logExtraTurn(Player player) {
        System.out.println(player.getName() + " gets another turn!");
    }

    public void logWin(Player player) {
        System.out.println("🎉 Player " + player.getName() + " wins!");
    }
}