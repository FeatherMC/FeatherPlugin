package net.digitalingot.featherplugin;

import com.moandjiezana.toml.Toml;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

public class FeatherConfig {

    @NotNull
    public static FeatherConfig load(@NotNull ConfigProvider configProvider) throws Exception {
        Path path = configProvider.getPath();

        // create file if not exists
        createIfNotExists(path);

        // load & parse file
        Toml toml = new Toml().read(path.toFile());

        // parse the disabled mods array
        List<String> disabledMods = toml.getList("disabledMods");

        return new FeatherConfig(disabledMods);
    }

    private static void createIfNotExists(@NotNull Path path) throws Exception {
        path.getParent().toFile().mkdirs();
        if (!path.toFile().createNewFile()) return;

        List<String> lines;
        try (InputStream inputStream = FeatherConfig.class.getResourceAsStream("/config.toml");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            lines = reader.lines().collect(Collectors.toList());
        }

        Files.write(path, lines);
    }

    @NotNull
    private final List<String> disabledMods;

    private FeatherConfig(@NotNull List<String> disabledMods) {
        this.disabledMods = disabledMods;
    }

    @NotNull
    public List<String> getDisabledMods() {
        return disabledMods;
    }
}
