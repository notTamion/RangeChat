package de.tamion.listeners;

import de.tamion.RangeChat;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.util.RayTraceResult;
import java.util.HashSet;

public class ChatListener implements Listener {
    @EventHandler
    public void onChat(PlayerChatEvent e) {
        Player p = e.getPlayer();
        FileConfiguration config = RangeChat.getPlugin().getConfig();
        double range = config.getDouble("chat.range");
        if(range == 0.0) {
            return;
        }
        new HashSet<>(e.getRecipients()).forEach(target -> {
            if(p.equals(target)) {
                return;
            }
            Location ploc = p.getEyeLocation();
            Location tloc = target.getEyeLocation();
            if(config.getBoolean("chat.requirelineofsight") && !isinlineofsight(ploc, tloc)) {
                e.getRecipients().remove(target);
                return;
            }
            if(config.getBoolean("chat.ignoreheight")) {
                ploc.setY(0);
                tloc.setY(0);
            }
            if(!p.getWorld().getPlayers().contains(target) || ploc.distance(tloc) >= range) {
                e.getRecipients().remove(target);
            }
        });
    }
    private static boolean isinlineofsight(Location a, Location b) {
        RayTraceResult rtr = a.getWorld().rayTraceBlocks(a, b.toVector().subtract(a.toVector()).normalize(), a.distance(b));
        if(rtr == null || rtr.getHitBlock().isPassable()) {
            return true;
        }
        return false;
    }
}
