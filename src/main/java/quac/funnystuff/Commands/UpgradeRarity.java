package quac.funnystuff.Commands;

import net.minecraft.nbt.CompoundTag;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;
import quac.funnystuff.Item.ItemBase;
import quac.funnystuff.Item.ItemRegistry;
import quac.funnystuff.Keys;
import quac.funnystuff.Utils.Message;


public class UpgradeRarity implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        PlayerInventory inv = p.getInventory();
        ItemStack stack = inv.getItemInMainHand();
        if(!stack.getType().isItem()) {
            p.sendMessage(Message.fromString("&cPlease hold an Item!"));
            return true;
        }
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(stack);

        if(!nmsItem.hasTag()) {
            p.sendMessage(Message.fromString("&cCannot recombobulate this Item!"));
            return true;
        }

        CompoundTag tag = nmsItem.getTag();
        assert tag != null;
        CompoundTag attributes = tag.getCompound(Keys.EXTRA_ATTRIBUTES);
        String id = attributes.getString(Keys.ID_KEY.getKey());

        p.sendMessage("Item has " + attributes.getInt(Keys.RARITY_UPGRADE.getKey()) + " upgrades");
        attributes.putInt(Keys.RARITY_UPGRADE.getKey(), attributes.getInt(Keys.RARITY_UPGRADE.getKey())+1);

        tag.put(Keys.EXTRA_ATTRIBUTES, attributes);

        nmsItem.setTag(tag);

        ItemStack newStack = CraftItemStack.asBukkitCopy(nmsItem);
        ItemMeta m = newStack.getItemMeta();
        assert m != null;
        m.setLore(ItemRegistry.getFunnyItem(id).getLore(attributes));
        m.setDisplayName(ItemBase.getDisplayName(attributes));
        newStack.setItemMeta(m);

        inv.setItemInMainHand(newStack);

        //p.sendMessage(Message.fromString("&aSuccessfully upgraded the item 1x {&c" + + "&a}."));

        return false;
    }
}
