package net.frozed.spigot.knockback;

import net.frozed.spigot.util.ConfigFile;

public class KnockbackProfile {

    private final ConfigFile configFile;
    private final String name;
    private double horizontal = 0.325;
    private double vertical = 0.325;
    private double extraVertical = 0.1;
    private double extraHorizontal = 0.5;
    private double verticalLimit = 0.36;
    private double friction = 3.5;

    public KnockbackProfile(String name) {
        this.name = name;
        this.configFile = new ConfigFile("knockback/" + name + ".yml");

        load();
    }

    public void save() {
        configFile.set("horizontal", horizontal);
        configFile.set("vertical", vertical);
        configFile.set("extraVertical", extraVertical);
        configFile.set("extraHorizontal", extraHorizontal);
        configFile.set("verticalLimit", verticalLimit);
        configFile.set("friction", friction);
        configFile.save(true);
    }

    public void load() {
        horizontal = configFile.getDouble("horizontal", 0.325);
        vertical = configFile.getDouble("vertical", 0.325);
        extraVertical = configFile.getDouble("extraVertical", 0.1);
        extraHorizontal = configFile.getDouble("extraHorizontal", 0.5);
        verticalLimit = configFile.getDouble("verticalLimit", 0.36);
        friction = configFile.getDouble("friction", 3.5);
        save();
    }

    public String getName() {
        return name;
    }

    public double getHorizontal() {
        return horizontal;
    }

    public void setHorizontal(double horizontal) {
        this.horizontal = horizontal;
    }

    public double getVertical() {
        return vertical;
    }

    public void setVertical(double vertical) {
        this.vertical = vertical;
    }

    public double getExtraVertical() {
        return extraVertical;
    }

    public void setExtraVertical(double extraVertical) {
        this.extraVertical = extraVertical;
    }

    public double getExtraHorizontal() {
        return extraHorizontal;
    }

    public void setExtraHorizontal(double extraHorizontal) {
        this.extraHorizontal = extraHorizontal;
    }

    public double getVerticalLimit() {
        return verticalLimit;
    }

    public void setVerticalLimit(double verticalLimit) {
        this.verticalLimit = verticalLimit;
    }

    public double getFriction() {
        return friction;
    }

    public void setFriction(double friction) {
        this.friction = friction;
    }

    public ConfigFile getConfigFile() {
        return configFile;
    }
}
