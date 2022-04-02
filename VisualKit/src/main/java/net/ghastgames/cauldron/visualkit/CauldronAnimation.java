package net.ghastgames.cauldron.visualkit;

import lombok.Getter;
import net.ghastgames.cauldron.visualkit.scoreboards.value.ScoreboardValue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CauldronAnimation<T> {
    @Getter
    private final boolean infinite; // if not just until it's finished
    @Getter
    private final int speed = 1;
    @Getter
    private final List<T> keyframes = new ArrayList<>();
    private final VisualManager<?, ?> manager;

    public CauldronAnimation(VisualManager<?, ?> manager, boolean infinite) {
        this.manager = manager;
        this.infinite = infinite;
    }

    public void addKeyframeForEveryone(T change) {
        keyframes.add(change);
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
                Bukkit.getLogger().info(String.valueOf(i));
                for (Player onlinePlayer : Bukkit.getOnlinePlayers()) {
                    ((VisualManager<?, T>) manager).update(onlinePlayer, keyframes.get(i));
                }
                i++;
            }
        }, 20 * speed, 0);
    }
}
