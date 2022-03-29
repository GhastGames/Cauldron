package net.ghastgames.cauldron.pluginkit;

import net.ghastgames.cauldron.pluginkit.annotations.CommandDetails;
import net.ghastgames.cauldron.pluginkit.commands.CauldronCommand;
import net.ghastgames.cauldron.pluginkit.commands.CommandExecution;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;
import java.util.Random;

public class PluginKit {
    private static JavaPlugin plugin;
    private static GlobalSettings settings;

    public static void init(JavaPlugin javaPlugin) {
        plugin = javaPlugin;
        initListeners();
    }

    private static void initListeners() {
        Bukkit.getPluginManager().registerEvents(new Listener() {

            @EventHandler
            public void onJoin(PlayerJoinEvent event) {
                if(settings.getMaxPlayers() == 0) return;
                if(Bukkit.getOnlinePlayers().size() >= settings.getMaxPlayers()) {
                    if(settings.getServerFullBypassPermission().isBlank()) {
                        if(!event.getPlayer().hasPermission(settings.getServerFullBypassPermission())) {
                            event.getPlayer().kickPlayer(Objects.requireNonNullElse(settings.getServerFullMessage(), "§cThis server is full."));
                            return;
                        }
                    }
                    event.getPlayer().kickPlayer(Objects.requireNonNullElse(settings.getServerFullMessage(), "§cThis server is full."));
                }
            }
        }, plugin);
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

    public static void registerBukkitCommand(String cmd, CommandExecutor executor) {
        plugin.getCommand(cmd).setExecutor(executor);
    }

    enum Restriction {
        BUILDING
    }

    public static GlobalSettings getSettings() {
        return settings;
    }
}
