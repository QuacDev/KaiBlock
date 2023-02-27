package quac.funnystuff;

import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundGameEventPacket;
import net.minecraft.server.level.ServerPlayer;
import org.bukkit.craftbukkit.v1_19_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import quac.funnystuff.Entity.PlayerBase;

public final class Main extends JavaPlugin {
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        Registries.Init(this);


        for (Player p : plugin.getServer().getOnlinePlayers()) {
            CraftPlayer craftPlayer = (CraftPlayer) p;
            ServerPlayer entityPlayer = craftPlayer.getHandle();
            Connection playerConnection = entityPlayer.connection.getConnection();

            PlayerBase base = PlayerBase.getPlayerBase(p);

            //Funny arrow ding
            ClientboundGameEventPacket packet = new ClientboundGameEventPacket(ClientboundGameEventPacket.ARROW_HIT_PLAYER, 1f);
            playerConnection.send(packet);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Player p : plugin.getServer().getOnlinePlayers()) {
            PlayerBase base = PlayerBase.getPlayerBase(p);
            base.DestroyPlayerBase();
        }
    }
}
