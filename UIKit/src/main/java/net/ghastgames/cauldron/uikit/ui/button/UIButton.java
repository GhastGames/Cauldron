package net.ghastgames.cauldron.uikit.ui.button;

import lombok.Data;
import net.ghastgames.cauldron.uikit.ui.UIItem;
import org.bukkit.entity.Player;

@Data
public class UIButton {
    public interface ClickAction {
        void onClick(Player player);
    }
    UIItem item;
    ClickAction action;
}
