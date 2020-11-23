package net.frozed.spigot.util;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class ConfigFile extends YamlConfiguration {

    private final String name;
    private File file;

    public ConfigFile(String name) {
        this.name = name + ".yml";

        try {
            boolean newFile = false;
            file = new File(name);

            if (!file.exists()) {
                newFile = file.createNewFile();
            }

            load(file);

            if (newFile)
                this.save(file);

        } catch (Exception ignored) {
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