package net.ghastgames.cauldron.visualkit;

import org.bukkit.entity.Player;

public interface VisualManager<T, U> {
    void set(Player player, T object);
    void update(Player player, U newObject);
    T get(Player player);
}
