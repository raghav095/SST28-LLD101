public class Easylevel implements GameRule {
    @Override
    public boolean shouldContinueTurn(Player player, int diceRoll) {
        return diceRoll == 6;
    }
    

}
