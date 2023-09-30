package de.tamion.commands;

import de.tamion.RangeChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class RequireLineOfSight implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("RangeChat.lineofsight")) {
            sender.sendMessage("You aren't allowed to execute this command");
            return false;
        }
        FileConfiguration config = RangeChat.getPlugin().getConfig();
        config.set("chat.requirelineofsight", !config.getBoolean("chat.requirelineofsight"));
        sender.sendMessage("Require Line of sight is now set to " + config.getBoolean("chat.requirelineofsight"));
        RangeChat.getPlugin().saveConfig();
        return false;
    }
}
