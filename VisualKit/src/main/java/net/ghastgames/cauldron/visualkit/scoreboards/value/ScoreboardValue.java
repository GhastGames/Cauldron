package net.ghastgames.cauldron.visualkit.scoreboards.value;

import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;

public class ScoreboardValue {
    public String id = RandomStringUtils.random(8, "0123456789abcdef");
    public String value;
    @Getter
    @Setter
    private boolean staticVariable = false;
    @Getter
    @Setter
    private String reference;

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
