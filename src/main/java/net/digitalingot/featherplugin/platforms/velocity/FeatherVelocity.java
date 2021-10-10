package net.digitalingot.featherplugin.platforms.velocity;

import com.google.inject.Inject;
import com.velocitypowered.api.event.Subscribe;
import com.velocitypowered.api.event.proxy.ProxyInitializeEvent;
import com.velocitypowered.api.proxy.ProxyServer;
import net.digitalingot.featherplugin.ConfigProvider;
import net.digitalingot.featherplugin.FeatherConfig;
import net.digitalingot.featherserverapi.platforms.velocity.FeatherAPIVelocityHook;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class FeatherVelocity {

    @NotNull
    private final ProxyServer server;
    @NotNull
    private final Path dataFolder;

    @Inject
    public FeatherVelocity(@NotNull ProxyServer server, @NotNull Path dataFolder) {
        this.server = server;
        this.dataFolder = dataFolder;
    }

    @Subscribe
    public void onProxyInitialization(@NotNull ProxyInitializeEvent event) {
        ConfigProvider configProvider = new VelocityConfigProvider(dataFolder);
        FeatherConfig config;
        try {
            config = FeatherConfig.load(configProvider);
        } catch (Exception e) {
            throw new IllegalStateException("Error while trying to load the config", e);
        }

        FeatherAPIVelocityHook.onEnable(this, server);
        server.getEventManager().register(this, new VelocityFeatherLoginListener(config));
    }
}
