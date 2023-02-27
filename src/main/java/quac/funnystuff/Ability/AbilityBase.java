package quac.funnystuff.Ability;

import org.bukkit.entity.Player;
import quac.funnystuff.Utils.Message;

import java.util.ArrayList;
import java.util.List;

public class AbilityBase {
    public String name;
    public AbilityUseButton useButton;
    public int cooldownTicks;
    public String description;

    public boolean use(Player p) {

        return false;
    }

    public AbilityBase(AbilityUseButton button) {
        this.name = "UndefinedAbilityName";
        this.useButton = button;
        this.description = "UndefinedAbilityDescription";
        this.cooldownTicks = 20;
    }

    public List<String> getAbilityLore() {
        List<String> lore = new ArrayList<>();

        lore.add(Message.fromString("&6Ability: " + this.name + " &e&l" + this.useButton.display));
        lore.add(Message.fromString("&7" + this.description));
        lore.add(Message.fromString("&8Cooldown: &a" + this.cooldownTicks/20 + "s"));

        return lore;
    }
}
