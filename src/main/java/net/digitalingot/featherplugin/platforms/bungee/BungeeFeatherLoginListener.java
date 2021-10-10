package net.digitalingot.featherplugin.platforms.bungee;

import net.digitalingot.featherplugin.FeatherConfig;
import net.digitalingot.featherserverapi.platforms.bungee.BungeeFeatherLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import org.jetbrains.annotations.NotNull;

class BungeeFeatherLoginListener implements Listener {

    @NotNull
    private final FeatherConfig config;

    BungeeFeatherLoginListener(@NotNull FeatherConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onFeatherLogin(@NotNull BungeeFeatherLoginEvent event) {
        event.getUser().disableMods(config.getDisabledMods());
    }

}
