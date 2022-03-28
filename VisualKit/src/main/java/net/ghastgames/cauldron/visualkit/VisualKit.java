package net.ghastgames.cauldron.visualkit;

import net.ghastgames.cauldron.visualkit.scoreboards.ScoreboardManager;

public class VisualKit {
    private VisualKit instance;
    private ScoreboardManager scoreboardManager;

    private VisualKit() {}

    public VisualKit getInstance() {
        if(instance == null) {
            instance = new VisualKit();
        }
        return instance;
    }
}
