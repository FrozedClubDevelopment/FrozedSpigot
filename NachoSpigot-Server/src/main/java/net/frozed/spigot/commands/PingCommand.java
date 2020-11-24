package net.frozed.spigot.commands;

import dev.cobblesword.nachospigot.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Created by Elb1to
 * Project: FrozedSpigot
 * Date: 11/24/2020 @ 3:00 PM
 */
public class PingCommand extends Command {

    public PingCommand() {
        super("ping");
        this.usageMessage = "/ping <player>";
        this.setAliases(Arrays.asList("latency", "playerconnection"));
    }

    @Override
    public boolean execute(CommandSender sender, String label, String[] args) {
        if (args.length < 1) {
            sender.sendMessage(CC.translate("&bYour ping: &f" + ((CraftPlayer) sender).getHandle().ping + "ms"));
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target != null) {
            sender.sendMessage(CC.translate("&b" + target.getName() + "'s ping: &f" + ((CraftPlayer) target).getHandle().ping + "ms"));
        } else {
            sender.sendMessage(CC.translate("&c" + args[0] + " is not online."));
        }

        return true;
    }
}
