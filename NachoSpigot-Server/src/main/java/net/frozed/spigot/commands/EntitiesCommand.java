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
 * Date: 11/24/2020 @ 3:06 PM
 */
public class EntitiesCommand extends Command {

    public EntitiesCommand() {
        super("entities");
        this.usageMessage = "/entities";
        this.setAliases(Arrays.asList("getmobs", "allmobs", "mobslist"));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        Player player = (Player) sender;

        if (player.hasPermission("frozedspigot.command.entities")) {
            player.sendMessage(CC.translate("&aGetting all alive entities and classifying them..."));

            for (World world : Bukkit.getWorlds()) {
                for (Entity entity : world.getEntities()) {
                    // player.sendMessage(CC.translate("&8 ● &b(&f" + entity.getType().name() + "&b)"));
                    //player.sendMessage(CC.translate("&8 ● &b(&f" + entity.getType().name() + "&b) &8[&7x" + entity.getWorld().getEntities().size() + "&8]"));
                }
                return true;
            }
        } else {
            player.sendMessage(CC.translate("&cYou don't have permission to do this."));
            return true;
        }

        return true;
    }
}
