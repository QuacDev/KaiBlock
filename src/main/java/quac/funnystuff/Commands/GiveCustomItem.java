package quac.funnystuff.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;
import quac.funnystuff.Item.ItemRegistry;

import java.util.ArrayList;
import java.util.List;

public class GiveCustomItem implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        PlayerInventory inv = p.getInventory();

        inv.addItem(ItemRegistry.getFunnyItem(args[0].toUpperCase()).getFunnyItemStack());

        return true;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1) {
            List<String> options = new ArrayList<>();
            ItemRegistry.registeredItems.forEach((id, funnyItem) -> {
                options.add(id);
            });

            return options;
        } else {
            return null;
        }
    }
}
