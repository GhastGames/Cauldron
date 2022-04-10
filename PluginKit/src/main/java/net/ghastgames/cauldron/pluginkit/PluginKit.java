package net.ghastgames.cauldron.pluginkit;

import lombok.Getter;
import net.ghastgames.cauldron.pluginkit.annotations.CommandDetails;
import net.ghastgames.cauldron.pluginkit.commands.CauldronCommand;
import net.ghastgames.cauldron.pluginkit.commands.CommandExecution;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Random;

public class PluginKit {
    @Getter
    private static JavaPlugin plugin;
    private static final GlobalSettings settings = new GlobalSettings();

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
                    if(settings.getServerFullBypassPermission().isEmpty()) {
                        if(!event.getPlayer().hasPermission(settings.getServerFullBypassPermission())) {
                            event.getPlayer().kickPlayer(settings.getServerFullMessage() == null ? "§cThis server is full." : settings.getServerFullMessage());
                            return;
                        }
                    }
                    event.getPlayer().kickPlayer(settings.getServerFullMessage() == null ? "§cThis server is full." : settings.getServerFullMessage());
                }
            }
        }, plugin);
    }

    public static void registerCommand(CauldronCommand command) {
        String commandName = "";
        String permission = "";

        if(command.getClass().isAnnotationPresent(CommandDetails.class)) {
            CommandDetails details = command.getClass().getAnnotation(CommandDetails.class);
            commandName = (details.name() == null ? "" : details.name());
            permission = (details.permission() == null ? "" : permission);
        } else {
            commandName = "";
            permission = "";
        }

        if(commandName.isEmpty()) {
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
