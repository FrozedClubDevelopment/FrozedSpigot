package net.frozed.spigot;

import net.frozed.spigot.knockback.KnockbackProfile;

public enum FrozedSpigot {

    INSTANCE;

    private KnockbackProfile activeKnockbackProfile;

    public KnockbackProfile getActiveKnockbackProfile() {
        return activeKnockbackProfile;
    }

    public void setActiveKnockbackProfile(KnockbackProfile activeKnockbackProfile) {
        this.activeKnockbackProfile = activeKnockbackProfile;
    }
}
