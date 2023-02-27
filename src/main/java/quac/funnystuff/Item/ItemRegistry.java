package quac.funnystuff.Item;

import quac.funnystuff.Item.Items.Misc.CHARLIE_WOOL;
import quac.funnystuff.Item.Items.Misc.FUNNY_STONE;
import quac.funnystuff.Item.Items.Weapons.TEST_ITEM;

import java.util.HashMap;

public class ItemRegistry {
    public static HashMap<String, ItemBase> registeredItems = new HashMap<>();

    static void registerFunnyItem(ItemBase a) {
        registeredItems.put(a.id, a);
    }

    public static ItemBase getFunnyItem(String id) {
        return registeredItems.get(id);
    }

    public static void register() {
        System.out.println("Registering all custom items...");

        registerFunnyItem(new FUNNY_STONE());
        registerFunnyItem(new CHARLIE_WOOL());

        registerFunnyItem(new TEST_ITEM());

        System.out.println("Registered (" + registeredItems.size() + ") custom items.");
    }
}
