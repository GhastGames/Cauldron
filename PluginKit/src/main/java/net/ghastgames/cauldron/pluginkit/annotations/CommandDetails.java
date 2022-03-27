package net.ghastgames.cauldron.pluginkit.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandDetails {
    String name();
    String[] permissions();
}
