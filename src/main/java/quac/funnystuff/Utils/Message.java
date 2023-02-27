package quac.funnystuff.Utils;

import org.bukkit.ChatColor;

public class Message {
    public static String fromString(String a) {
        return ChatColor.translateAlternateColorCodes('&',a);
    }
}
