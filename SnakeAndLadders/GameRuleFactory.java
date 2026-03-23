import java.util.HashMap;
import java.util.Map;

public class GameRuleFactory {

    private static final Map<String, GameRule> ruleMap = new HashMap<>();

    static {
        ruleMap.put("easy", new Easylevel());
        ruleMap.put("hard", new Hardlevel());
    }

    public static GameRule getRule(String difficulty) {
        GameRule rule = ruleMap.get(difficulty.toLowerCase());

        if (rule == null) {
            throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }

        return rule;
    }
}