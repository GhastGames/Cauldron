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
        String[] permissions = {};

        if(command.getClass().isAnnotationPresent(CommandDetails.class)) {
            CommandDetails details = command.getClass().getAnnotation(CommandDetails.class);
            commandName = Objects.requireNonNullElse(details.name(), "");
            permissions = Objects.requireNonNullElse(details.permissions(), new String[]{""});
        } else {
            commandName = "";
            permissions = new String[]{""};
        }

        if(commandName.isBlank()) {
            commandName = "ghastgames-" + new Random().nextInt();
        }

        plugin.getCommand(commandName).setExecutor((commandSender, c, s, args) -> command.execute(new CommandExecution(commandSender, args)));
    }
}
