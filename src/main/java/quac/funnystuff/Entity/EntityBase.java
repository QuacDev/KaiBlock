package quac.funnystuff.Entity;

import org.bukkit.entity.Entity;

import java.util.HashMap;

public class EntityBase {
    static HashMap<Entity, EntityBase> entityBaseHashMap = new HashMap<>();

    Entity entity;
    public NameTagArmorStand tagArmorStand;
    int entityID;

    public EntityBase(Entity entity) {
        this.entity = entity;
        this.tagArmorStand = new NameTagArmorStand(this.entity);
        this.entityID = entity.getEntityId();

        entityBaseHashMap.put(this.entity, this);
    }

    public static EntityBase getEntityBase(Entity entity) {
        if(entityBaseHashMap.containsKey(entity)) {
            return entityBaseHashMap.get(entity);
        }
        return new EntityBase(entity);
    }

    public void DeleteEntityBase() {
        tagArmorStand.DestroyStand();
        entityBaseHashMap.remove(this.entity, this);
    }
}
