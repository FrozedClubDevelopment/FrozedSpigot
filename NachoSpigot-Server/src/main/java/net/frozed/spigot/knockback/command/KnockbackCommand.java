package net.frozed.spigot.knockback.command;

import dev.cobblesword.nachospigot.CC;
import net.frozed.spigot.FrozedSpigot;
import net.frozed.spigot.knockback.KnockbackProfile;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.Arrays;

public class KnockbackCommand extends Command {

    FrozedSpigot frozedSpigot = FrozedSpigot.get();

    public KnockbackCommand() {
        super("knockback");
        this.setUsage("/knockback");
        this.setAliases(Arrays.asList("kb", "beefwellington", "cockandballs"));
        this.setPermission("frozedspigot.knockback");
    }

    @Override
    public boolean execute(CommandSender sender, String commandLabel, String[] args) {
        if (sender.hasPermission("frozedspigot.command.knockback")) {
            if (args.length < 1) {
                sender.sendMessage(CC.CHAT_BAR);
                sender.sendMessage(CC.translate("&b&lFrozedSpigot &7- &fv1.0 &8[&31.8.8&8]"));
                sender.sendMessage(CC.translate("&7&oKnockback Commands Help"));
                sender.sendMessage(CC.CHAT_BAR);
                sender.sendMessage(CC.translate("&b ▶ &f/knockback"));
                sender.sendMessage(CC.translate("  &3 ● &7list"));
                sender.sendMessage(CC.translate("  &3 ● &7create <profileName>"));
                sender.sendMessage(CC.translate("  &3 ● &7delete <profileName>"));
                sender.sendMessage(CC.translate(" "));
                sender.sendMessage(CC.translate("  &3 ▶ &7edit <profileName>"));
                sender.sendMessage(CC.translate("    &3 ▶ &7<option>"));
                sender.sendMessage(CC.translate("      &3 ▶ &7<value>"));
                sender.sendMessage(CC.translate(""));
                sender.sendMessage(CC.translate("  &3 ▶ &7apply <profileName> <all:playerName>"));
                sender.sendMessage(CC.CHAT_BAR);

                return true;
            }

            sender.sendMessage(CC.CHAT_BAR);
            switch (args[0].toLowerCase()) {
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
                case "list": {
                    sender.sendMessage(CC.translate("&b&lFrozedSpigot &8- &fKnockback Profiles"));
                    sender.sendMessage(" ");
                    FrozedSpigot.get().getKnockbackManager().getKnockbackProfileMap().values().forEach(profile -> sender.sendMessage(" - " + profile.getName()));
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
                default: {
                    sender.sendMessage("error");
                    break;
                }
            }
            sender.sendMessage(CC.CHAT_BAR);
        } else {
            sender.sendMessage(CC.translate("&cYou don't have permission to do this."));
            return true;
        }

        return true;
    }
}
