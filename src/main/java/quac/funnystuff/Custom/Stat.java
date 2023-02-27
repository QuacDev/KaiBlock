package quac.funnystuff.Custom;

import java.util.HashMap;

public class Stat {
    StatType type;
    double value;

    public Stat(StatType type, double value) {
        this.type=type;
        this.value=value;
    }

    public static HashMap<StatType, Double> basePlayerStats() {
        HashMap<StatType, Double> stats = new HashMap<>();
        stats.put(StatType.MAX_HEALTH, 100d);
        stats.put(StatType.DEFENSE, 0d);
        stats.put(StatType.STRENGTH, 0d);
        stats.put(StatType.INTELLIGENCE, 100d);
        stats.put(StatType.CRITICAL_DAMAGE, 50d);
        stats.put(StatType.CRITICAL_CHANCE, 30d);

        return stats;
    }
}
