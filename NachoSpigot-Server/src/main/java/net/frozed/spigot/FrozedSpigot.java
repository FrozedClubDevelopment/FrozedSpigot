package net.frozed.spigot;

import net.frozed.spigot.knockback.KnockbackManager;
import net.frozed.spigot.knockback.KnockbackProfile;

public class FrozedSpigot {

    private static FrozedSpigot INSTANCE;
    private final KnockbackManager knockbackManager;
    private KnockbackProfile activeKnockbackProfile;

    public FrozedSpigot() {
        INSTANCE = this;
        knockbackManager = new KnockbackManager();
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

    public static FrozedSpigot get() {
        return INSTANCE;
    }
}
