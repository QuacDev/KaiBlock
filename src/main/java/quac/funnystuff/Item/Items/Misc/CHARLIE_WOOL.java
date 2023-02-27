package quac.funnystuff.Item.Items.Misc;

import org.bukkit.Material;
import quac.funnystuff.Item.ItemBase;
import quac.funnystuff.Item.Rarity;
import quac.funnystuff.Utils.Color;

public class CHARLIE_WOOL extends ItemBase {
    public CHARLIE_WOOL() {
        super();

        this.id = "CHARLIE_WOOL";
        this.mat = Material.WHITE_WOOL;
        this.displayName = Color.rainbowText("Charlie Wool", false);
        this.baseRarity = Rarity.LEGENDARY;
        this.autoColorDisplay = false;
    }
}
