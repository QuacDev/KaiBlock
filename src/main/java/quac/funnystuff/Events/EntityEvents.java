package quac.funnystuff.Events;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import quac.funnystuff.Entity.EntityBase;

public class EntityEvents implements Listener {
    @EventHandler
    public void onEntitySpawn(EntitySpawnEvent event) {
        Entity entity = event.getEntity();
        if(!entity.getType().equals(EntityType.ARMOR_STAND) && entity.getType().isAlive()) {
            EntityBase base = EntityBase.getEntityBase(entity);
            base.tagArmorStand.updateName();
        }
    }

    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        Entity entity = event.getEntity();
        if(!entity.getType().equals(EntityType.ARMOR_STAND) && entity.getType().isAlive()) {
            EntityBase base = EntityBase.getEntityBase(entity);
            base.DeleteEntityBase();
        }
    }
}
