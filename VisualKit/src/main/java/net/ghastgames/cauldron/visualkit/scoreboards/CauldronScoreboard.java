package net.ghastgames.cauldron.visualkit.scoreboards;

import lombok.Data;
import net.ghastgames.cauldron.visualkit.scoreboards.value.ScoreboardValue;

@Data
public class CauldronScoreboard {
    private String title;
    private ScoreboardValue[] content;
}
