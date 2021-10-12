package net.digitalingot.featherplugin;

import com.moandjiezana.toml.Toml;
import net.digitalingot.featherserverapi.proto.models.ChromaColor;
import net.digitalingot.featherserverapi.proto.models.Waypoint;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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

        // parse waypoints array
        List<Toml> waypointsToml = toml.getList("waypoints");

        List<Waypoint> waypoints = new ArrayList<>();
        for (Toml waypointToml : waypointsToml) {
            String name = waypointToml.getString("name");
            boolean chroma = waypointToml.getBoolean("chroma");
            int color = (int) (long) waypointToml.getLong("color");
            String world = waypointToml.getString("world");
            int x = (int) (long) waypointToml.getLong("x");
            int y = (int) (long) waypointToml.getLong("y");
            int z = (int) (long) waypointToml.getLong("z");

            waypoints.add(new Waypoint(name, new ChromaColor(chroma, color), world, new Waypoint.Location(x, y, z)));

        }

        return new FeatherConfig(disabledMods, waypoints);
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
    @NotNull
    private final List<Waypoint> waypoints;

    private FeatherConfig(@NotNull List<String> disabledMods, @NotNull List<Waypoint> waypoints) {
        this.disabledMods = disabledMods;
        this.waypoints = waypoints;
    }

    @NotNull
    public List<String> getDisabledMods() {
        return disabledMods;
    }

    @NotNull
    public List<Waypoint> getWaypoints() {
        return waypoints;
    }
}
