package net.ghastgames.cauldron.visualkit.actionbar;

import net.ghastgames.cauldron.visualkit.VisualManager;
import net.minecraft.server.v1_8_R3.ChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class ActionbarManager implements VisualManager<Actionbar, Actionbar> {

    @Override
    public void set(Player player, Actionbar object) {
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer.a(object.getText()), (byte) 2));
    }

    @Override
    public void update(Player player, Actionbar newObject) {
        set(player, newObject);
    }

    @Override
    public Actionbar get(Player player) {
        return null;
    }
}
