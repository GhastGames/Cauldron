package net.ghastgames.cauldron.visualkit.scoreboards;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class ScoreboardManager {
    private HashMap<Player, Scoreboard> scoreboards;
    private static ScoreboardManager instance;

    private ScoreboardManager() {}

    public static ScoreboardManager getInstance() {
        if(instance == null) instance = new ScoreboardManager();
        return instance;
    }
}
