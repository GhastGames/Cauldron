package net.ghastgames.cauldron.visualkit;

import lombok.Getter;
import org.bukkit.entity.Player;

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
        this.manager.update(player, change);
    }
}
