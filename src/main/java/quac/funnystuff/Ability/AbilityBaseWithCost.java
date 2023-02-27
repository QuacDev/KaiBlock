package quac.funnystuff.Ability;

import org.bukkit.entity.Player;
import quac.funnystuff.Entity.PlayerBase;

public class AbilityBaseWithCost extends AbilityBase{
    public int manaCost;

    public AbilityBaseWithCost(AbilityUseButton button) {
        super(button);
    }

    @Override
    public boolean use(Player p) {
        PlayerBase base = PlayerBase.getPlayerBase(p);
        if(base.mana >= manaCost) {
            base.useAbility(this);
        }
        return base.mana >= manaCost;
    }
}
