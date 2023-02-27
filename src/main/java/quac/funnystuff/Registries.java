package quac.funnystuff;

import org.bukkit.plugin.java.JavaPlugin;
import quac.funnystuff.Commands.CopyData;
import quac.funnystuff.Commands.GiveCustomItem;
import quac.funnystuff.Commands.MoveStandCommands;
import quac.funnystuff.Commands.UpgradeRarity;
import quac.funnystuff.Events.EntityEvents;
import quac.funnystuff.Events.PlayerEvents;
import quac.funnystuff.Item.ItemRegistry;

public class Registries {
    public static void Init(JavaPlugin pl) {
        ItemRegistry.register();

        pl.getServer().getPluginManager().registerEvents(new PlayerEvents(), pl);
        pl.getServer().getPluginManager().registerEvents(new EntityEvents(), pl);

        //pl.getCommand("movestand").setExecutor(new MoveStandCommands());
        //pl.getCommand("movestand").setTabCompleter(new MoveStandCommands());

        pl.getCommand("copydata").setExecutor(new CopyData());

        pl.getCommand("givecustomitem").setExecutor(new GiveCustomItem());
        pl.getCommand("givecustomitem").setTabCompleter(new GiveCustomItem());

        pl.getCommand("recomb").setExecutor(new UpgradeRarity());
    }
}
