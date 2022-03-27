package net.ghastgames.cauldron.pluginkit;

import net.ghastgames.cauldron.pluginkit.annotations.CommandDetails;
import net.ghastgames.cauldron.pluginkit.commands.CauldronCommand;
import net.ghastgames.cauldron.pluginkit.commands.CommandExecution;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Random;

public class PluginKit {
    private static JavaPlugin plugin;

    public static void init(JavaPlugin javaPlugin) {
        plugin = javaPlugin;
    }

    public static void registerCommand(CauldronCommand command) {
        String commandName = "";
        String permission = "";

        if(command.getClass().isAnnotationPresent(CommandDetails.class)) {
            CommandDetails details = command.getClass().getAnnotation(CommandDetails.class);
            commandName = Objects.requireNonNullElse(details.name(), "");
            permission = Objects.requireNonNullElse(details.permission(), "");
        } else {
            commandName = "";
            permission = "";
        }

        if(commandName.isBlank()) {
            commandName = "ghastgames-" + new Random().nextInt();
        }

        String finalPermission = permission;
        plugin.getCommand(commandName).setExecutor((commandSender, c, s, args) -> {
            if(!finalPermission.isEmpty()) {
                if(!commandSender.hasPermission(finalPermission)) {
                    commandSender.sendMessage("§cYou don't have permission to do this.");
                    return false;
                }
            }
            return command.execute(new CommandExecution(commandSender, args));
        });
    }
}
