package net.digitalingot.featherplugin.platforms.bukkit;

import net.digitalingot.featherplugin.ConfigProvider;
import net.digitalingot.featherplugin.FeatherConfig;
import net.digitalingot.featherserverapi.platforms.bukkit.FeatherAPIBukkitHook;
import org.bukkit.plugin.java.JavaPlugin;

public class FeatherBukkit extends JavaPlugin {

    @Override
    public void onEnable() {
        ConfigProvider configProvider = new BukkitConfigProvider(this);
        FeatherConfig config;
        try {
            config = FeatherConfig.load(configProvider);
        } catch (Exception e) {
            throw new IllegalStateException("Error while trying to load the config", e);
        }

        FeatherAPIBukkitHook.onEnable(this);
        this.getServer().getPluginManager().registerEvents(new BukkitFeatherLoginListener(config), this);
    }

    @Override
    public void onDisable() {
        FeatherAPIBukkitHook.onDisable(this);
    }
}
