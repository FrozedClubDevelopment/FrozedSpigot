package net.frozed.spigot.util;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.concurrent.CompletableFuture;

public class ConfigFile extends YamlConfiguration {

    private final String name;
    private File file;

    public ConfigFile(String name, boolean saveFromResource) {
        this.name = name;
        try {
            boolean newFile = false;
            file = new File(name);

            if (!file.exists())
                newFile = true;

            load(file);

            if (newFile) {
                if (saveFromResource) {
                    InputStream inputStream = ConfigFile.class.getResourceAsStream(file.getName());
                    Files.copy(inputStream, file.toPath());
                } else {
                    save(file);
                }
            }

        } catch (Exception ignored) {
        }
    }

    public void save(boolean async) {
        try {
            if (async) {
                CompletableFuture.runAsync(() -> save(false));
                return;
            }
            save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return name;
    }

    public File getFile() {
        return file;
    }
}