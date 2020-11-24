package net.frozed.spigot.commands;

import dev.cobblesword.nachospigot.CC;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Created by Elb1to
 * Project: FrozedSpigot
 * Date: 11/24/2020 @ 3:00 PM
 */
public class KillEntitiesCommand extends Command {

    public KillEntitiesCommand() {
        super("killentities");
        setPermission("frozedspigot.killentities");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        Bukkit.getWorlds().forEach(entities ->
                entities.getEntities().stream().filter(entity ->
                        (!(entity instanceof Player))).forEach(entity -> {
                    entity.remove();
                    atomicInteger.incrementAndGet();
                }));

        sender.sendMessage(CC.WHITE + "You have successfully killed " + CC.AQUA + CC.BOLD
                + atomicInteger.get() + CC.WHITE + " entities.");
        return true;
    }
}
