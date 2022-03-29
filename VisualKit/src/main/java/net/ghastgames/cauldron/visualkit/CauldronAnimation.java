package net.ghastgames.cauldron.visualkit;

import lombok.Getter;
import net.ghastgames.cauldron.visualkit.scoreboards.value.ScoreboardValue;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.LinkedList;

public class CauldronAnimation<T> {
    @Getter
    private final boolean infinite; // if not just until it's finished
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

    public void play() {

    }
}
