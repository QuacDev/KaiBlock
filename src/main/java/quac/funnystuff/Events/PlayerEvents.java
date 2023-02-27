package quac.funnystuff.Events;

import net.minecraft.nbt.CompoundTag;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import quac.funnystuff.Item.ItemBase;
import quac.funnystuff.Item.ItemRegistry;
import quac.funnystuff.Keys;
import quac.funnystuff.Utils.Message;


public class PlayerEvents implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendMessage(Message.fromString("&aHi"));
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        ItemStack i = event.getItem();
        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(i);
        if(!nmsItem.hasTag()) return;
        CompoundTag tag = nmsItem.getTag();
        assert tag != null;
        if(!tag.contains(Keys.EXTRA_ATTRIBUTES)) return;
        CompoundTag extraAttributes = tag.getCompound(Keys.EXTRA_ATTRIBUTES);
        String id = extraAttributes.getString(Keys.ID_KEY.getKey());
        ItemBase itemBase = ItemRegistry.getFunnyItem(id);

        switch (event.getAction()) {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                if(!p.isSneaking() && itemBase.hasLeftClick) {
                    itemBase.leftClickAbility().use(p);
                }
                if(p.isSneaking() && itemBase.hasShiftLeftClick) {
                    itemBase.shiftLeftClickAbility().use(p);
                }
                break;
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                if(!p.isSneaking() && itemBase.hasRightClick) {
                    itemBase.rightClickAbility().use(p);
                }
                if(p.isSneaking() && itemBase.hasShiftRightClick) {
                    itemBase.shiftRightClickAbility().use(p);
                }
                break;
        }
    }

    /*@EventHandler
    public void onEntityDestroy(EntityDeathEvent event) {
        if(event.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            ArmorStand stand = (ArmorStand) event.getEntity();
            PersistentDataContainer pDC = stand.getPersistentDataContainer();

            if(pDC.has(Keys.ID_KEY, PersistentDataType.TAG_CONTAINER)) {
                MoveArmorStand ms = MoveArmorStand.getMoveStand(stand);
                assert ms != null;
                ms.deleteStand();
            }
        }
    }*/
}
