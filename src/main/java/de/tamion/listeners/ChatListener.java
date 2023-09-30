package de.tamion.listeners;

import de.tamion.RangeChat;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashSet;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        FileConfiguration config = RangeChat.getPlugin().getConfig();
        double range = config.getDouble("chat.range");
        boolean ignoreheight = config.getBoolean("chat.ignoreheight");
        if(range == 0.0) {
            return;
        }
        if(ignoreheight) {
            new HashSet<>(e.getRecipients()).forEach(target -> {
                Location ploc = p.getLocation();
                ploc.setY(0);
                Location tloc = target.getLocation();
                tloc.setY(0);
                if(!p.getWorld().getPlayers().contains(target) || ploc.distance(tloc) >= range) {
                    e.getRecipients().remove(target);
                }
            });
        } else {
            new HashSet<>(e.getRecipients()).forEach(target -> {
                if(!p.getWorld().getPlayers().contains(target) || p.getLocation().distance(target.getLocation()) >= range) {
                    e.getRecipients().remove(target);
                }
            });
        }

    }
}
