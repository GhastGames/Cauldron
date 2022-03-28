package net.ghastgames.cauldron.pluginkit.commands;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ConditionResponse {
    boolean ok;
    String message;
}
