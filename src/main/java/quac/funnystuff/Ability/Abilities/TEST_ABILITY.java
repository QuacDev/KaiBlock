package quac.funnystuff.Ability.Abilities;

import org.bukkit.Sound;
import org.bukkit.entity.Player;
import quac.funnystuff.Ability.AbilityBase;
import quac.funnystuff.Ability.AbilityBaseWithCost;
import quac.funnystuff.Ability.AbilityUseButton;
import quac.funnystuff.Utils.Message;

public class TEST_ABILITY extends AbilityBaseWithCost {
    public TEST_ABILITY(AbilityUseButton button) {
        super(button);
        this.name = "Parley";
        this.cooldownTicks = 100;
        this.description = "Plays a Villager Sound";
        this.manaCost = 5;
    }

    @Override
    public boolean use(Player p) {
        if(!super.use(p)) {
            return false;
        }

        p.playSound(p.getLocation(), Sound.ENTITY_VILLAGER_NO, 2f, 1f);

        return true;
    }
}
