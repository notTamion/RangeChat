package de.tamion;

import de.tamion.commands.IgnoreHeight;
import de.tamion.commands.RequireLineOfSight;
import de.tamion.commands.SetChatRange;
import de.tamion.listeners.ChatListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class RangeChat extends JavaPlugin {

    private static RangeChat plugin;
    @Override
    public void onEnable() {
        plugin = this;
        PluginManager pluginManager = Bukkit.getPluginManager();

        pluginManager.registerEvents(new ChatListener(), this);

        getCommand("setchatrange").setExecutor(new SetChatRange());
        getCommand("ignoreheight").setExecutor(new IgnoreHeight());
        getCommand("requirelineofsight").setExecutor(new RequireLineOfSight());
    }

    public static RangeChat getPlugin() {
        return plugin;
    }
}
