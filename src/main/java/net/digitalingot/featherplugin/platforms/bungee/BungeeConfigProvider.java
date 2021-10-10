package net.digitalingot.featherplugin.platforms.bungee;

import net.digitalingot.featherplugin.ConfigProvider;
import net.md_5.bungee.api.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

class BungeeConfigProvider implements ConfigProvider {

    @NotNull
    private final Plugin plugin;

    public BungeeConfigProvider(@NotNull Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    @NotNull
    public Path getPath() {
        return plugin.getDataFolder().toPath().resolve("config.toml");
    }

}
