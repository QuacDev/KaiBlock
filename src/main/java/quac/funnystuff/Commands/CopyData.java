package quac.funnystuff.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class CopyData implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        ItemStack s = p.getInventory().getItemInMainHand();
        if(s.getType().isItem()) {
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            if(s.hasItemMeta()) {
                clipboard.setContents(new StringSelection(s.getItemMeta().toString()), new StringSelection(s.getItemMeta().toString()));
            }
        }
        return false;
    }
}
