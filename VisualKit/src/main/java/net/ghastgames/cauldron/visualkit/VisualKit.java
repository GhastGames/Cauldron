package net.ghastgames.cauldron.visualkit;

import net.ghastgames.cauldron.visualkit.scoreboards.ScoreboardManager;
import net.ghastgames.cauldron.visualkit.titles.TitleManager;

public class VisualKit {
    private static VisualKit instance;
    private final ScoreboardManager scoreboardManager = ScoreboardManager.getInstance();
    private final TitleManager titleManager = new TitleManager();

    private VisualKit() {}

    public static VisualKit getInstance() {
        if(instance == null) {
            instance = new VisualKit();
        }
        return instance;
    }

    public ScoreboardManager getScoreboardManager() {
        return scoreboardManager;
    }

    public TitleManager getTitleManager() {
        return titleManager;
    }
}
