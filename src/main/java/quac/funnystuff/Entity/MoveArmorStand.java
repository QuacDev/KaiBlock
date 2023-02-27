package quac.funnystuff.Entity;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.ArmorStand;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import quac.funnystuff.Main;
import quac.funnystuff.Keys;
import quac.funnystuff.Utils.Coordinates;
import quac.funnystuff.Utils.Message;

import java.util.ArrayList;
import java.util.List;

public class MoveArmorStand {
    public static ArrayList<MoveArmorStand> moveArmorStands = new ArrayList<>();
    ArmorStand s;
    PersistentDataContainer dataContainer;
    static ArrayList<NamespacedKey> keys = new ArrayList<>();

    public MoveArmorStand(ArmorStand stand) {
        s = stand;
        moveArmorStands.add(this);
        dataContainer = s.getPersistentDataContainer();
        s.setCustomNameVisible(true);

        dataContainer.set(Keys.ID_KEY, PersistentDataType.STRING, "funnystuff:MOVESTAND");

        UpdateStandName();
    }

    public void UpdateStandName() {
        s.setCustomName(Message.fromString("&aMoveAbleArmorStand {&c" + moveArmorStands.indexOf(this) + "&a}"));
    }

    public ArmorStand getStand() {
        return s;
    }

    public int getIndex() {
        return moveArmorStands.indexOf(this);
    }

    public static MoveArmorStand getMoveStand(int index) {
        return moveArmorStands.get(index);
    }
    public static MoveArmorStand getMoveStand(ArmorStand stand) {
        for (MoveArmorStand moveArmorStand : moveArmorStands) {
            if(moveArmorStand.s == stand) {
                return moveArmorStand;
            }
        }
        return null;
    }

    public void setPoint(Location l, int index) {
        keys.add(new NamespacedKey(Main.plugin, "DataPoint" + getIndex() + "_" + index));
        Coordinates.getGoodText(l);
        dataContainer.set(
                keys.get(index),
                PersistentDataType.STRING,
                Coordinates.getGoodText(l)
                );
    }

    public Location getPoint(int index) {
        String point = dataContainer.get(keys.get(index), PersistentDataType.STRING);
        assert point != null;
        String[] coords = point.split(",");
        int x = Integer.parseInt(coords[0]);
        int y = Integer.parseInt(coords[1]);
        int z = Integer.parseInt(coords[2]);
        return new Location(s.getWorld(), x, y, z);
    }

    public List<MetadataValue> getPoints() {
        return null;
    }

    public void moveStandToPoint(int pointIndex) {
        Location point = getPoint(pointIndex);
        System.out.println(point.toString());
        s.teleport(point);
    }

    public void deleteStand() {
        moveArmorStands.remove(this);
        UpdateAllStands();
    }

    public static void UpdateAllStands() {
        for (MoveArmorStand moveArmorStand : moveArmorStands) {
            moveArmorStand.UpdateStandName();
        }
    }
}
