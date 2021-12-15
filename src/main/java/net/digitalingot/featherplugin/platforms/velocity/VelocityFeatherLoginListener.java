package net.digitalingot.featherplugin.platforms.velocity;

import com.velocitypowered.api.event.Subscribe;
import net.digitalingot.featherplugin.FeatherConfig;
import net.digitalingot.featherserverapi.abstractions.FeatherUser;
import net.digitalingot.featherserverapi.platforms.velocity.VelocityFeatherLoginEvent;
import org.jetbrains.annotations.NotNull;

class VelocityFeatherLoginListener {

    @NotNull
    private final FeatherConfig config;

    VelocityFeatherLoginListener(@NotNull FeatherConfig config) {
        this.config = config;
    }

    @Subscribe
    public void onFeatherLogin(@NotNull VelocityFeatherLoginEvent event) {
        FeatherUser user = event.getUser();
        user.disableMods(config.getDisabledMods());
        user.setWaypoints(config.getWaypoints());
        if (config.isDisableHitDelay()) {
            user.disableHitDelay();
        }
    }

}
