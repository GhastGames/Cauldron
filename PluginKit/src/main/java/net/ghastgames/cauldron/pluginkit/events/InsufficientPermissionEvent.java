package net.ghastgames.cauldron.pluginkit.events;

import lombok.Data;
import org.bukkit.entity.Player;

@Data
public class InsufficientPermissionEvent {
    String permission;
    Player player;
}
