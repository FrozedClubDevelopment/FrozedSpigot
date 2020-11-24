package net.frozed.spigot.util;

import net.minecraft.server.MobEffect;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;
import java.util.Set;

/**
 * Created by Elb1to
 * Project: FrozedSpigot
 * Date: 11/23/2020 @ 7:58 PM
 */
public class PotionUtils {

    public static PotionEffect toBukkit(MobEffect effect) {
        return new PotionEffect(PotionEffectType.getById(effect.getEffectId()), effect.getDuration(), effect.getAmplifier(), effect.isAmbient());
    }

    public static MobEffect toNMS(PotionEffect effect) {
        return new MobEffect(effect.getType().getId(), effect.getDuration(), effect.getAmplifier(), effect.isAmbient(), effect.hasParticles());
    }

    public static MobEffect cloneWithDuration(MobEffect effect, int duration) {
        return new MobEffect(effect.getEffectId(), duration, effect.getAmplifier(), effect.isAmbient(), effect.isShowParticles());
    }

    public static void extendDuration(MobEffect effect, int duration) {
        effect.a(cloneWithDuration(effect, duration));
    }

    // Boolean Util
    public static boolean isBoolean(String s) {
        return s.equalsIgnoreCase("true") || s.equalsIgnoreCase("false");
    }

    /**
     * Credits: https://github.com/TacoSpigot/TacoSpigot
     *
     * @param list     the list to remove from
     * @param toRemove what to remove
     * @param position the position to remove from
     * @param <T>      the type being removed
     * @return the new position
     */
    public static <T extends Marker> int removeAll(List<T> list, Set<T> toRemove, int position) {
        for (Marker marker : toRemove) {
            marker.markRemoval();
        }

        int size = list.size();
        int insertAt = 0;

        for (int i = 0; i < size; i++) {
            T element = list.get(i);
            if (i == position) position = insertAt;
            if (element != null && !element.isNeedRemoval()) {
                list.set(insertAt++, element);
            }
        }

        list.subList(insertAt, size).clear();

        return position;
    }

    // Remove-All Util
    public interface Marker {
        boolean isNeedRemoval();

        void markRemoval();
    }
}
