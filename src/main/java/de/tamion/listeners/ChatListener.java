package de.tamion.listeners;

import de.tamion.RangeChat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.HashSet;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        Double range = RangeChat.getPlugin().getConfig().getDouble("chat.range");
        if(range != 0.0) {
            new HashSet<>(e.getRecipients()).forEach(target -> {
                if(!p.getWorld().getPlayers().contains(target) || p.getLocation().distance(target.getLocation()) >= range) {
                    e.getRecipients().remove(target);
                }
            });
        }
    }
}
