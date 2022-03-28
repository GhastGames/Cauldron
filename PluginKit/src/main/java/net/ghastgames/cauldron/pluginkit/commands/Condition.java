package net.ghastgames.cauldron.pluginkit.commands;

import org.bukkit.command.CommandSender;

public interface Condition {
    ConditionResponse execute(CommandSender sender, String[] args);
}
