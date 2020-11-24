package net.frozed.spigot.commands;

import dev.cobblesword.nachospigot.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Elb1to
 * Project: FrozedSpigot
 * Date: 11/24/2020 @ 3:00 PM
 */
public class KillEntitiesCommand extends Command {

    public KillEntitiesCommand() {
        super("killentities");
        this.usageMessage = "/killentities";
        this.setPermission("frozedspigot.command.killentities");
        this.setAliases(Arrays.asList("removeentities", "removemobs", "killmobs"));
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger();

        Bukkit.getWorlds().forEach(entities ->
                entities.getEntities().stream().filter(entity -> (!(entity instanceof Player))).forEach(entity -> {
                    entity.remove();
                    atomicInteger.incrementAndGet();
                })
        );

        sender.sendMessage(CC.translate("&bYou have successfully killed &f&l" + atomicInteger.get() + "&b entities."));
        return true;
    }
}
