package net.ghastgames.cauldron.visualkit;

import lombok.Getter;
import net.ghastgames.cauldron.visualkit.scoreboards.value.ScoreboardValue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.LinkedList;

public class CauldronAnimation<T> {
    @Getter
    private final boolean infinite; // if not just until it's finished
    @Getter
    private final int speed = 1;
    @Getter
    private final LinkedList<Runnable> keyframes = new LinkedList<>();
    private final VisualManager<?, ?> manager;

    public CauldronAnimation(VisualManager<?, ?> manager, boolean infinite) {
        this.manager = manager;
        this.infinite = infinite;
    }

    public void addKeyframe(Player player, T change) {
        try {
            keyframes.add(new BukkitRunnable() {
                @Override
                public void run() {
                    ((VisualManager<?, T>) manager).update(player, change);
                }
            });
        } catch(ClassCastException exception) {
            throw new IllegalArgumentException("The VisualManager used is incompatible with this CauldronAnimation instance, since it has a different visual type.");
        }
    }

    public void addKeyframeForEveryone(T change) {
        try {
            keyframes.add(new BukkitRunnable() {
                @Override
                public void run() {
                    for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                        ((VisualManager<?, T>) manager).update(onlinePlayer, change);
                    }
                }
            });
        } catch(ClassCastException exception) {
            throw new IllegalArgumentException("The VisualManager used is incompatible with this CauldronAnimation instance, since it has a different visual type.");
        }
    }

    public void play(JavaPlugin plugin) {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, new BukkitRunnable() {
            int i = 0;

            @Override
            public void run() {
                if(i == keyframes.size()) {
                    if(!infinite) {
                        i = 0;
                    } else {
                        cancel();
                        return;
                    }
                }
                keyframes.get(i).run();
                i++;
            }
        }, 20 * speed, 0);
    }
}
