package net.frozed.spigot.knockback;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class KnockbackManager {

    private final Map<String, KnockbackProfile> knockbackProfileMap = new HashMap<>();

    private void loadProfiles() {

    }

    public KnockbackProfile createProfile(String name) {
        KnockbackProfile knockbackProfile = new KnockbackProfile(name);
        knockbackProfileMap.put(name, knockbackProfile);
        return knockbackProfile;
    }

    public void deleteProfile(KnockbackProfile knockbackProfile) {
        knockbackProfile.getConfigFile().getFile().delete();
    }
}
