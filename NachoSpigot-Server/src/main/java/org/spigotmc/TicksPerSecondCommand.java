package org.spigotmc;

import dev.cobblesword.nachospigot.CC;
import net.jafama.FastMath;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DurationFormatUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.scheduler.BukkitScheduler;

import java.lang.management.ManagementFactory;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.IntStream;

public class TicksPerSecondCommand extends Command {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    public TicksPerSecondCommand(String name) {
        super(name);
        this.description = "Gets the current ticks per second for the server";
        this.usageMessage = "/tps";
        this.setAliases(Arrays.asList("tickspersecond", "ticksper", "ticks"));
        this.setPermission("bukkit.command.tps");
    }

    private static String format(double tps) {
        return ((tps > 18.0) ? ChatColor.GREEN : (tps > 16.0) ? ChatColor.YELLOW : ChatColor.RED).toString()
                + ((tps > 20.0) ? "*" : "") + FastMath.min(FastMath.round(tps * 100.0) / 100.0, 20.0);
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (!testPermission(sender)) return true;

        double[] tps = org.bukkit.Bukkit.spigot().getTPS();
        String[] tpsAvg = new String[tps.length];

        IntStream.range(0, tps.length).forEach(value -> tpsAvg[value] = format(tps[value]));

        int activeEntities = Bukkit.getWorlds().stream().mapToInt(world -> world.getLivingEntities().size()).sum();
        int totalEntities = Bukkit.getWorlds().stream().mapToInt(world -> world.getEntities().size()).sum();

        int loadedChunks = Bukkit.getWorlds().stream()
                .filter(Objects::nonNull)
                .filter(world -> world.getLoadedChunks() != null)
                .mapToInt(world -> world.getLoadedChunks().length)
                .sum();

        BukkitScheduler bukkitScheduler = Bukkit.getScheduler();

        sender.sendMessage(CC.CHAT_BAR);

        sender.sendMessage(CC.translate("&bAlive threads: &f" + ManagementFactory.getThreadMXBean().getThreadCount() + " &3&l｜ &bDaemon threads: &f" + ManagementFactory.getThreadMXBean().getDaemonThreadCount()));
        sender.sendMessage(CC.translate("&bActive workers: &f" + bukkitScheduler.getActiveWorkers().size() + " &3&l｜ &bPending tasks: &f" + bukkitScheduler.getPendingTasks().size()));
        sender.sendMessage(CC.translate("&bMemory usage: &f"
                + ((Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory()) / (1024 * 1024))
                + "/" + (Runtime.getRuntime().totalMemory() / (1024 * 1024)) + " MB (" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + " MB free)")
        );

        sender.sendMessage(CC.translate("&bTPS from last 1m, 5m, 15m: ") + StringUtils.join(tpsAvg, ", "));

        sender.sendMessage(CC.translate("&bLast tick: &f" + decimalFormat.format(MinecraftServer.getServer().lastTickTime)
                + " ms &3&l｜ &bServer Uptime: &f" +
                DurationFormatUtils.formatDurationWords(ManagementFactory.getRuntimeMXBean().getUptime(),
                        true, true))
        );

        sender.sendMessage(CC.translate("&bOS: &f" + ManagementFactory.getOperatingSystemMXBean().getName()
                + " &3&l｜ &bCPU Cores: &f" + ManagementFactory.getOperatingSystemMXBean().getAvailableProcessors()
                + " &3&l｜ &bCPU Load: &f" + ManagementFactory.getOperatingSystemMXBean().getSystemLoadAverage())
        );

        sender.sendMessage(CC.translate("&bOnline players: &f" + Bukkit.getServer().getOnlinePlayers().size() + "/" + Bukkit.getServer().getMaxPlayers()
                + " &3&l｜ &bActive entities: &f" + activeEntities + "/" + totalEntities
                + " &3&l｜ &bChunks: &f" + loadedChunks)
        );

        sender.sendMessage(CC.CHAT_BAR);

        return true;
    }
}
