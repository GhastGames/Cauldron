package net.ghastgames.cauldron.pluginkit.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.command.CommandSender;

@Data
@AllArgsConstructor
public class CommandExecution {
    private CommandSender sender;
    private String[] args;
}
