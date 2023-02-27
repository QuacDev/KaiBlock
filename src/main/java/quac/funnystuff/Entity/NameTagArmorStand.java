package quac.funnystuff.Entity;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import quac.funnystuff.Utils.Message;

import java.util.HashMap;
import java.util.Objects;

public class NameTagArmorStand {
    public ArmorStand stand;
    public Entity entityParent;

    public NameTagArmorStand(Entity parent) {
        this.stand = (ArmorStand) parent.getWorld().spawnEntity(parent.getLocation(), EntityType.ARMOR_STAND);
        this.entityParent = parent;

        parent.addPassenger(stand);
        stand.setInvisible(true);
        stand.setMarker(true);
        //stand.setSmall(true);
        updateName();
    }

    public void updateName() {
        LivingEntity livingEntity = (LivingEntity) entityParent;
        double maxHealth = Objects.requireNonNull(livingEntity.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        stand.setCustomNameVisible(true);
        stand.setCustomName(Message.fromString(
                "&c" + entityParent.getName() +
                        " &a" + livingEntity.getHealth() + "&f/&a" + maxHealth + "&c❤"
        ));
    }

    public void DestroyStand() {
        stand.remove();
    }
}
