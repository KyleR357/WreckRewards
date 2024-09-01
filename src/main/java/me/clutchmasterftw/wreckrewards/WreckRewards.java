package me.clutchmasterftw.wreckrewards;

import me.clutchmasterftw.wreckrewards.events.PrisonBlockBreaks;
import org.bukkit.plugin.java.JavaPlugin;
import tech.mcprison.prison.Prison;

import java.util.logging.Logger;

public final class WreckRewards extends JavaPlugin {
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

        Prison.get().getEventBus().register(new PrisonBlockBreaks());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
