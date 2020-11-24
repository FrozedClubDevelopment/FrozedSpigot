package org.bukkit.command.defaults.nacho;

import dev.cobblesword.nachospigot.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;

public class SetMaxSlotCommand extends Command {
    public SetMaxSlotCommand(String name) {
        super(name);
        this.description = "Set the max slots for the server";
        this.usageMessage = "/setMaxSlots [amount]";
        this.setAliases(Arrays.asList("setMaxSlot", "maxSlots"));
        setPermission("NachoSpigot.command.setMaxSlots");
    }

    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (!testPermission(sender)) return true;

        if (args.length == 0) {
            sender.sendMessage(CC.GRAY + "There are currently " + CC.WHITE + Bukkit.getMaxPlayers() + CC.GRAY + " slots!");
            sender.sendMessage(ChatColor.RED + "Please use '/setMaxSlots [amount]' to set the number of max slots for the server");
            return false;
        }

        int amount = 1;
        Player player = (Player) sender;

        try {
            amount = Integer.parseInt(args[1]);
        } catch (Exception ex) {
            player.sendMessage(ChatColor.RED + "Please enter a number instead of '" + args[1] + "'.");
            return false;
        }

        Bukkit.getServer().setMaxPlayers(amount);
        player.sendMessage(ChatColor.GREEN + "Max Player Slots are now set at " + amount + "!");

        return false;
    }
}