package net.ghastgames.cauldron.visualkit.scoreboards.value;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ScoreboardValue {
    public UUID id = UUID.randomUUID();
    public String value;
    @Getter
    @Setter
    private boolean staticVariable = false;

    public void set(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }

    public static ScoreboardValue fromString(String value) {
        ScoreboardValue scoreboardValue = new ScoreboardValue();
        scoreboardValue.set(value);
        scoreboardValue.setStaticVariable(false);
        return scoreboardValue;
    }
}
