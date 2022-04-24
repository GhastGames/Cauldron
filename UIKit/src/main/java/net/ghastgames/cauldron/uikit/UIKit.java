package net.ghastgames.cauldron.uikit;

import net.ghastgames.cauldron.uikit.ui.UserInterface;
import org.bukkit.entity.Player;

public class UIKit {
    private static UIKit instance;

    public static UIKit getInstance() {
        if(instance == null) instance = new UIKit();
        return instance;
    }

    public void showUI(Player player, UserInterface userInterface) {
        player.openInventory(userInterface.getInventory());
    }
}
