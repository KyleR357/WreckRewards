package me.clutchmasterftw.wreckrewards;

import me.clutchmasterftw.wreckrewards.events.PrisonBlockBreaks;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class WreckRewards extends JavaPlugin {
    public static final String PREFIX = ChatColor.BLUE + "Wreck" + ChatColor.DARK_BLUE + "MC" + ChatColor.GRAY + " » " + ChatColor.RESET;

    public static WreckRewards getPlugin() {
        return plugin;
    }

    private static WreckRewards plugin;

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        Logger logger = getLogger();
        logger.info("WreckRewards has loaded successfully!");

        Bukkit.getServer().getPluginManager().registerEvents(new PrisonBlockBreaks(), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
