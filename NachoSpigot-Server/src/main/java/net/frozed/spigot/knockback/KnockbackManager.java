package net.frozed.spigot.knockback;

import net.frozed.spigot.util.FrozedFileUtils;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class KnockbackManager {

    private final Map<String, KnockbackProfile> knockbackProfileMap = new HashMap<>();

    public KnockbackManager() {
        File file = new File("knockback");
        Arrays.stream(Objects.requireNonNull(file.listFiles())).forEach(fileName -> {
            KnockbackProfile profile = createProfile(FrozedFileUtils.transformName(fileName.getName()));
            System.out.println("Loaded profile called " + profile.getName());
        });
    }

    public KnockbackProfile createProfile(String name) {
        KnockbackProfile knockbackProfile = new KnockbackProfile(name);
        knockbackProfileMap.put(name, knockbackProfile);
        return knockbackProfile;
    }

    public void deleteProfile(String name) {
        KnockbackProfile knockbackProfile = getByName(name);
        knockbackProfile.getConfigFile().getFile().delete();
        knockbackProfileMap.remove(name);
    }

    public KnockbackProfile getByName(String name) {
        return knockbackProfileMap.getOrDefault(name, null);
    }

    public Map<String, KnockbackProfile> getKnockbackProfileMap() {
        return knockbackProfileMap;
    }
}
