package net.ghastgames.cauldron.uikit;

import net.ghastgames.cauldron.uikit.ui.UserInterface;

public class UIKit {
    private static UIKit instance;

    public static UIKit getInstance() {
        if(instance == null) instance = new UIKit();
        return instance;
    }

    public void showUI(UserInterface userInterface) {

    }
}
