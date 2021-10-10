package net.digitalingot.featherplugin;

import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;

public interface ConfigProvider {

    @NotNull
    Path getPath();

}
