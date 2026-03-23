import java.util.HashMap;
import java.util.Map;

public class Hardlevel implements GameRule {

    private Map<Player, Integer> countMap = new HashMap<>();

    @Override
    public boolean shouldContinueTurn(Player player, int diceRoll) {

        int count = countMap.getOrDefault(player, 0);

        if (diceRoll == 6) {
            count++;

            if (count == 3) {
                countMap.put(player, 0); 
                return false; 
            }

            countMap.put(player, count);
            return true; 
        }

        countMap.put(player, 0);
        return false;
    }
}