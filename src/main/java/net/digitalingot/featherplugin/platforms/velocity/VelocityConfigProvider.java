package net.digitalingot.featherplugin.platforms.velocity;

import net.digitalingot.featherplugin.ConfigProvider;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public class VelocityConfigProvider implements ConfigProvider {

    @NotNull
    private final Path path;

    public VelocityConfigProvider(@NotNull Path path) {
        this.path = path;
    }

    @Override
    @NotNull
    public Path getPath() {
        return path;
    }

}
