package quac.funnystuff.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import quac.funnystuff.Entity.MoveArmorStand;
import quac.funnystuff.Utils.Coordinates;
import quac.funnystuff.Utils.Message;

import java.util.ArrayList;
import java.util.List;

public class MoveStandCommands implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player p = (Player) sender;
        
        if(args.length == 0) {
            return false;
        }
        
        if(args[0].equalsIgnoreCase("create")) {
            ArmorStand stand = (ArmorStand) p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
            MoveArmorStand ms = new MoveArmorStand(stand);
            ms.setPoint(p.getLocation(), 0);
            p.sendMessage(Message.fromString("&aSuccessfully created a stand with the id {&c" + ms.getIndex() + "&a}."));
        } else if(args[0].equalsIgnoreCase("move") || args[0].equalsIgnoreCase("setpoint")) {
            int index = Integer.parseInt(args[1]);
            MoveArmorStand ms = MoveArmorStand.getMoveStand(index);
            int pointIndex = Integer.parseInt(args[2]);
            if(args[0].equals("move")) {
                ms.moveStandToPoint(pointIndex);

            } else if(args[0].equalsIgnoreCase("setpoint")) {
                ms.setPoint(p.getLocation(), pointIndex);
                p.sendMessage(Message.fromString(
                        "&aSuccessfully set point {&c" + pointIndex + "&a} of stand {&c" + index + "&a} to &c" + Coordinates.getGoodText(p.getLocation())));
            }
        } else if(args[0].equalsIgnoreCase("getpoints")) {
            int index = Integer.parseInt(args[1]);
            MoveArmorStand ms = MoveArmorStand.getMoveStand(index);
            for (int i = 0; i < ms.getPoints().size(); i++) {
                p.sendMessage(Message.fromString("&aPoint {&c" + i + "&a}: &c" + Coordinates.getGoodText(ms.getPoint(i))));
            }
        }

        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1) {
            List<String> options = new ArrayList<>();
            options.add("create");
            options.add("move");
            options.add("setpoint");
            options.add("getpoints");

            return options;
        } else if(args.length == 2) {
            if(args[0].equalsIgnoreCase("move") || args[0].equalsIgnoreCase("setpoint")) {
                List<String> options = new ArrayList<>();
                for (MoveArmorStand moveArmorStand : MoveArmorStand.moveArmorStands) {
                    options.add(String.valueOf(moveArmorStand.getIndex()));
                }
                return options;
            }
        }/* else if(args.length == 3) {
            (args[0].equalsIgnoreCase("move")) {
                List<String> options = new ArrayList<>();
                for(int i = 0; i < MoveArmorStand.getMoveStand(Integer.parseInt(args[1])).getPoints().size(); i++) {
                    options.add(String.valueOf(i));
                }
                return options;
            }
        }*/
        return null;
    }
}
