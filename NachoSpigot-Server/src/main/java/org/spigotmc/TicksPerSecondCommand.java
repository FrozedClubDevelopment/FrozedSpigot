package org.spigotmc;

import net.jafama.FastMath;
import net.minecraft.server.MinecraftServer;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.text.DecimalFormat;
import java.util.stream.IntStream;

public class TicksPerSecondCommand extends Command {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public TicksPerSecondCommand(String name) {
        super(name);
        this.description = "Gets the current ticks per second for the server";
        this.usageMessage = "/tps";
        this.setPermission("bukkit.command.tps");
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender))
            return true;

        double[] tps = org.bukkit.Bukkit.spigot().getTPS();
        String[] tpsAvg = new String[tps.length];

        IntStream.range(0, tps.length)
                .forEach(value -> tpsAvg[value] = format(tps[value]));

        int entities = Bukkit.getWorlds().stream().mapToInt(world -> world.getEntities().size()).sum();

        sender.sendMessage(ChatColor.GOLD + "TPS from last 1m, 5m, 15m: "
                + org.apache.commons.lang.StringUtils.join(tpsAvg, ", "));
        sender.sendMessage(ChatColor.GOLD + "Full Tick: " + ChatColor.GREEN
                + decimalFormat.format(MinecraftServer.getServer().lastTickTime));
        sender.sendMessage(ChatColor.GOLD + "Entities: " + ChatColor.GREEN + entities);
        sender.sendMessage(ChatColor.GOLD + "Current Memory Usage: " + ChatColor.GREEN
                + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024))
                + "/" + (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " mb (Max: "
                + (Runtime.getRuntime().maxMemory() / (1024 * 1024)) + " mb)");
        return true;
    }

    private static String format(double tps) {
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
                + ((tps > 20.0) ? "*" : "") + FastMath.min(FastMath.round(tps * 100.0) / 100.0, 20.0);
    }
}
