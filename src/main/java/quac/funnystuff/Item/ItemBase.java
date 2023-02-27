package quac.funnystuff.Item;

import net.minecraft.nbt.CompoundTag;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_19_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import quac.funnystuff.Ability.AbilityBase;
import quac.funnystuff.Custom.StatType;
import quac.funnystuff.Keys;

import quac.funnystuff.Utils.Message;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ItemBase {
    public String id;
    public String displayName;
    public Material mat;
    public Rarity baseRarity;
    public boolean autoColorDisplay = true;
    public boolean hasLeftClick,hasRightClick,hasShiftRightClick,hasShiftLeftClick;

    public HashMap<StatType, Double> baseStats = new HashMap<>();

    public ItemBase() {
        this.id = "UNDEFINED_ID";
        this.displayName = "Undefined DisplayName";
        this.mat = Material.STONE;
        this.baseRarity = Rarity.COMMON;

        this.hasLeftClick = false;
        this.hasRightClick = false;
        this.hasShiftRightClick = false;
        this.hasShiftLeftClick = false;
    }


    public ItemStack getFunnyItemStack() {
        ItemStack stack = new ItemStack(this.mat);

        net.minecraft.world.item.ItemStack nmsItem = CraftItemStack.asNMSCopy(stack);

        CompoundTag nbtTag = new CompoundTag();
        CompoundTag extraAttributes = new CompoundTag();

        extraAttributes.putString(Keys.ID_KEY.getKey(), this.id);
        extraAttributes.putUUID(Keys.UUID_KEY.getKey(), UUID.randomUUID());
        extraAttributes.putInt(Keys.RARITY_UPGRADE.getKey(), 0);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm");
        LocalDateTime now = LocalDateTime.now();
        extraAttributes.putString(Keys.TIMESTAMP_KEY.getKey(), dtf.format(now));

        nbtTag.put(Keys.EXTRA_ATTRIBUTES, extraAttributes);

        nmsItem.setTag(nbtTag);

        ItemStack newStack = CraftItemStack.asBukkitCopy(nmsItem);
        ItemMeta m = newStack.getItemMeta();

        assert m != null;
        m.setLore(getLore(extraAttributes));

        m.setDisplayName(getDisplayName(extraAttributes));

        m.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        m.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
        m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        m.addItemFlags(ItemFlag.HIDE_DESTROYS);
        m.addItemFlags(ItemFlag.HIDE_PLACED_ON);

        m.setUnbreakable(true);

        newStack.setItemMeta(m);
        return newStack;
    }

    public AbilityBase leftClickAbility() {return null;}
    public AbilityBase rightClickAbility() {return null;}
    public AbilityBase shiftLeftClickAbility() {return null;}
    public AbilityBase shiftRightClickAbility() {return null;}

    public List<String> getLore(CompoundTag extraAttributes) {

        List<String> lore = new ArrayList<>(getStatsLore(extraAttributes));
        lore.add("");
        lore.add(getRarityDisplay(extraAttributes));

        return lore;
    }

    public String getRarityDisplay(CompoundTag extraAttributes) {
        int rarityUpgrades = extraAttributes.getInt(Keys.RARITY_UPGRADE.getKey());

        Rarity rarity =  Rarity.getRarityFromIndex(this.baseRarity.index + extraAttributes.getInt(Keys.RARITY_UPGRADE.getKey()));
        if(rarityUpgrades == 0) {
            return Message.fromString(rarity.display);
        } else {
            return Message.fromString( rarity.color.code + "&l&kR&r " + rarity.display + " " + rarity.color.code + "&l&kR");
        }
    }

    public List<String> getStatsLore(CompoundTag extraAttributes) {
        List<String> lore = new ArrayList<>();
        HashMap<StatType, Double> stats = getStats(extraAttributes);

        DecimalFormat format = new DecimalFormat("0.#");

        if(stats.containsKey(StatType.BASE_MELEE_DAMAGE)) {
            lore.add(Message.fromString("&7Damage: &c+" + format.format(stats.get(StatType.BASE_MELEE_DAMAGE))));
        }
        if(stats.containsKey(StatType.STRENGTH)) {
            lore.add(Message.fromString("&7Strength: &c+" + format.format(stats.get(StatType.STRENGTH))));
        }
        if(stats.containsKey(StatType.CRITICAL_CHANCE)) {
            lore.add(Message.fromString("&7Crit Chance: &c+" + format.format(stats.get(StatType.CRITICAL_CHANCE))));
        }
        if(stats.containsKey(StatType.CRITICAL_DAMAGE)) {
            lore.add(Message.fromString("&7Crit Damage: &c+" + format.format(stats.get(StatType.CRITICAL_DAMAGE))));
        }
        if(stats.containsKey(StatType.INTELLIGENCE)) {
            lore.add(Message.fromString("&7Intelligence: &a+" + format.format(stats.get(StatType.INTELLIGENCE))));
        }
        if(stats.containsKey(StatType.MAX_HEALTH)) {
            lore.add(Message.fromString("&7Health: &a+" + format.format(stats.get(StatType.MAX_HEALTH))));
        }
        if(stats.containsKey(StatType.DEFENSE)) {
            lore.add(Message.fromString("&7Defense: &a+" + format.format(stats.get(StatType.DEFENSE))));
        }
        return lore;
    }

    public List<String> getAbilityLore(CompoundTag extraAttributes) {
        List<String> lore = new ArrayList<>();
        if(this.hasRightClick) {
            lore.addAll(this.rightClickAbility().getAbilityLore());
            lore.add("");
        }
        if(this.hasShiftRightClick) {
            lore.addAll(this.shiftRightClickAbility().getAbilityLore());
            lore.add("");
        }
        if(this.hasLeftClick) {
            lore.addAll(this.leftClickAbility().getAbilityLore());
            lore.add("");
        }
        if(this.hasShiftLeftClick) {
            lore.addAll(this.shiftLeftClickAbility().getAbilityLore());
            lore.add("");
        }
        return lore;
    }

    public static String getDisplayName(CompoundTag extraAttributes) {
        ItemBase item = ItemRegistry.getFunnyItem(extraAttributes.getString(Keys.ID_KEY.getKey()));

        if(item.autoColorDisplay) {
            return Message.fromString(Rarity.getRarityFromIndex(
                    item.baseRarity.index + extraAttributes.getInt(Keys.RARITY_UPGRADE.getKey())).color.code + item.displayName);
        } else {
            return Message.fromString(item.displayName);
        }
    }

    // Modify later With enchants n stuff
    public HashMap<StatType, Double> getStats(CompoundTag extraAttributes) {
        return this.baseStats;
    }
}
