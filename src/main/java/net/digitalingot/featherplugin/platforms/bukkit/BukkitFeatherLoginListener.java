package net.digitalingot.featherplugin.platforms.bukkit;

import net.digitalingot.featherplugin.FeatherConfig;
import net.digitalingot.featherserverapi.platforms.bukkit.BukkitFeatherLoginEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

class BukkitFeatherLoginListener implements Listener {

    @NotNull
    private final FeatherConfig config;

    public BukkitFeatherLoginListener(@NotNull FeatherConfig config) {
        this.config = config;
    }

    @EventHandler
    public void onFeatherLogin(@NotNull BukkitFeatherLoginEvent event) {
        event.getUser().disableMods(config.getDisabledMods());
    }

}
