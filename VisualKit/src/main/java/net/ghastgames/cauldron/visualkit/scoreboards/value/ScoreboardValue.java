package net.ghastgames.cauldron.visualkit.scoreboards.value;

import java.util.UUID;

public abstract class ScoreboardValue {
    public UUID id = UUID.randomUUID();
    public abstract boolean isStatic();
    public abstract String get();
}
