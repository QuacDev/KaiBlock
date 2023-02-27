package quac.funnystuff.Entity;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import quac.funnystuff.Ability.AbilityBase;
import quac.funnystuff.Ability.AbilityBaseWithCost;
import quac.funnystuff.Custom.Stat;
import quac.funnystuff.Custom.StatType;
import quac.funnystuff.Main;
import quac.funnystuff.Utils.Message;

import java.util.HashMap;
import java.util.UUID;

public class PlayerBase {
    public static HashMap<UUID, PlayerBase> playerBaseHashMap = new HashMap<>();

    public int tickTask;
    public int middleTextTask;

    public HashMap<StatType, Double> stats = new HashMap<>();

    public double health;
    public double mana;

    public Player p;
    public UUID uuid;

    private String middleText = "     ";

    public PlayerBase(Player p) {
        this.p = p;
        this.uuid=p.getUniqueId();
        playerBaseHashMap.put(this.uuid, this);

        tickTask = p.getServer().getScheduler().runTaskTimer(Main.plugin, this::tick, 1, 1).getTaskId();

        this.stats = Stat.basePlayerStats();

        health = this.stats.get(StatType.MAX_HEALTH);
        mana = this.stats.get(StatType.INTELLIGENCE);
    }

    public void tick() {
        p.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(Message.fromString(
                "&c" + health + "/" + stats.get(StatType.MAX_HEALTH) + "❤" +
                        middleText  +
                "&b" + mana + "/" + stats.get(StatType.MAX_HEALTH) + "✎"
        )));
    }

    public void updateMiddleText(String s, int ticks) {
        p.getServer().getScheduler().cancelTask(middleTextTask);
        middleText = "     " + s + "     ";
        middleTextTask = p.getServer().getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
            @Override
            public void run() {
                middleText = "     ";
            }
        }, ticks);
    }

    public void DestroyPlayerBase() {
        p.getServer().getScheduler().cancelTask(middleTextTask);
        p.getServer().getScheduler().cancelTask(tickTask);
        playerBaseHashMap.remove(this.uuid);
    }

    public void useAbility(AbilityBaseWithCost base) {
        mana -= base.manaCost;
        updateMiddleText("&b-" + base.manaCost + " Mana (&6" + base.name + "&b)", 30);
    }

    public static PlayerBase getPlayerBase(Player p) {
        if(playerBaseHashMap.containsKey(p.getUniqueId())) {
            return playerBaseHashMap.get(p.getUniqueId());
        }
        return new PlayerBase(p);
    }
}
