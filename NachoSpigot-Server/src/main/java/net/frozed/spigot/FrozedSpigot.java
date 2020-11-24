package net.frozed.spigot;

import net.frozed.spigot.knockback.KnockbackManager;
import net.frozed.spigot.knockback.KnockbackProfile;
import net.frozed.spigot.util.ConfigFile;

public class FrozedSpigot {

    private static FrozedSpigot INSTANCE;
    private final KnockbackManager knockbackManager;
    private KnockbackProfile activeKnockbackProfile;
    private final ConfigFile frozedConfig;

    public FrozedSpigot() {
        INSTANCE = this;
        knockbackManager = new KnockbackManager();
        frozedConfig = new ConfigFile("frozedspigot.yml", true);
    }

    public KnockbackManager getKnockbackManager() {
        return knockbackManager;
    }

    public KnockbackProfile getActiveKnockbackProfile() {
        return activeKnockbackProfile;
    }

    public void setActiveKnockbackProfile(KnockbackProfile activeKnockbackProfile) {
        this.activeKnockbackProfile = activeKnockbackProfile;
    }

    public ConfigFile getFrozedConfig() {
        return frozedConfig;
    }

    public static FrozedSpigot get() {
        return INSTANCE;
    }
}
