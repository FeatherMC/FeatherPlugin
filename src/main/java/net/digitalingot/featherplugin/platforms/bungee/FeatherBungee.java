package net.digitalingot.featherplugin.platforms.bungee;

import net.digitalingot.featherplugin.ConfigProvider;
import net.digitalingot.featherplugin.FeatherConfig;
import net.digitalingot.featherserverapi.platforms.bungee.FeatherAPIBungeeHook;
import net.md_5.bungee.api.plugin.Plugin;

public class FeatherBungee extends Plugin {

    @Override
    public void onEnable() {
        ConfigProvider configProvider = new BungeeConfigProvider(this);
        FeatherConfig config;
        try {
            config = FeatherConfig.load(configProvider);
        } catch (Exception e) {
            throw new IllegalStateException("Error while trying to load the config", e);
        }

        FeatherAPIBungeeHook.onEnable(this);
        this.getProxy().getPluginManager().registerListener(this, new BungeeFeatherLoginListener(config));
    }

    @Override
    public void onDisable() {
        FeatherAPIBungeeHook.onDisable(this);
    }
}
