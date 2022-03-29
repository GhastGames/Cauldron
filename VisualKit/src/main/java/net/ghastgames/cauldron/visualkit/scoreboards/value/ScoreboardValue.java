package net.ghastgames.cauldron.visualkit.scoreboards.value;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class ScoreboardValue {
    public UUID id = UUID.randomUUID();
    public String value;
    @Getter
    private boolean staticVariable;

    public void set(String value) {
        this.value = value;
    }

    public String get() {
        return value;
    }
}
