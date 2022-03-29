package net.ghastgames.cauldron.visualkit.scoreboards;

import net.ghastgames.cauldron.visualkit.VisualManager;
import net.ghastgames.cauldron.visualkit.scoreboards.value.ScoreboardValue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.HashMap;
import java.util.UUID;

public class ScoreboardManager implements VisualManager<CauldronScoreboard, ScoreboardValue> {
    private HashMap<Player, CauldronScoreboard> scoreboards;
    private static ScoreboardManager instance;

    private ScoreboardManager() {}

    public static ScoreboardManager getInstance() {
        if(instance == null) instance = new ScoreboardManager();
        return instance;
    }

    public void set(Player player, CauldronScoreboard scoreboardToShow) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("ghast", "games");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        int count = scoreboardToShow.getContent().length;
        String[] placeholders = new String[]{"§a", "§b", "§c", "§d", "§e", "§f", "§1", "§2", "§3", "§4", "§a§b", "§l", "§u", "§o", "§u§l"};
        if(count > placeholders.length) {
            throw new IllegalArgumentException("Scoreboard too long");
        }
        for(ScoreboardValue value : scoreboardToShow.getContent()) {
            if(value.isStaticVariable()) {
                Score score = objective.getScore(value.get());
                score.setScore(count);
            } else {
                Team dynamicValue = scoreboard.registerNewTeam(value.id);
                dynamicValue.addEntry(placeholders[count]);
                dynamicValue.setPrefix(value.get());
            }
            count--;
        }
        objective.setDisplayName(scoreboardToShow.getTitle().get());
        player.setScoreboard(scoreboard);
    }

    public void update(Player player, ScoreboardValue update) {
        player.getScoreboard().getTeam(update.id.toString()).setPrefix(update.get());
    }
}
