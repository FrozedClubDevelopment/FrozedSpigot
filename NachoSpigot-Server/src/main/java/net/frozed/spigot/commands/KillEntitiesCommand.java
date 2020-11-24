package net.frozed.spigot.commands;

import dev.cobblesword.nachospigot.CC;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Created by Elb1to
 * Project: FrozedSpigot
 * Date: 11/24/2020 @ 3:00 PM
 */
public class KillEntitiesCommand extends Command {

    public KillEntitiesCommand() {
        super("killentities");
        this.usageMessage = "/killentities";
        this.setAliases(Arrays.asList("removeentities", "removemobs", "killmobs"));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;

        /*
         * For some reason the executor of the command gets his client frozen
         * whenever he/she executes the /killentities command...
         */
        if (player.hasPermission("frozedspigot.command.killentities")) {
            player.sendMessage(CC.translate("&aRemoving all entities..."));

            int entitiesRemoved = 0;
            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    entity.remove();
                    entitiesRemoved++;
                }
            }
            player.sendMessage(CC.translate("&a" + entitiesRemoved + " entities were removed."));
        } else {
            player.sendMessage(CC.translate("&cYou don't have permission to do this."));
            return true;
        }

        return true;
    }
}
