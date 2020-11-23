package net.frozed.spigot.knockback.command;

import net.frozed.spigot.FrozedSpigot;
import net.frozed.spigot.knockback.KnockbackProfile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class KnockbackCommand extends Command {

    private final FrozedSpigot frozedSpigot;

    public KnockbackCommand(FrozedSpigot frozedSpigot) {
        super("knockback");
        this.frozedSpigot = frozedSpigot;
        setPermission("frozedspigot.knockback");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {

        if (args.length < 1) {
            sender.sendMessage("nigga");
            return true;
        }

        switch (args[0]) {
            case "list": {
                sender.sendMessage("List of available knockback profiles");
                FrozedSpigot.get().getKnockbackManager().getKnockbackProfileMap().values()
                        .forEach(profile -> {
                            sender.sendMessage(" - " + profile.getName());
                        });
                break;
            }
            case "setdefault": {
                KnockbackProfile knockbackProfile = frozedSpigot.getKnockbackManager().getByName(args[1]);

                if (knockbackProfile == null) {
                    sender.sendMessage(ChatColor.RED + "This knockback profile does not exist.");
                    return true;
                }

                FrozedSpigot.get().setActiveKnockbackProfile(knockbackProfile);
                break;
            }
            case "create": {

                if (frozedSpigot.getKnockbackManager().getByName(args[1]) != null) {
                    sender.sendMessage(ChatColor.RED + "This knockback profile already exists.");
                    return true;
                }

                frozedSpigot.getKnockbackManager().createProfile(args[1]);
                break;
            }

            case "delete": {

                if (frozedSpigot.getKnockbackManager().getByName(args[1]) == null) {
                    sender.sendMessage(ChatColor.RED + "This knockback profile does not exist.");
                    return true;
                }

                frozedSpigot.getKnockbackManager().deleteProfile(args[1]);
                break;
            }
            default: {
                sender.sendMessage("error");
                break;
            }
        }

        return true;
    }
}
