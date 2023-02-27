package quac.funnystuff.Item.Items.Weapons;

import org.bukkit.Material;
import quac.funnystuff.Ability.Abilities.TEST_ABILITY;
import quac.funnystuff.Ability.AbilityBase;
import quac.funnystuff.Ability.AbilityUseButton;
import quac.funnystuff.Custom.StatType;
import quac.funnystuff.Item.ItemBase;
import quac.funnystuff.Item.Rarity;

public class TEST_ITEM extends ItemBase {
    public TEST_ITEM() {
        super();

        this.baseRarity = Rarity.COMMON;
        this.mat = Material.WOODEN_SWORD;
        this.displayName = "Test Item";
        this.autoColorDisplay = true;
        this.id = "TEST_ITEM";

        this.hasRightClick = true;
        this.hasShiftRightClick = true;

        this.baseStats.put(StatType.BASE_MELEE_DAMAGE, 1d);
    }

    @Override
    public AbilityBase rightClickAbility() {
        return new TEST_ABILITY(AbilityUseButton.RIGHT_CLICK);
    }

    @Override
    public AbilityBase shiftRightClickAbility() {
        return new TEST_ABILITY(AbilityUseButton.SHIFT_RIGHT_CLICK);
    }
}
