package net.digitalingot.featherplugin.platforms.bukkit;

import net.digitalingot.featherplugin.ConfigProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

class BukkitConfigProvider implements ConfigProvider {
    @NotNull
    private final JavaPlugin plugin;

    public BukkitConfigProvider(@NotNull JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    @NotNull
    public Path getPath() {
        return plugin.getDataFolder().toPath().resolve("config.toml");
    }
}
