package quac.funnystuff.Utils;

import org.bukkit.Location;

public class Coordinates {
    public static String getGoodText(Location loc) {
        int x = loc.getBlockX();
        int y = loc.getBlockY();
        int z = loc.getBlockZ();
        return x + "," + y + "," + z;
    }
}
