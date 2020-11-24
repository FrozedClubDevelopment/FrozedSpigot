package org.spigotmc;

import net.minecraft.server.EntityPlayer;
import net.minecraft.server.MinecraftServer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.io.File;

public class RestartCommand extends Command {

    public RestartCommand(String name) {
        super(name);
        this.description = "Restarts the server";
        this.usageMessage = "/restart";
        this.setPermission("bukkit.command.restart");
    }

    public static void restart() {
        restart(new File(SpigotConfig.restartScript));
    }

    public static void restart(final File script) {
        AsyncCatcher.enabled = false; // Disable async catcher incase it interferes with us
        try {
            if (script.isFile()) {
                System.out.println("Attempting to restart with " + SpigotConfig.restartScript);

                // Disable Watchdog
                WatchdogThread.doStop();

                // Kick all players
                for (EntityPlayer p : MinecraftServer.getServer().getPlayerList().players) {
                    p.playerConnection.disconnect(SpigotConfig.restartMessage);
                }
                // Give the socket a chance to send the packets
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
                // Close the socket so we can rebind with the new process
                MinecraftServer.getServer().getServerConnection().b();

                // Give time for it to kick in
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }

                // Actually shutdown
                try {
                    MinecraftServer.getServer().stop();
                } catch (Throwable t) {
                }

                // This will be done AFTER the server has completely halted
                Thread shutdownHook = new Thread(() -> {
                    try {
                        String os = System.getProperty("os.name").toLowerCase();
                        if (os.contains("win")) {
                            Runtime.getRuntime().exec("cmd /c start " + script.getPath());
                        } else {
                            Runtime.getRuntime().exec(new String[]{
                                    "sh", script.getPath()
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });

                shutdownHook.setDaemon(true);
                Runtime.getRuntime().addShutdownHook(shutdownHook);
            } else {
                System.out.println("Startup script '" + SpigotConfig.restartScript + "' does not exist! Stopping server.");
            }
            System.exit(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean execute(CommandSender sender, String currentAlias, String[] args) {
        if (testPermission(sender)) {
            MinecraftServer.getServer().processQueue.add(RestartCommand::restart);
        }
        return true;
    }
}
