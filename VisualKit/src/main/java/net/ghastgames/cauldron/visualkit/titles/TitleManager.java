package net.ghastgames.cauldron.visualkit.titles;

import net.ghastgames.cauldron.visualkit.VisualManager;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import org.apache.commons.lang.StringEscapeUtils;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class TitleManager implements VisualManager<Title, Title> {

    @Override
    public void set(Player player, Title newTitle) {
        CraftPlayer craftPlayer = (CraftPlayer) player;
        IChatBaseComponent title = IChatBaseComponent.ChatSerializer.a("{text: \"" + newTitle.getTitle().replace("\"", "") + "\"}");
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, title, newTitle.fadeIn, newTitle.stay, newTitle.fadeOut);

        IChatBaseComponent subtitle = IChatBaseComponent.ChatSerializer.a("{text: \"" + newTitle.getTitle().replace("\"", "") + "\"}");
        PacketPlayOutTitle packetPlayOutTitle2 = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, subtitle, newTitle.fadeIn, newTitle.stay, newTitle.fadeOut);

        craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutTitle);
        craftPlayer.getHandle().playerConnection.sendPacket(packetPlayOutTitle2);
    }

    @Override
    public void update(Player player, Title newObject) {
        set(player, newObject);
    }

    @Override
    public Title get(Player player) {
        return null;
    }
}
