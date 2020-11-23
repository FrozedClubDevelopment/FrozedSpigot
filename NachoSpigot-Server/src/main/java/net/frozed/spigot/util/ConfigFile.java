package net.frozed.spigot.util;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

public class ConfigFile extends YamlConfiguration {

    private final String name;
    private File file;

    public ConfigFile(String name) {
        this.name = name;

        try {
            boolean newFile = false;
            file = new File(name);

            if (!file.exists())
                newFile = file.createNewFile();

            load(file);

            if (newFile)
                this.save(file);

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