package de.tamion.commands;

import de.tamion.RangeChat;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class SetChatRange implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("RangeChat.setchatrange")) {
            sender.sendMessage("You aren't allowed to execute this command");
            return false;
        }
        if(args.length != 1) {
            sender.sendMessage("Please use: /setChatRange [Range in blocks]");
            return false;
        }
        try {
            Double range = Double.parseDouble(args[0]);
            RangeChat.getPlugin().getConfig().set("chat.range", range);
            RangeChat.getPlugin().saveConfig();
            sender.sendMessage("Chat Range set to " + range);
        } catch(NumberFormatException e) {
            sender.sendMessage("The Provided Range is invalid");
        }
        return false;
    }
}
