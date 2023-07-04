package de.tamion.commands;

import de.tamion.RangeChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.jetbrains.annotations.NotNull;

public class IgnoreHeight implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("RangeChat.ignoreheight")) {
            sender.sendMessage("You aren't allowed to execute this command");
            return false;
        }
        FileConfiguration config = RangeChat.getPlugin().getConfig();
        config.set("chat.ignoreheight", !config.getBoolean("chat.ignoreheight"));
        sender.sendMessage("Ignore Height is now set to " + config.getBoolean("chat.ignoreheight"));
        RangeChat.getPlugin().saveConfig();
        return false;
    }
}
