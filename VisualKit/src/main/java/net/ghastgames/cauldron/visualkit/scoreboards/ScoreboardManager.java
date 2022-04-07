package net.ghastgames.cauldron.visualkit.scoreboards;

import net.ghastgames.cauldron.visualkit.VisualManager;
import net.ghastgames.cauldron.visualkit.scoreboards.value.ScoreboardValue;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class ScoreboardManager implements VisualManager<CauldronScoreboard, ScoreboardValue> {
    private HashMap<Player, CauldronScoreboard> scoreboards = new HashMap<>();
    private static ScoreboardManager instance;

    private ScoreboardManager() {}

    public static ScoreboardManager getInstance() {
        if(instance == null) instance = new ScoreboardManager();
        return instance;
    }

    public void set(Player player, CauldronScoreboard scoreboardToShow) {
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective( "ghast", "games");
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
                if(value.get().length() >= 14) {
                    dynamicValue.setPrefix(value.get().substring(0, 16));
                    dynamicValue.setSuffix(value.get().substring(16).isEmpty() ? "" : value.get().substring(16));
                } else {
                    dynamicValue.setPrefix(value.get());
                }
                objective.getScore(placeholders[count]).setScore(count);
            }
            count--;
        }
        objective.setDisplayName(scoreboardToShow.getTitle().get());
        player.setScoreboard(scoreboard);
        scoreboards.put(player, scoreboardToShow);
    }

    public void update(Player player, ScoreboardValue update) {
        player.getScoreboard().getTeam(update.id.toString()).setPrefix(update.get());
    }

    public void update(Player player, ScoreboardValue[] update) {
        CauldronScoreboard scoreboard = scoreboards.get(player);
        for (ScoreboardValue scoreboardValue : scoreboard.getContent()) {
            ScoreboardValue newValue = getById(scoreboardValue.id, update);
            player.getScoreboard().getTeam(newValue.id).setPrefix(newValue.get());
        }
    }

    public ScoreboardValue getById(String id, ScoreboardValue[] values) {
        for (ScoreboardValue value : values) {
            if(Objects.equals(value.id, id)) return value;
        }
        return null;
    }

    public ScoreboardValue getByReference(String reference, ScoreboardValue[] values) {
        for (ScoreboardValue value : values) {
            if(Objects.equals(value.getReference(), reference)) return value;
        }
        return null;
    }

    public CauldronScoreboard get(Player player) {
        return scoreboards.get(player);
    }
}
