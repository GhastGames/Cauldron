package net.ghastgames.cauldron.visualkit;

import net.ghastgames.cauldron.visualkit.scoreboards.ScoreboardManager;

public class VisualKit {
    private static VisualKit instance;
    private static ScoreboardManager scoreboardManager;

    private VisualKit() {}

    public static VisualKit getInstance() {
        if(instance == null) {
            instance = new VisualKit();
        }
        return instance;
    }
}
