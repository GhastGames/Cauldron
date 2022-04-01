package net.ghastgames.cauldron.visualkit.scoreboards;

import lombok.Data;
import net.ghastgames.cauldron.visualkit.scoreboards.value.ScoreboardValue;

@Data
public class CauldronScoreboard {
    private ScoreboardValue title;
    private ScoreboardValue[] content;

    public void updateValueByReference(String reference, ScoreboardValue newValue) {
        for (int i = 0; i < content.length; i++) {
            if(content[i].getReference() != null) {
                if (content[i].getReference().equals(reference)) {
                    newValue.setReference(content[i].getReference());
                    content[i] = newValue;
                }
            }
        }
    }
}
